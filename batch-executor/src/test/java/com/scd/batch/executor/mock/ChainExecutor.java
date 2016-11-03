package com.scd.batch.executor.mock;


import com.scd.batch.common.job.executor.AbstractChainedExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;

public class ChainExecutor extends AbstractChainedExecutor {

    // private static Logger logger = LoggerFactory.getLogger(ChainExecutor.class);

    public ChainExecutor() {
    }

    public ChainExecutor(String name) {
        super(name);
    }

    public ChainExecutor(String name, boolean cycleRunning) {
        super(name, cycleRunning);
    }

    @Override
    public boolean beforeExecute(ExecutorContext context) {
        logger.debug("before execute:[{}]", getName());
        return true;
    }

    @Override
    public void execute(ExecutorContext context) {
        logger.debug("execute:[{}]", getName());

    }

    @Override
    public void afterExecute(ExecutorContext context) {
        logger.debug("after execute:[{}]", getName());
    }
}
