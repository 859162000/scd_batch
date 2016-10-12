package com.scd.batch.common.job;

import com.scd.batch.common.utils.concurrent.NamingThreadFactory;
import com.scd.batch.common.job.executor.Executor;
import com.scd.batch.common.job.executor.ExecutorContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by pippo on 16/4/27.
 */
public class Job {

    public Job() {
    }

    public Job(String name) {
        this.name = name;
    }

    public ExecutorContext run() throws Throwable {

        ExecutorContext context = new ExecutorContext();

        if (parallel) {
            serial(context);
        } else {
            parallel(context);
        }

        return context;
    }

    protected void serial(ExecutorContext context) throws InterruptedException {
        for (Executor executor : executors) {
            executor.start(context);
            executor.waitForComplete();
        }
    }

    protected void parallel(final ExecutorContext context) throws
            InterruptedException,
            ExecutionException,
            TimeoutException {
        Collection<Callable<Void>> callables = new HashSet<>();
        for (final Executor executor : executors) {
            callables.add(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    executor.start(context);
                    executor.waitForComplete();
                    return null;
                }
            });
        }

        List<Future<Void>> futures = Executors.newFixedThreadPool(currency,
                new NamingThreadFactory("job." + name, false)).invokeAll(callables);
        for (Future<Void> future : futures) {
            future.get(timeout, TimeUnit.MILLISECONDS);
        }
    }

    private String name;
    private boolean parallel = false;
    private int currency = 1;
    private long timeout = Long.MAX_VALUE;
    private List<Executor> executors = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setExecutors(List<Executor> executors) {
        this.executors = executors;
    }
}
