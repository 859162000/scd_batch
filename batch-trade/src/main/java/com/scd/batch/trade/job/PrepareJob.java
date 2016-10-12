package com.scd.batch.trade.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.service.daycut.SwitchService;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class PrepareJob extends AbstractExecutor {

    @Resource
    private SwitchService switchService;
    @Resource
    private JobControlService jobControlService;

    /**
     * 待初始化的任务集合
     */
    private final Set<JobType> jobTypes = new HashSet<>();

    /**
     * 交易相关-分库个数
     */
    private final int databaseNum;

    /**
     * 交易相关-每个库中分表个数
     */
    private final int tableNum;
    
    /**
     * 账务相关-分库个数
     */
    private final int accgDatabaseNum;

    /**
     * 账务相关-每个库中分表个数
     */
    private final int accgTableNum;

    /**
     * Constructor with databaseNum & tableNum
     */
    public PrepareJob(int databaseNum, int tableNum, int accgDatabaseNum, int accgTableNum) {
        this.databaseNum = databaseNum;
        this.tableNum = tableNum;
        this.accgDatabaseNum = accgDatabaseNum;
        this.accgTableNum = accgTableNum;
    }

    /**
     * Spring entity init
     */
    public void init() {
        jobTypes.add(JobType.LOAN);
        jobTypes.add(JobType.LOAN_EXPEDITE);
        jobTypes.add(JobType.LOAN_OLD_RECONCILIATION);
        jobTypes.add(JobType.LOAN_RECONCILIATION);
        jobTypes.add(JobType.LOAN_AUTO_REPAY);
        
        // 账务相关任务
        jobTypes.add(JobType.ACCOUNTING);
        jobTypes.add(JobType.ACCG_REQ_PROVISION);
        jobTypes.add(JobType.ACCG_REQ_TRANSACTION);
        jobTypes.add(JobType.ACCG_REQ_BATCH_PENALTY);
    }

    @Override
    public void execute(ExecutorContext context) {
        ShortDate accountDate = switchService.currentAccountDate();
        logger.info("prepare job, accountDate: {}", accountDate);

        for (JobType jobType : jobTypes) {
            int databaseNum = (jobType == JobType.ACCOUNTING ? this.accgDatabaseNum : this.databaseNum);
            int tableNum = (jobType == JobType.ACCOUNTING ? this.accgTableNum : this.tableNum);
            
            createJobControl(jobType, accountDate.toDate(), databaseNum, tableNum);
        }
    }

    @Override
    public void handleException(ExecutorContext context, Throwable t) {
        logger.error("job execute error, job name: {}, context: {}, exception: {}",
                getName(), context, ExceptionUtils.getStackTrace(t));

        throw new RuntimeException(t);
    }

    /**
     * 为取模方式的任务(CustomerId % tableNum), 创建JobControl
     */
    private void createJobControl(JobType jobType, Date accountDate, int databaseNum, int tableNum) {
        int controlCount = databaseNum * tableNum;

        List<JobControl> controlList = new ArrayList<>(controlCount);
        for (int i = 0; i < controlCount; i++) {
            JobControl control = buildJobControl(jobType, accountDate, i % databaseNum + "");
            control.setTableId(i % tableNum + "");
            controlList.add(control);
        }

        jobControlService.insertList(controlList);
    }

    /**
     * 组装JobControl通用属性
     */
    private JobControl buildJobControl(JobType jobType, Date accountDate, String databaseId) {
        JobControl control = new JobControl();

        control.setPhase(PhaseType.LOAD.type);
        control.setPhaseStatus(PhaseStatus.INIT.type);
        control.setJobType(jobType.getType());
        control.setUuid(null);
        control.setRetryTime(0);
        control.setCheckPoint(0);
        control.setAccountDate(accountDate);
        control.setDatabaseId(databaseId);

        return control;
    }

}
