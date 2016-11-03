package com.scd.batch.common.job.batch;

import com.scd.batch.common.entity.reconciliation.TransferErrorBase;
import com.scd.batch.common.job.constants.SourceType;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@FunctionalInterface
public interface ReconliationCalculator {

    /**
     * Calculate with the source data lines. Lambda supported
     */
    void calculate(SourceType sourceType,
                   List<String> sourceDataLines,
                   ConcurrentHashMap<String, TransferErrorBase> repo);

}
