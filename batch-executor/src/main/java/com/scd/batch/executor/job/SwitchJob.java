package com.scd.batch.executor.job;

import javax.annotation.Resource;

import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class SwitchJob extends AbstractExecutor {

    @Resource
    private SwitchService switchService;

    @Override
    public void execute(ExecutorContext context) {

        ShortDate old = switchService.currentAccountDate();
        switchService.cutAccountDate(old);
        ShortDate accountDate =  switchService.currentAccountDate();

        logger.info("old account date: {}, new account date: {}", old, accountDate);
    }

    @Override
    public void handleException(ExecutorContext context, Throwable t) {
        logger.error("job execute error, job name: {}, context: {}, exception: {}",
                getName(), context, ExceptionUtils.getStackTrace(t));

        throw new RuntimeException(t);
    }
}
