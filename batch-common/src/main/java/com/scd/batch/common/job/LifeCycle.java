package com.scd.batch.common.job;

public interface LifeCycle {

    /**
     * Initialize the component
     */
    void initialize();

    /**
     * Start the componenet
     */
    void start();

    /**
     * Shutdown the component
     */
    void shutdown();
}
