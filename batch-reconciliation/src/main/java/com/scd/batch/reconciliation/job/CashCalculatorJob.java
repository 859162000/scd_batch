package com.scd.batch.reconciliation.job;

import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.CommonErrorMsg;
import com.scd.batch.common.constant.reconciliation.TransferErrorStatus;
import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.dao.reconciliation.TransferErrorDao;
import com.scd.batch.common.entity.reconciliation.CashErrorBase;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.TransferErrorBase;
import com.scd.batch.common.entity.reconciliation.TransferErrorEntity;
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
 * 取现对账任务
 */
public class CashCalculatorJob extends ReconciliationBatchJob {

    @Autowired
    private TransferErrorDao transferErrorDao;

    @Override
    protected JobType getJobType() {
        return JobType.CashCalculatorJob;
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

        logger.info("sourceType:" + sourceType.getType() + ", sourceLines:" + sourceLines);
        if (sourceType.getType() == SourceType.HUIFU.getType()) {

            List<CashTransferEntity> transferEntityList = CommonUtil.transform2Bean(sourceLines,
                    CashTransferEntity.class);

            for (CashTransferEntity transfer : transferEntityList) {

                // TODO 先使用HASH，考虑使用redis，不存在，则写入hash
                transferRepo.computeIfAbsent(transfer.getOrdId(),
                        new Function<String, CashErrorBase>() {
                            public CashErrorBase apply(String t) {
                                return new CashErrorBase(t, null, transfer, TransferErrorType.HUIFU);
                            }
                        });

                // 存在，则对账
                transferRepo.computeIfPresent(transfer.getOrdId(),
                        new BiFunction<String, TransferErrorBase, TransferErrorBase>() {

                            public TransferErrorBase apply(String t, TransferErrorBase u) {

                                CashErrorBase cashErrorEntity = (CashErrorBase) u;
                                if (cashErrorEntity.getTransferErrorType().getType() == TransferErrorType.SCD.getType
                                        ()) {
                                    // 能对上，则删除原来的数据
                                    if (transfer.getValue().equals(cashErrorEntity.getWithdrawL().getValue())) {
                                        return null;
                                    } else {
                                        // 对不上，双方数据不匹配，设置汇付流水
                                        cashErrorEntity.setTransferEntity(transfer);
                                        cashErrorEntity.setTransferErrorType(TransferErrorType.BOTH);
                                    }
                                }
                                return cashErrorEntity;
                            }
                        }
                );

            }
        } else if (sourceType.getType() == SourceType.SCD.getType()) {

            List<WithdrawL> withdrawLList = CommonUtil.transform2Bean(sourceLines,
                    WithdrawL.class);

            for (WithdrawL withdrawL : withdrawLList) {

                // TODO 先使用HASH，考虑使用redis，不存在，则写入hash
                transferRepo.computeIfAbsent(withdrawL.getOrderId(),
                        new Function<String, CashErrorBase>() {
                            public CashErrorBase apply(String t) {
                                return new CashErrorBase(t, withdrawL, null, TransferErrorType.SCD);
                            }
                        });

                // 存在，则对账
                transferRepo.computeIfPresent(withdrawL.getOrderId(),
                        new BiFunction<String, TransferErrorBase, TransferErrorBase>() {

                            public TransferErrorBase apply(String t, TransferErrorBase u) {

                                CashErrorBase cashErrorEntity = (CashErrorBase) u;
                                if (cashErrorEntity.getTransferErrorType().getType() == TransferErrorType.HUIFU
                                        .getType()) {
                                    // 能对上，则删除原来的数据
                                    if (withdrawL.getValue().equals(cashErrorEntity.getTransferEntity().getValue())) {
                                        return null;
                                    } else {
                                        // 对不上，双方数据不匹配，设置SCD流水
                                        cashErrorEntity.setWithdrawL(withdrawL);
                                        cashErrorEntity.setTransferErrorType(TransferErrorType.BOTH);
                                    }
                                }

                                return cashErrorEntity;
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

            CashErrorBase cashError = (CashErrorBase) v;

            // 差错处理状态为初始态
            TransferErrorEntity entity = new TransferErrorEntity(k,
                    TransferType.WITHDRAW.getType(),
                    cashError.getTransferErrorType().getType(),
                    TransferErrorStatus.INIT.getType());

            if (cashError.getWithdrawL() != null) {
                entity.setaValue(cashError.getWithdrawL().getValue());
                entity.setaJson(JsonUtils.toJson(cashError.getWithdrawL()));
            }

            if (cashError.getTransferEntity() != null) {
                entity.setbValue(cashError.getTransferEntity().getValue());
                entity.setbJson(JsonUtils.toJson(cashError.getTransferEntity()));
            }

            errorEntityList.add(entity);
        });

        transferErrorDao.batchInsert(TableSpec.getDefault(), errorEntityList);
    }


}
