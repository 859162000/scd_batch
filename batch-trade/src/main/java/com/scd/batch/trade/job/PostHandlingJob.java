package com.scd.batch.trade.job;

import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostHandlingJob extends AbstractExecutor {
    private static final String OK = "OK";

    @Resource
    private JobControlService jobControlService;
    @Resource
    private DayCutService dayCutService;

    @Override
    public void execute(ExecutorContext context) {
        ShortDate accountDate = ShortDate.valueOf(dayCutService.load());
        List<JobControl> jobs = jobControlService.getAllJobs(accountDate, jobType);
        jobs.forEach(job -> {
            if (job.getPhaseStatus() != PhaseStatus.DONE.type) {
                logger.error("some jobs execute failed: {}", JsonUtils.toJson(job));
                throw new RuntimeException("some jobs execute failed");
            }
        });

        File file = new File(rootPath, accountDate.toString() + File.separator + OK);

        try {
            file.getParentFile().mkdirs();
            Files.touch(file);
        } catch (Exception e) {
            try {
                TimeUnit.SECONDS.sleep(5);
                file.getParentFile().mkdirs();
                Files.touch(file);
            } catch (Exception e2) {
                logger.error("touch file exception, file: {}, e: {}", file, ExceptionUtils.getStackTrace(e2));
                throw new RuntimeException("touch file exception");
            }
        }
    }

    private JobType jobType;

    private String rootPath;

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public void setRootPath(String rootPath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(rootPath), "missed rootPath");
        this.rootPath = rootPath;
    }
}
