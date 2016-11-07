package com.scd.batch.executor;

import javax.annotation.Resource;

import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.google.common.base.Preconditions;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class RetryExecutor extends AbstractExecutor {

    private static final String RETRY_REMAIN_COUNT = "RETRY_REMAIN_COUNT";

    @Resource
    private SwitchService switchService;

    /**
     * 重试次数
     */
    private int retryCount = 3;

    @Override
    public boolean beforeExecute(ExecutorContext context) {
        // super.beforeExecute(context) always return true
        if (! super.beforeExecute(context)) {
            return false;
        }

        if (context.getAttach(RETRY_REMAIN_COUNT) == null) {
            context.addAttach(RETRY_REMAIN_COUNT, retryCount);
        }

        int retryRemainCount = context.getAttach(RETRY_REMAIN_COUNT);
        if (retryRemainCount <= 0) {
            return false;
        }

        context.addAttach(RETRY_REMAIN_COUNT, retryRemainCount - 1);

        ShortDate accountDate = switchService.currentAccountDate();
        context.addAttach(ShortDate.class, accountDate);

        initRetry(context);

        return true;
    }

    @Override
    public void handleException(ExecutorContext context, Throwable e) {
        logger.error("job execute error, job desc: {}, context: {}, error: {}",
                getName(), context, ExceptionUtils.getStackTrace(e));
    }

    protected abstract void initRetry(ExecutorContext context);

    public void setRetryCount(int retryCount) {
        Preconditions.checkArgument(retryCount > 0, "retryCount must be greater than zero");
        this.retryCount = retryCount;
    }
}
