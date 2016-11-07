package com.scd.batch.common.job.batch;

import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.job.util.BatchFileUtils;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;


public abstract class DataFlowBatchJob extends AbstractExecutor {

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
    private String rootPath;
    private String sourceNamePrefix;
    private String targetNamePrefix;
    private int batchSize = Pagination.DEFAULT_PAGE_SIZE;

    /**
     * Data flow: Source provider & Target hadler
     */
    private SourceDataProvider sourceDataProvider;
    private DataFlowCalculator dataFlowCalculator;
    private TargetDataHandler targetDataHandler;

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
        initDataFlow();

        return true;
    }

    @Override
    public void execute(ExecutorContext context) {
        try {
            // 1. data input from source provider
            List<String> sourceLines = sourceDataProvider.batchRead();

            while (CollectionUtils.isNotEmpty(sourceLines)) {
                // 2. data calculate
                List<String> resultLines = dataFlowCalculator.calculate(sourceLines);

                // 3. data output to target handler
                targetDataHandler.handle(resultLines);

                // 4. forward source read cursor
                forwardSourceCursor(context);
                sourceLines = sourceDataProvider.batchRead();
            }

            // 5. post handler after all, such as make MD5 file
            targetDataHandler.postHandler();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterExecute(ExecutorContext context) {
        // clear data flow
        clearDataFlow();

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

        // close data flow
        clearDataFlow();

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
     * Get {@link SourceDataProvider}
     *
     * @return
     */
    protected abstract SourceDataProvider getSourceDataProvider();

    /**
     * Get {@link TargetDataHandler}
     *
     * @return
     */
    protected abstract TargetDataHandler getTargetDataHandler();

    /**
     * Get {@link DataFlowCalculator}
     *
     * @return
     */
    protected abstract DataFlowCalculator getDataFlowCalculator();

    /**
     * Get a handler instance of {@link TargetFileWriteHandler}
     *
     * @return
     */
    protected TargetDataHandler getTargetFileHandler() {
        File targetDir = getExecutorContext().getAttach(ATTACH_KEY_ROOT_FILE_DIR);
        String targetFileName = getExecutorContext().getAttach(ATTACH_KEY_TARGET_FILE_NAME);

        return new TargetFileWriteHandler(targetDir, targetFileName);
    }

    /**
     * Get a provider instance of {@link SourceFileProvider}
     *
     * @return
     */
    protected SourceDataProvider getSourceFileProvider() {
        File sourceDir = getExecutorContext().getAttach(ATTACH_KEY_ROOT_FILE_DIR);
        String sourceFileName = getExecutorContext().getAttach(ATTACH_KEY_SOURCE_FILE_NAME);

        return new SourceFileProvider(sourceDir, sourceFileName, batchSize);
    }

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

        // Set source & target info
        File parentDir = new File(BatchFileUtils.getDirPath(rootPath, context));
        context.addAttach(ATTACH_KEY_ROOT_FILE_DIR, parentDir);

        if (StringUtils.isNotBlank(sourceNamePrefix)) {
            String sourceFileName = BatchFileUtils.getFileName(sourceNamePrefix, control);
            context.addAttach(ATTACH_KEY_SOURCE_FILE_NAME, sourceFileName);
        }

        if (StringUtils.isNotBlank(targetNamePrefix)) {
            String targetFileName = BatchFileUtils.getFileName(targetNamePrefix, control);
            context.addAttach(ATTACH_KEY_TARGET_FILE_NAME, targetFileName);
        }
    }

    /**
     * Init data flow
     */
    private void initDataFlow() {
        // init source provider & calculate & target handler
        dataFlowCalculator = getDataFlowCalculator();
        sourceDataProvider = getSourceDataProvider();
        targetDataHandler = getTargetDataHandler();

        sourceDataProvider.init();
        targetDataHandler.init();

        // Remove existed target data
        targetDataHandler.clear();
    }

    /**
     * Clear data flow of SourceDataProvider & TargetDataHandler
     */
    private void clearDataFlow() {
        targetDataHandler.close();
        sourceDataProvider.close();
    }

    /**
     * Move to next pagination
     */
    private void forwardSourceCursor(ExecutorContext context) {
        Pagination pagination = context.getAttach(Pagination.class);
        pagination.setCurPage(pagination.getCurPage() + 1);
    }

    /**
     * Setters for spring entity
     */
    public void setRootPath(String rootPath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(rootPath), "missed rootPath");
        this.rootPath = StringUtils.trim(rootPath);
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize > 0 ? batchSize : Pagination.DEFAULT_PAGE_SIZE;
    }

    public void setSourceNamePrefix(String sourceNamePrefix) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sourceNamePrefix), "missed sourceNamePrefix");
        this.sourceNamePrefix = StringUtils.trim(sourceNamePrefix);
    }

    public void setTargetNamePrefix(String targetNamePrefix) {
        Preconditions.checkArgument(StringUtils.isNotBlank(targetNamePrefix), "missed targetNamePrefix");
        this.targetNamePrefix = StringUtils.trim(targetNamePrefix);
    }

}
