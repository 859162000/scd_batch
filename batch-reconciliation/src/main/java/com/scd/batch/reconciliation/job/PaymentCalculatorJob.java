package com.scd.batch.reconciliation.job;

import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.CommonErrorMsg;
import com.scd.batch.common.constant.reconciliation.TransferErrorStatus;
import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.dao.reconciliation.TransferErrorDao;
import com.scd.batch.common.entity.reconciliation.*;
import com.scd.batch.common.entity.bid.LoanEntity;
import com.scd.batch.common.exception.ErrorCodeException;
import com.scd.batch.common.job.batch.ReconciliationBatchJob;
import com.scd.batch.common.job.batch.ReconliationCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
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
 * 放还款对账任务
 */
public class PaymentCalculatorJob extends ReconciliationBatchJob {

    @Autowired
    private TransferErrorDao transferErrorDao;

    @Override
    protected JobType getJobType() {
        return JobType.PaymentCalculatorJob;
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

            List<LoanPaymentTransferEntity> transferEntityList = CommonUtil.transform2Bean(sourceLines,
                    LoanPaymentTransferEntity.class);

            for (LoanPaymentTransferEntity transfer : transferEntityList) {

                // TODO 先使用HASH，考虑使用redis，不存在，则写入hash
                transferRepo.computeIfAbsent(transfer.getOrdId(),
                        new Function<String, LoanErrorBase>() {
                            public LoanErrorBase apply(String t) {
                                return new LoanErrorBase(t, null, transfer, TransferErrorType.HUIFU);
                            }
                        });

                // 存在，则对账
                transferRepo.computeIfPresent(transfer.getOrdId(),
                        new BiFunction<String, TransferErrorBase, LoanErrorBase>() {

                            public LoanErrorBase apply(String t, TransferErrorBase u) {

                                LoanErrorBase paymentErrorEntity = (LoanErrorBase) u;

                                if (paymentErrorEntity.getTransferErrorType().getType() == TransferErrorType.SCD
                                        .getType()) {
                                    // 能对上，则删除原来的数据
                                    if (transfer.getValue().equals(paymentErrorEntity.getLoanEntity().getValue())) {
                                        return null;
                                    } else {
                                        // 对不上，双方数据不匹配，设置汇付流水
                                        paymentErrorEntity.setTransferEntity(transfer);
                                        paymentErrorEntity.setTransferErrorType(TransferErrorType.BOTH);
                                    }
                                }
                                return paymentErrorEntity;
                            }
                        }
                );

            }
        } else if (sourceType.getType() == SourceType.SCD.getType()) {

            List<LoanEntity> loanList = CommonUtil.transform2Bean(sourceLines,
                    LoanEntity.class);

            for (LoanEntity loan : loanList) {

                // TODO 先使用HASH，考虑使用redis，不存在，则写入hash
                transferRepo.computeIfAbsent(loan.getSeqId(),
                        new Function<String, LoanErrorBase>() {
                            public LoanErrorBase apply(String t) {
                                return new LoanErrorBase(t, loan, null, TransferErrorType.SCD);
                            }
                        });

                // 存在，则对账
                transferRepo.computeIfPresent(loan.getSeqId(),
                        new BiFunction<String, TransferErrorBase, LoanErrorBase>() {

                            public LoanErrorBase apply(String t, TransferErrorBase u) {

                                LoanErrorBase paymentErrorEntity = (LoanErrorBase) u;

                                if (paymentErrorEntity.getTransferErrorType().getType() == TransferErrorType.HUIFU
                                        .getType()) {
                                    // 能对上，则删除原来的数据
                                    if (loan.getValue().equals(paymentErrorEntity.getTransferEntity().getValue())) {
                                        return null;
                                    } else {
                                        // 对不上，双方数据不匹配，设置SCD流水
                                        paymentErrorEntity.setLoanEntity(loan);
                                        paymentErrorEntity.setTransferErrorType(TransferErrorType.BOTH);
                                    }
                                }

                                return paymentErrorEntity;
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

            PaymentErrorBase errorBase = (PaymentErrorBase) v;

            // 差错处理状态为初始态
            TransferErrorEntity entity = new TransferErrorEntity(k,
                    TransferType.WITHDRAW.getType(),
                    errorBase.getTransferErrorType().getType(),
                    TransferErrorStatus.INIT.getType());

            if (errorBase.getCreditRepayReal() != null) {
                entity.setaValue(errorBase.getCreditRepayReal().getValue());
                entity.setaJson(JsonUtils.toJson(errorBase.getCreditRepayReal()));
            }

            if (errorBase.getTransferEntity() != null) {
                entity.setbValue(errorBase.getTransferEntity().getValue());
                entity.setbJson(JsonUtils.toJson(errorBase.getTransferEntity()));
            }

            errorEntityList.add(entity);
        });

        transferErrorDao.batchInsert(TableSpec.getDefault(), errorEntityList);
    }


}
