package com.scd.batch.executor.mock;


import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;

public class LoopExecutor extends AbstractExecutor {

    // private static Logger logger = LoggerFactory.getLogger(LoopExecutor.class);

    public LoopExecutor() {
    }

    public LoopExecutor(String name) {
        super(name);
    }

    public LoopExecutor(String name, boolean cycleRunning) {
        super(name, cycleRunning);
    }

    @Override
    public void initialize() {

    }

    @Override
    public boolean beforeExecute(ExecutorContext context) {
        String name = getName() + ".loop";
        Integer loop = context.getAttach(name);
        if (loop == null) {
            loop = 0;
        }

        logger.debug("before execute:[{}], the loop count is:[{}]", getName(), loop);

        loop += 1;
        context.addAttach(name, loop);

        return loop <= 10;
    }

    @Override
    public void execute(ExecutorContext context) {
        Integer loop = context.getAttach(getName() + ".loop");
        logger.debug("execute:[{}], the loop count is:[{}]", getName(), loop);
    }

    @Override
    public void afterExecute(ExecutorContext context) {
        Integer loop = context.getAttach(getName() + ".loop");
        logger.debug("after execute:[{}], the loop count is:[{}]", getName(), loop);
    }

}
