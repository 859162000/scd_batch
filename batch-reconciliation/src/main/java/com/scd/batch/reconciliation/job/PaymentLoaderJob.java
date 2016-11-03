package com.scd.batch.reconciliation.job;

import com.google.common.collect.Lists;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.dao.reconciliation.LoanPaymentTransferDao;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.job.batch.DataFlowBatchJob;
import com.scd.batch.common.job.batch.DataFlowCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class PaymentLoaderJob extends DataFlowBatchJob {

    @Resource
    private LoanPaymentTransferDao transferDao;

    @Override
    protected JobType getJobType() {
        return JobType.PaymentCalculatorJob;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.LOAD;
    }

    @Override
    protected PhaseType getNextPhase() {
        return PhaseType.CALCULATE;
    }

    @Override
    protected SourceDataProvider getSourceDataProvider() {
        // Source from DB
        return () -> batchQueryDB(getExecutorContext());
    }

    @Override
    protected DataFlowCalculator getDataFlowCalculator() {
        // No data calculate
        return getNoOpCalculator();
    }

    @Override
    protected TargetDataHandler getTargetDataHandler() {
        // Target save to local file
        return getTargetFileHandler();
    }

    /**
     * Closure: batch query DB
     */
    public List<String> batchQueryDB(ExecutorContext context) {

        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        ShortDate accountDate = context.getAttach(ShortDate.class);

        // 批量获取 ID
        List<Long> batchIds = getBatchIdList(context);

        // 数据已全部查出
        if (batchIds == null) {
            return null;
        }

        List<LoanPaymentTransferEntity> huifuList = transferDao.getListByPage(tableSpec,
                accountDate.toDate(),
                batchIds,
                TransferType.REPAYMENT.getType());

        if (CollectionUtils.isEmpty(huifuList)) {
            return Lists.newArrayList("");
        }

        return CommonUtil.transform2Lines(huifuList);
    }

    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate accountDate = context.getAttach(ShortDate.class);

        return transferDao.getAllTransferIds(tableSpec,
                accountDate.toDate(),
                TransferType.REPAYMENT.getType());
    }
}
