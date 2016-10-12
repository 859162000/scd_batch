package com.scd.batch.trade.job.loan;

import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;
import com.google.common.base.Preconditions;
import com.scd.batch.trade.RetryExecutor;
import com.scd.batch.trade.common.FailureStatus;
import com.scd.batch.trade.service.FailureRecordService;
import com.scd.batch.trade.service.loan.LoanService;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class LoanRetryFailedJob extends RetryExecutor {
    @Resource
    private FailureRecordService failureRecordService;
    @Resource
    private LoanService loanService;

    /**
     * 分页大小
     */
    private int batchSize = Pagination.DEFAULT_PAGE_SIZE;

    @Override
    public void initRetry(ExecutorContext context) {
        Pagination pagination = new Pagination();
        pagination.setPageSize(batchSize);
        context.addAttach(Pagination.class, pagination);
    }

    @Override
    public void execute(ExecutorContext context) {
        ShortDate accountDate = context.getAttach(ShortDate.class);

        Pagination pagination = context.getAttach(Pagination.class);

        List<Long> batchId;

    }

    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        ShortDate accountDate = context.getAttach(ShortDate.class);
        return failureRecordService.getAllFailedId(accountDate);
    }

    @Override
    protected void afterProcess(ExecutorContext context) {
        ShortDate accountDate = context.getAttach(ShortDate.class);
        List<Long> loanIdList = failureRecordService.getFailureIdList(accountDate, FailureStatus.INIT);
        if (CollectionUtils.isNotEmpty(loanIdList)) {
            failureRecordService.update2succ(accountDate, FailureStatus.INIT);
        }
    }

    public void setBatchSize(int batchSize) {
        Preconditions.checkArgument(batchSize > 0, "batchSize MUST be positive");
        this.batchSize = batchSize;
    }
}
