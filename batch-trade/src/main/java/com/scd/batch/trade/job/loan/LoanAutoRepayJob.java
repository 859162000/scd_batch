package com.scd.batch.trade.job.loan;

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
import com.scd.batch.trade.model.loan.AutoRepayInfo;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.service.loan.LoanAutoRepayService;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class LoanAutoRepayJob extends DataFlowBatchJob {
    @Resource
    private LoanAutoRepayService loanAutoRepayService;

    private List<String> batchQueryDB(ExecutorContext context) {
        TableSpec ts = context.getAttach(TableSpec.class);

        ShortDate accountDate = context.getAttach(ShortDate.class);

        // 批量获取客户 ID
        List<Long> batchCustomerId = getBatchIdList(context);

        // 数据已全部查出
        if (batchCustomerId == null) {
            return null;
        }

        // 已经按 customerId, loanId 有序
        List<InstallmentInfo> batch = loanAutoRepayService.getInstallmentsByCustomerId(
                ts, batchCustomerId, accountDate);

        if (CollectionUtils.isEmpty(batch)) {
            return Lists.newArrayList("");
        }

        Map<Long, List<AutoRepayInfo>> customer2loan = loanAutoRepayService.groupAndSort(batch, accountDate);

        List<AutoRepayInfo> result = loanAutoRepayService.mergeMapValue(customer2loan);

        return CommonUtil.joinerFormBean(result);
    }



    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate accountDate = context.getAttach(ShortDate.class);

        return loanAutoRepayService.getAllOverdueCustomerId(tableSpec, accountDate);
    }

    @Override
    protected JobType getJobType() {
        return JobType.LOAN_AUTO_REPAY;
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
        return getTargetFileHandler();
    }

    @Override
    protected DataFlowCalculator getDataFlowCalculator() {
        return getNoOpCalculator();
    }
}
