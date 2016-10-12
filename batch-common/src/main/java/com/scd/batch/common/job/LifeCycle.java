package com.scd.batch.common.job;

/**
 * {@link LifeCycle} represents the component lifecycle
 * <p>
 * Created by chenguoqing01 on 16/4/25.
 */
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
