package com.scd.batch.reconciliation.job;

import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.CommonErrorMsg;
import com.scd.batch.common.constant.reconciliation.TransferErrorStatus;
import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.dao.reconciliation.TransferErrorDao;
import com.scd.batch.common.entity.reconciliation.*;
import com.scd.batch.common.entity.trade.WithdrawL;
import com.scd.batch.common.exception.ErrorCodeException;
import com.scd.batch.common.job.batch.*;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.constants.SourceType;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.TableSpec;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 商户扣款对账任务
 */
@Deprecated
public class TrfCalculatorJob extends ReconciliationBatchJob {

    @Autowired
    private TransferErrorDao transferErrorDao;

    @Override
    protected JobType getJobType() {
        // TODO
//        return JobType.TrfCalculatorJob;
        return null;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.UPDATE;
    }

    @Override
    protected PhaseType getNextPhase() {
        return null;
    }

    @Override
    protected SourceDataProvider getHuiFuSourcesDataProvider() {
        // Read local source file
        return getSourceFileProvider(SourceType.HUIFU);
    }

    @Override
    protected SourceDataProvider getScdSourcesDataProvider() {
        // Read local source file
        return getSourceFileProvider(SourceType.SCD);
    }

    @Override
    protected TargetDataHandler getTargetReconciliationHandler() {
        return getTargetFileHandler();
    }

    @Override
    protected ReconliationCalculator getReconliationCalculator() {
        // Calculate with source data lines
        return this::reconciliaiton;
    }

    /**
     * 对账
     */
    public void reconciliaiton(SourceType sourceType,
                               List<String> sourceLines,
                               ConcurrentHashMap<String, TransferErrorBase> transferRepo) {

        if (sourceType.getType() == SourceType.HUIFU.getType()) {

            List<TrfTransferEntity> transferEntityList = CommonUtil.transform2Bean(sourceLines,
                    TrfTransferEntity.class);

            for (TrfTransferEntity transfer : transferEntityList) {

                // TODO 先使用HASH，考虑使用redis，不存在，则写入hash
                transferRepo.computeIfAbsent(transfer.getOrdId(),
                        new Function<String, TrfErrorBase>() {
                            public TrfErrorBase apply(String t) {
                                return new TrfErrorBase(t, null, transfer, TransferErrorType.HUIFU);
                            }
                        });

                // 存在，则对账
                transferRepo.computeIfPresent(transfer.getOrdId(),
                        new BiFunction<String, TransferErrorBase, TransferErrorBase>() {

                            public TransferErrorBase apply(String t, TransferErrorBase u) {

                                TrfErrorBase trfErrorBase = (TrfErrorBase) u;
                                if (trfErrorBase.getTransferErrorType().getType() == TransferErrorType.SCD.getType
                                        ()) {
                                    // 能对上，则删除原来的数据
                                    if (transfer.getValue().equals(trfErrorBase.getScdTransfer().getValue())) {
                                        return null;
                                    } else {
                                        // 对不上，双方数据不匹配，设置汇付流水
                                        trfErrorBase.setHuifuTransfer(transfer);
                                        trfErrorBase.setTransferErrorType(TransferErrorType.BOTH);
                                    }
                                }
                                return trfErrorBase;
                            }
                        }
                );

            }
        } else if (sourceType.getType() == SourceType.SCD.getType()) {

            List<TrfTransferEntity> transferEntityList = CommonUtil.transform2Bean(sourceLines,
                    TrfTransferEntity.class);

            for (TrfTransferEntity transfer : transferEntityList) {

                // TODO 先使用HASH，考虑使用redis，不存在，则写入hash
                transferRepo.computeIfAbsent(transfer.getOrdId(),
                        new Function<String, TrfErrorBase>() {
                            public TrfErrorBase apply(String t) {
                                return new TrfErrorBase(t, transfer, null, TransferErrorType.SCD);
                            }
                        });

                // 存在，则对账
                transferRepo.computeIfPresent(transfer.getOrdId(),
                        new BiFunction<String, TransferErrorBase, TransferErrorBase>() {

                            public TransferErrorBase apply(String t, TransferErrorBase u) {

                                TrfErrorBase errorBase = (TrfErrorBase) u;
                                if (errorBase.getTransferErrorType().getType() == TransferErrorType.HUIFU
                                        .getType()) {
                                    // 能对上，则删除原来的数据
                                    if (transfer.getValue().equals(errorBase.getHuifuTransfer().getValue())) {
                                        return null;
                                    } else {
                                        // 对不上，双方数据不匹配，设置SCD流水
                                        errorBase.setScdTransfer(transfer);
                                        errorBase.setTransferErrorType(TransferErrorType.BOTH);
                                    }
                                }

                                return errorBase;
                            }
                        }
                );

            }

            logger.error("reconciliation: transferEntity.size():", sourceLines.size());
        } else {
            throw new ErrorCodeException(CommonErrorCode.REC_INVALID_SOURCETYPE,
                    CommonErrorMsg.REC_INVALID_SOURCETYPE);
        }

        // 更新到数据库
        batchInsertResult2DB(transferRepo);
    }

    /**
     * 对账结果写入数据库
     *
     * @param transferRepo
     */

    private void batchInsertResult2DB(ConcurrentHashMap<String, TransferErrorBase> transferRepo) {

        List<TransferErrorEntity> errorEntityList = new ArrayList<>();

        transferRepo.forEach((k, v) -> {

            TrfErrorBase trfErrorBase = (TrfErrorBase) v;

            // 差错处理状态为初始态
            TransferErrorEntity entity = new TransferErrorEntity(k,
                    TransferType.LOANS.getType(),
                    trfErrorBase.getTransferErrorType().getType(),
                    TransferErrorStatus.INIT.getType());

            if (trfErrorBase.getScdTransfer() != null) {
                entity.setaValue(trfErrorBase.getScdTransfer().getValue());
                entity.setaJson(JsonUtils.toJson(trfErrorBase.getScdTransfer()));
            }

            if (trfErrorBase.getHuifuTransfer() != null) {
                entity.setbValue(trfErrorBase.getHuifuTransfer().getValue());
                entity.setbJson(JsonUtils.toJson(trfErrorBase.getHuifuTransfer()));
            }

            errorEntityList.add(entity);
        });

        transferErrorDao.batchInsert(TableSpec.getDefault(), errorEntityList);
    }


}
