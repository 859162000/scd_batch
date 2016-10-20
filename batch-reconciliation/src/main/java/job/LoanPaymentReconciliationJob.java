package job;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.reconciliation.LoanPaymentErrorEntity;
import com.scd.batch.common.entity.trade.LoanEntity;
import com.scd.batch.common.job.batch.*;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.constants.SourceType;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.common.utils.EnumUtils;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.sun.tools.javac.code.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 放还款对账任务
 */
public class LoanPaymentReconciliationJob extends ReconciliationBatchJob {

    @Override
    protected JobType getJobType() {
        return JobType.LOAN_RECONCILIATION;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.CALCULATE;
    }

    @Override
    protected PhaseType getNextPhase() {
        return PhaseType.UPDATE;
    }

    @Override
    protected SourceDataProvider getSourceDataProvider() {
        // Read local source file
        return getSourceFileProvider();
    }

    @Override
    protected TargetReconcliationHandler getTargetReconciliationHandler() {
        return this::batchInsertResult2DB;
    }

    @Override
    protected ReconliationCalculator getReconliationCalculator() {
        // Calculate with source data lines
        return this::reconciliaiton;
    }

    /**
     * 对账
     */
    private void reconciliaiton(SourceType sourceType, List<String> sourceLines, TransferRepo transferRepo) {

        List<LoanPaymentTransferEntity> transferEntityList = CommonUtil.transform2Bean(sourceLines, LoanPaymentTransferEntity.class);

        ShortDate accountDate = getExecutorContext().getAttach(ShortDate.class);

        transferEntityList.parallelStream().forEach(transferEntity -> {

                    // 不存在，则写入
                    transferRepo.repo.computeIfAbsent(transferEntity.getOrdId(), new Function<String, String>() {
                        public String apply(String t) {
                            return sourceType.getType() + "," + JsonUtils.toJson(transferEntity);
                        }
                    });

                    // 存在，则对账
                    transferRepo.repo.computeIfPresent(transferEntity.getOrdId(), new BiFunction<String, String, String>() {
                        public String apply(String t, String u) {

                            // 能对上，则删除原来的数据
                            SourceType originSourceType = EnumUtils.getEnum(SourceType.class, u.substring(0, u.indexOf(",")));
                            if (originSourceType.equals(sourceType)) {
                                return t;
                            }

                            // TODO 换成SCD的流水
                            LoanPaymentTransferEntity originEntity = JsonUtils.toBean(u.substring(u.indexOf(",") + 1), LoanPaymentTransferEntity.class);

                            if (originEntity.getValue().equals(transferEntity.getValue())) {
                                return null;
                            }

                            // 不能对上，则写入差错数据
                            return t + "|" + sourceType.getType() + "," + JsonUtils.toJson(transferEntity);
                        }
                    });
                }

        );

        logger.error("reconciliation: transferEntity.size():", sourceLines.size());
    }

    /**
     * 对账结果写入数据库
     *
     * @param transferRepo
     */
    private void batchInsertResult2DB(TransferRepo transferRepo) {

        // TODO 使用安全类型
        List<LoanPaymentErrorEntity> errorEntityList = new ArrayList<>();

        transferRepo.repo.forEach((k, v) -> {
            errorEntityList.add(buildErrorEntity(k, v));
        });

        // TODO 写入数据库

    }

    /**
     * 构建差错对象
     *
     * @param key
     * @param value
     * @return
     */
    private LoanPaymentErrorEntity buildErrorEntity(String key, String value) {

        LoanPaymentTransferEntity transferEntity = null;
        LoanEntity loanEntity = null;
        TransferErrorType errorType = null;

        if (value.contains("|")) {
            errorType = TransferErrorType.BOTH;

            String[] valueArray = value.split("|");
            SourceType sourceType = EnumUtils.getEnum(SourceType.class, valueArray[0].substring(0, valueArray[0].indexOf(",")));

            if (sourceType.equals(SourceType.HUIFU)) {
                transferEntity = JsonUtils.toBean(valueArray[0].substring(valueArray[0].indexOf(",") + 1), LoanPaymentTransferEntity.class);
            }

            // TODO SCD流水

        } else {

            // TODO 某一方单边
            errorType = TransferErrorType.SCD;
        }

        LoanPaymentErrorEntity errorEntity = new LoanPaymentErrorEntity(errorType, key, loanEntity, transferEntity);

        return errorEntity;
    }

}
