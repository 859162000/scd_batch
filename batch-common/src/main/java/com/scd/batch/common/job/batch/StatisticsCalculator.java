package com.scd.batch.common.job.batch;

@FunctionalInterface
public interface StatisticsCalculator {

    /**
     * Calculate with the source data lines. Lambda supported
     *
     * @return calculated data
     */
    String calculate();

}
