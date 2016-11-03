package com.scd.batch.common.job.batch;

import com.google.common.base.Preconditions;
import com.scd.batch.common.entity.reconciliation.TransferErrorBase;
import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.constants.SourceType;
import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.job.util.BatchFileUtils;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 步骤：1、网络爬取数据Crawler，或者从数据库加载数据Loader；
 * 2、核心对账逻辑实现；
 * 3、结果回写数据库，对账完成状态和差错数据。
 */
public abstract class ReconciliationBatchJob extends AbstractExecutor {

    /**
     * Constanst context attach key
     */
    protected static final String ATTACH_KEY_ROOT_FILE_DIR = "ROOT_FILE_DIR";
    protected static final String ATTACH_KEY_SOURCE_SCD_FILE_NAME = "SOURCE_SCD_FILE_NAME";
    protected static final String ATTACH_KEY_SOURCE_HUIFU_FILE_NAME = "SOURCE_HUIFU_FILE_NAME";
    protected static final String ATTACH_KEY_TARGET_FILE_NAME = "TARGET_FILE_NAME";

    private String rootPath;
    private String sourceSCDNamePrefix;
    private String sourceHuiFuNamePrefix;
    private String targetNamePrefix;
    /**
     * Spring properties of data flow<br>
     * file root path, source name, target name, batch size
     */
    private int batchSize = Pagination.DEFAULT_PAGE_SIZE;

    /**
     * Data flow: Source provider & Target hadler
     */
    private SourceDataProvider scdSourcesDataProvider;
    private SourceDataProvider huiFuSourcesDataProvider;
    private ReconliationCalculator reconliationCalculator;
    private TargetDataHandler targetReconcliationHandler;

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
            List<String> huiFuSourceLines = huiFuSourcesDataProvider.batchRead();
            List<String> scdSourceLines = scdSourcesDataProvider.batchRead();

            ConcurrentHashMap<String, TransferErrorBase> transferRepo = new ConcurrentHashMap<>();

            while (CollectionUtils.isNotEmpty(scdSourceLines) || CollectionUtils.isNotEmpty(huiFuSourceLines)) {

                // 3. forward source read cursor
                forwardSourceCursor(context);
                // 2. data calculate
                if (CollectionUtils.isNotEmpty(huiFuSourceLines)) {
                    reconliationCalculator.calculate(SourceType.HUIFU, huiFuSourceLines, transferRepo);
                    huiFuSourceLines = huiFuSourcesDataProvider.batchRead();
                }

                if (CollectionUtils.isNotEmpty(scdSourceLines)) {
                    reconliationCalculator.calculate(SourceType.SCD, scdSourceLines, transferRepo);
                    scdSourceLines = scdSourcesDataProvider.batchRead();
                }

            }

            List<String> result = new ArrayList<>();
            transferRepo.forEach((k, v) -> {
                result.add(k + "|" + JsonUtils.toJson(v));
            });

            // 4. post handler after all, such as make MD5 file
            targetReconcliationHandler.handle(result);

        } catch (Exception e) {
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
        logger.error("job execute error, job name: {}, context: {}, error: {}",
                getName(), context, ExceptionUtils.getStackTrace(e));

        // close data flow
        clearDataFlow();

        JobControl control = context.getAttach(JobControl.class);
        jobControlService.updatePhaseStatus(control.getUuid(), getCurrentPhase(), PhaseStatus.FAIL);
    }

    /**
     * Get a provider instance of {@link SourceFileProvider}
     *
     * @return
     */
    protected SourceDataProvider getSourceFileProvider(SourceType sourceType) {
        File sourceDir = getExecutorContext().getAttach(ATTACH_KEY_ROOT_FILE_DIR);

        String sourceFileName = getExecutorContext().getAttach
                (sourceType == sourceType.SCD ? ATTACH_KEY_SOURCE_SCD_FILE_NAME : ATTACH_KEY_SOURCE_HUIFU_FILE_NAME);

        return new SourceFileProvider(sourceDir, sourceFileName, batchSize);
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

        if (StringUtils.isNotBlank(sourceSCDNamePrefix)) {
            String sourceFileName = BatchFileUtils.getFileName(sourceSCDNamePrefix, control);
            context.addAttach(ATTACH_KEY_SOURCE_SCD_FILE_NAME, sourceFileName);
        }

        if (StringUtils.isNotBlank(sourceHuiFuNamePrefix)) {
            String sourceFileName = BatchFileUtils.getFileName(sourceHuiFuNamePrefix, control);
            context.addAttach(ATTACH_KEY_SOURCE_HUIFU_FILE_NAME, sourceFileName);
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
        reconliationCalculator = getReconliationCalculator();
        scdSourcesDataProvider = getScdSourcesDataProvider();
        huiFuSourcesDataProvider = getHuiFuSourcesDataProvider();
        targetReconcliationHandler = getTargetReconciliationHandler();

        scdSourcesDataProvider.init();
        huiFuSourcesDataProvider.init();
        targetReconcliationHandler.init();

        // Remove existed target data
        targetReconcliationHandler.clear();
    }

    /**
     * Clear data flow of SourceDataProvider & TargetDataHandler
     */
    private void clearDataFlow() {
        targetReconcliationHandler.close();
        scdSourcesDataProvider.close();
        huiFuSourcesDataProvider.close();
    }

    /**
     * Move to next pagination
     */
    private void forwardSourceCursor(ExecutorContext context) {
        Pagination pagination = context.getAttach(Pagination.class);
        pagination.setCurPage(pagination.getCurPage() + 1);
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
    protected abstract SourceDataProvider getScdSourcesDataProvider();

    protected abstract SourceDataProvider getHuiFuSourcesDataProvider();


    /**
     * Get an no operation calculator
     *
     * @return
     */
    protected abstract ReconliationCalculator getReconliationCalculator();

    /**
     * Get {@link TargetDataHandler}
     *
     * @return
     */
    protected abstract TargetDataHandler getTargetReconciliationHandler();

    protected TargetDataHandler getTargetFileHandler() {
        File targetDir = getExecutorContext().getAttach(ATTACH_KEY_ROOT_FILE_DIR);
        String targetFileName = getExecutorContext().getAttach(ATTACH_KEY_TARGET_FILE_NAME);

        return new TargetFileWriteHandler(targetDir, targetFileName);
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

    public void setSourceSCDNamePrefix(String sourceSCDNamePrefix) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sourceSCDNamePrefix), "missed sourceSCDNamePrefix");
        this.sourceSCDNamePrefix = sourceSCDNamePrefix;
    }

    public void setSourceHuiFuNamePrefix(String sourceHuiFuNamePrefix) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sourceHuiFuNamePrefix), "missed sourceHuiFuNamePrefix");
        this.sourceHuiFuNamePrefix = sourceHuiFuNamePrefix;
    }

    public void setTargetNamePrefix(String targetNamePrefix) {
        Preconditions.checkArgument(StringUtils.isNotBlank(targetNamePrefix), "missed targetNamePrefix");
        this.targetNamePrefix = StringUtils.trim(targetNamePrefix);
    }

}
