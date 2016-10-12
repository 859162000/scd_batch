package com.scd.batch.common.job.batch;

import java.util.List;

@FunctionalInterface
public interface DataFlowCalculator {

    /**
     * Calculate with the source data lines. Lambda supported
     * 
     * @return calculated data
     */
    List<String> calculate(List<String> sourceDataLines);

}
