package com.scd.batch.common.job.batch;

import com.scd.batch.common.job.executor.ExecutorContext;

@FunctionalInterface
public interface ScheduleCalculator {

    /**
     * Calculate with the source data lines. Lambda supported
     *
     * @return calculated data
     */
    String schedule(ExecutorContext context);

}
