package com.scd.batch.common.job.executor;

import com.scd.batch.common.utils.Pagination;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * {@link AbstractExecutor} represents a abstract implementation
 */
public abstract class AbstractExecutor implements Executor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String ATTACH_KEY_TEMP_PARAM = "TEMP_PARAM";

    private static final String TRACE_KEY = "_TRACE_KEY";
    
    /** Executor name */
    protected String name;
    /** Whether cycle execute  */
    protected boolean cycleRunning;

    /** Execute blocking semaphore */ 
    protected transient Semaphore blocking = new Semaphore(0);
    /** Blocking timeout unit ms  */
    protected long timeout = Long.MAX_VALUE;
    
    /** Executor context */
    private ExecutorContext context;

    /**
     * Constructors
     */
    public AbstractExecutor() {
    }

    public AbstractExecutor(String name) {
        this.name = name;
    }

    public AbstractExecutor(String name, boolean cycleRunning) {
        this.name = name;
        this.cycleRunning = cycleRunning;
    }

    @Override
    public void start() {
        start(new ExecutorContext());
    }

    @Override
    public void start(ExecutorContext context) {
        MDC.put(TRACE_KEY, ThreadLocalRandom.current().nextLong() + "");

        Stopwatch stopwatch = Stopwatch.createStarted();

        // Set executor context
        if (context == null) {
            context = new ExecutorContext();
        }
        this.context = context;

        try {
            logger.info("start job: {}", getName());
            beforeProcess(context);
            process(context);
            afterProcess(context);
            logger.info("job: {} finished, takes: {}", getName(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } finally {
            MDC.remove(TRACE_KEY);

            blocking.release();
        }
    }

    /**
     * Process as below:<p>
     * beforeExecute -> execute -> afterExecute -> handleException<br>
     * If is cycle running and no exception occurs, the process well repeat until beforeExecute return false
     */
    protected final void process(ExecutorContext context) {
        do {
            try {
                if (!beforeExecute(context)) {
                    logger.info("cancel process executor:[{}] with context:[{}], because of before execute is false",
                            this, context);
                    context.setStatus(ExecutorContext.Status.cancel);
                    break;
                }

                execute(context);
                afterExecute(context);
                context.setStatus(ExecutorContext.Status.success);
                
            } catch (Throwable throwable) {
                context.setStatus(ExecutorContext.Status.error);
                handleException(context, throwable);
                
                // Exception occurs, break the process
                break;
            }
            
        } while (isCycleRunning());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void shutdown() {
    }

    /**
     * You'd better call super.beforeExecute if a subclass overwrites this method.
     */
    @Override
    public boolean beforeExecute(ExecutorContext context) {
        // Remove temp param
        context.removeAttach(ATTACH_KEY_TEMP_PARAM);

        return true;
    }

    @Override
    public void afterExecute(ExecutorContext context) {
    }

    @Override
    public void handleException(ExecutorContext context, Throwable t) {
        logger.error("process executor:[{}] with context:[{}] due to error:[{}]", this, context,
                ExceptionUtils.getStackTrace(t));
    }

    @Override
    public void waitForComplete() throws InterruptedException {
        try {
            blocking.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error("executor:[{}] execute timeout", getName());
            throw e;
        }
    }
    
    @Override
    public ExecutorContext getExecutorContext() {
        return this.context;
    }
    
    /**
     * Getters & Setters
     */
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isCycleRunning() {
        return cycleRunning;
    }

    public void setCycleRunning(boolean cycleRunning) {
        this.cycleRunning = cycleRunning;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("name", name)
                .append("cycleRunning", cycleRunning).toString();
    }

    protected final List<Long> getBatchIdList(ExecutorContext context) {
        if (context.getAttach(ATTACH_KEY_TEMP_PARAM) == null) {
            List<Long> allId = getAllIdList(context);
            if (allId == null) {
                allId = new ArrayList<>();
            }
            context.addAttach(ATTACH_KEY_TEMP_PARAM, allId);
            logger.info("getBatchIdList size: {}", allId.size());
        }

        List<Long> allId = context.getAttach(ATTACH_KEY_TEMP_PARAM);

        final int accountNumber = allId.size();

        // 取得分页数据
        final Pagination pagination = context.getAttach(Pagination.class);

        final int startInclusive = pagination.getStartIndex();

        final int endExclusive = Math.min(startInclusive + pagination.getPageSize(), accountNumber);

        if (startInclusive >= accountNumber) {
            return null;
        }

        return allId.subList(startInclusive, endExclusive);

    }

    protected List<Long> getAllIdList(ExecutorContext context) {
        throw new UnsupportedOperationException("you need override this method");
    }

    protected void beforeProcess(ExecutorContext context) {

    }

    protected void afterProcess(ExecutorContext context) {

    }

}
