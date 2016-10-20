package com.scd.batch.common.job.batch;

import com.scd.batch.common.job.constants.SourceType;

import java.util.List;

@FunctionalInterface
public interface ReconliationCalculator {

    /**
     * Calculate with the source data lines. Lambda supported
     */
    void calculate(SourceType sourceType, List<String> sourceDataLines, TransferRepo repo);

}
