package com.scd.batch.common.job.executor;

import com.scd.batch.common.job.LifeCycle;

/**
 * {@link Executor} represents a executable component
 */
public interface Executor extends LifeCycle {

    String getName();

    boolean isCycleRunning();
    
    ExecutorContext getExecutorContext();

    void start(ExecutorContext context);
    
    boolean beforeExecute(ExecutorContext context);

    void execute(ExecutorContext context);

    void afterExecute(ExecutorContext context);

    void handleException(ExecutorContext context, Throwable t);

    void waitForComplete() throws InterruptedException;

}
