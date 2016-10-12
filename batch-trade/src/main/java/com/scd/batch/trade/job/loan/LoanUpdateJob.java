package com.scd.batch.trade.job.loan;

import com.scd.batch.common.job.batch.DataFlowBatchJob;
import com.scd.batch.common.job.batch.DataFlowCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.common.CommonUtil;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.service.loan.LoanUpdateControlService;

import java.util.List;

import javax.annotation.Resource;

public class LoanUpdateJob extends DataFlowBatchJob {

    @Resource
    private LoanUpdateControlService loanUpdateControlService;

    @Override
    protected JobType getJobType() {
        return JobType.LOAN;
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
    protected SourceDataProvider getSourceDataProvider() {
        // Read local source file
        return getSourceFileProvider();
    }

    @Override
    protected DataFlowCalculator getDataFlowCalculator() {
        // Calculate with no operations
        return getNoOpCalculator();
    }

    @Override
    protected TargetDataHandler getTargetDataHandler() {
        // Save target to DB
        return this::updateInfo;
    }

    private void updateInfo(List<String> resultLines) {
        // Get remain list from context
        ExecutorContext context = getExecutorContext();

        // Handle each lines(batch size)
        List<LoanInfo> loanInfoList = CommonUtil.transform2Bean(resultLines, LoanInfo.class);

        loanUpdateControlService.update(loanInfoList, context.getAttach(ShortDate.class));
    }
}
