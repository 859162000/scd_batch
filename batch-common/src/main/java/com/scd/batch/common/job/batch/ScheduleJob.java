package com.scd.batch.common.job.batch;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.Settings;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


public abstract class ScheduleJob extends AbstractExecutor {

    /**
     * Constanst context attach key
     */
    protected static final String ATTACH_KEY_ROOT_FILE_DIR = "ROOT_FILE_DIR";
    protected static final String ATTACH_KEY_SOURCE_FILE_NAME = "SOURCE_FILE_NAME";
    protected static final String ATTACH_KEY_TARGET_FILE_NAME = "TARGET_FILE_NAME";

    /**
     * Spring properties of data flow<br>
     * file root path, source desc, target desc, batch size
     */
    private int batchSize = Pagination.DEFAULT_PAGE_SIZE;

    /**
     * Data flow: Source provider & Target hadler
     */
    private ScheduleCalculator scheduleCalculator;

    @Resource
    protected JobControlService jobControlService;

    @Override
    public boolean beforeExecute(ExecutorContext context) {
        // super.beforeExecute(context) always return true
        if (!super.beforeExecute(context)) {
            return false;
        }

        // Grab free job control
        JobControl control = jobControlService.grabJobControl(context, getJobType(), getCurrentPhase());
        if (control == null) {
            return false;
        }

        // Init context & data flow
        initContext(context, control, batchSize);
        initDataFlow(context);

        return true;
    }

    @Override
    public void execute(ExecutorContext context) {
        try {

            // 1. data calculate
            String resultLine = scheduleCalculator.schedule(context);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterExecute(ExecutorContext context) {

        PhaseType phaseType = getNextPhase();
        PhaseStatus phaseStatus = PhaseStatus.INIT;

        // Current phase equals with Next phase, means we get the end
        if (getNextPhase() == null) {
            phaseType = getCurrentPhase();
            phaseStatus = PhaseStatus.DONE;
        }

        JobControl control = context.getAttach(JobControl.class);
        jobControlService.updatePhaseStatus(control.getUuid(), phaseType, phaseStatus);
    }

    @Override
    public void handleException(ExecutorContext context, Throwable e) {
        logger.error("job execute error, job desc: {}, context: {}, error: {}",
                getName(), context, ExceptionUtils.getStackTrace(e));

        JobControl control = context.getAttach(JobControl.class);
        jobControlService.updatePhaseStatus(control.getUuid(), getCurrentPhase(), PhaseStatus.FAIL);
    }

    /**
     * Get {@link JobType}
     *
     * @return
     */
    protected abstract JobType getJobType();

    /**
     * Get current {@link PhaseType}
     *
     * @return
     */
    protected abstract PhaseType getCurrentPhase();

    /**
     * Get next {@link PhaseType}
     *
     * @return
     */
    protected abstract PhaseType getNextPhase();

    /**
     * Get {@link DataFlowCalculator}
     *
     * @return
     */
    protected abstract ScheduleCalculator getScheduleCalculator(ExecutorContext context);

    /**
     * Get an no operation calculator
     *
     * @return
     */
    protected DataFlowCalculator getNoOpCalculator() {
        return (sourceDataLines) -> (sourceDataLines);
    }

    /**
     * Init ExecutorContext
     */
    private void initContext(ExecutorContext context, JobControl control, int batchSize) {
        // Remove temp param
        context.removeAttach(ATTACH_KEY_TEMP_PARAM);

        // Set pagination info
        Pagination pagination = new Pagination();
        pagination.setPageSize(batchSize);

        context.addAttach(Pagination.class, pagination);
    }

    /**
     * Init data flow
     */
    private void initDataFlow(ExecutorContext context) {
        // init source provider & calculate & target handler
        scheduleCalculator = getScheduleCalculator(context);

    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

}
