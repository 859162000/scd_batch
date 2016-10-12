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
import com.google.common.collect.Lists;
import com.scd.batch.trade.common.CommonUtil;
import com.scd.batch.trade.model.loan.ExpediteInfo;
import com.scd.batch.trade.service.loan.LoanExpediteService;
import org.apache.commons.collections.CollectionUtils;

public class LoanExpediteJob extends DataFlowBatchJob {

    @Resource
    private LoanExpediteService loanExpediteService;
    
    @Override
    protected JobType getJobType() {
        return JobType.LOAN_EXPEDITE;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.LOAD;
    }

    @Override
    protected PhaseType getNextPhase() {
        return null;
    }

    @Override
    protected SourceDataProvider getSourceDataProvider() {
        return () -> batchQueryDB(getExecutorContext());
    }

    @Override
    protected TargetDataHandler getTargetDataHandler() {
        // Target save to local file
        return getTargetFileHandler();
    }

    @Override
    protected DataFlowCalculator getDataFlowCalculator() {
        // No data calculate
        return getNoOpCalculator();
    }

    /**
     * Closure: batch query DB
     */
    private List<String> batchQueryDB(ExecutorContext context) {
        ShortDate accountDate = context.getAttach(ShortDate.class);
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        List<Long> installmentIdList = getBatchIdList(context);
        if (installmentIdList == null) {
            return null;
        }
        List<ExpediteInfo> expediteInfos = loanExpediteService.getExpedite(tableSpec, accountDate, installmentIdList);
        // 为空时为了继续查询
        if (CollectionUtils.isEmpty(expediteInfos)) {
            return Lists.newArrayList("");
        }
        return CommonUtil.joinerFormBean(expediteInfos);
        
    }

    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        return loanExpediteService.getAllLoanId(tableSpec);
    }
}
