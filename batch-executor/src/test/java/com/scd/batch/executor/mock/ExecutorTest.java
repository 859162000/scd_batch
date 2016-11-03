package com.scd.batch.executor.mock;

import com.scd.batch.common.job.Job;
import com.scd.batch.common.job.executor.Executor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExecutorTest {

    @Test
    public void loop() throws InterruptedException {

        LoopExecutor executor = new LoopExecutor();
        executor.setName("loop");
        executor.setCycleRunning(true);

        executor.start();
        executor.waitForComplete();
    }

    @Test
    public void chain() throws InterruptedException {
        ChainExecutor executor = new ChainExecutor("chain");
        executor.getChain().add(new LoopExecutor("step.1", true));
        executor.getChain().add(new SubExecutor("stop.2"));
        executor.getChain().add(new LoopExecutor("step.3", true));

        executor.start();
        executor.waitForComplete();
    }

    @Test
    public void jobSerial() throws Throwable {
        List<Executor> executors = new ArrayList<>();
        executors.add(new LoopExecutor("loop", true));

        ChainExecutor chain = new ChainExecutor("chain");
        chain.getChain().add(new LoopExecutor("step.1", true));
        chain.getChain().add(new SubExecutor("stop.2"));
        chain.getChain().add(new LoopExecutor("step.3", true));
        executors.add(chain);

        Job job = new Job();
        job.setName("job1");
        job.setParallel(false);
        job.setExecutors(executors);

        job.run();
    }

    @Test
    public void jobParallel() throws Throwable {
        List<Executor> executors = new ArrayList<>();
        executors.add(new LoopExecutor("loop", true));

        ChainExecutor chain = new ChainExecutor("chain");
        chain.getChain().add(new LoopExecutor("step.1", true));
        chain.getChain().add(new SubExecutor("stop.2"));
        chain.getChain().add(new LoopExecutor("step.3", true));
        executors.add(chain);

        Job job = new Job();
        job.setName("job1");
        job.setParallel(true);
        job.setCurrency(2);
        job.setExecutors(executors);

        job.run();
    }
}
