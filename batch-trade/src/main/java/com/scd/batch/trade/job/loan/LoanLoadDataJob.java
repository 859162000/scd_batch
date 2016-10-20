package com.scd.batch.trade.job.loan;

import java.util.List;

import javax.annotation.Resource;

import com.scd.batch.common.job.batch.DataFlowBatchJob;
import com.scd.batch.common.job.batch.DataFlowCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.trade.common.LoanUtil;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.model.loan.LoanInstallmentInfo;
import com.scd.batch.trade.service.loan.LoanService;
import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;

public class LoanLoadDataJob extends DataFlowBatchJob {

    @Resource
    private LoanService loanService;

    @Override
    protected JobType getJobType() {
        return JobType.LOAN;
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
    private List<String> batchQueryDB(ExecutorContext context) {
        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        ShortDate accountDate = context.getAttach(ShortDate.class);

        // 批量获取账号 ID
        List<Long> batchLoanId = getBatchIdList(context);

        // 数据已全部查出
        if (batchLoanId == null) {
            return null;
        }

        List<LoanInstallmentInfo> batch = loanService.getInstallmentInfo(tableSpec, batchLoanId, accountDate);

        List<LoanInfo> loanInfoList = LoanUtil.transform2LoanInfo(batch);

        if (CollectionUtils.isEmpty(loanInfoList)) {
            return Lists.newArrayList("");
        }

        return CommonUtil.transform2Lines(loanInfoList);
    }

    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate accountDate = context.getAttach(ShortDate.class);

        return loanService.getAllOverdueLoanId(tableSpec, accountDate);
    }
}
