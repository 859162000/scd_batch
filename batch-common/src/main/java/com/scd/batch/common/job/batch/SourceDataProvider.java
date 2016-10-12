package com.scd.batch.common.job.batch;

import java.util.List;

@FunctionalInterface
public interface SourceDataProvider {

    /**
     * Initialize provider
     */
    default void init() {
    }

    /**
     * Close provider
     */
    default void close() {
    }

    /**
     * Batch read from the provider. Lambda supported
     * 
     * @return
     */
    List<String> batchRead();

}
