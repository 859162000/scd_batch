package com.scd.batch.common.job.executor;

import java.util.ArrayList;
import java.util.List;

/**
 * 链式 executor
 */
public abstract class AbstractChainedExecutor extends AbstractExecutor {

    public AbstractChainedExecutor() {
    }

    public AbstractChainedExecutor(String name) {
        super(name);
    }

    public AbstractChainedExecutor(String name, boolean cycleRunning) {
        super(name, cycleRunning);
    }

    @Override
    public final void initialize() {
        chain.forEach(Executor::initialize);
    }

    @Override
    public final void start(ExecutorContext context) {
        if (context == null) {
            context = new ExecutorContext();
        }

        try {
            for (Executor executor : chain) {
                executor.start(context);

                try {
                    executor.waitForComplete();
                } catch (InterruptedException e) {
                    context.setStatus(ExecutorContext.Status.error);
                    handleException(context, e);
                    // 如果出错那么不继续执行
                    break;
                }
            }
        } finally {
            blocking.release();
        }
    }

    protected List<Executor> chain = new ArrayList<>();

    public List<Executor> getChain() {
        return chain;
    }

    public void setChain(List<Executor> chain) {
        this.chain = chain;
    }
}
