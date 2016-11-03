package com.scd.batch.executor.job;

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
import com.scd.batch.common.utils.IdGenUtil;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.service.daycut.SwitchService;
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
//    private final int statisticsDatabaseNum;

    /**
     * 账务相关-每个库中分表个数
     */
//    private final int statisticsTableNum;

    /**
     * Constructor with databaseNum & tableNum
     */
    public PrepareJob(int databaseNum, int tableNum, int statisticsDatabaseNum, int statisticsTableNum) {
        this.databaseNum = databaseNum;
        this.tableNum = tableNum;
//        this.statisticsDatabaseNum = statisticsDatabaseNum;
//        this.statisticsTableNum = statisticsTableNum;
    }

    /**
     * Spring entity init
     */
    public void init() {

        // 灵活调度批跑


        // 对账相关
        jobTypes.add(JobType.LoanCrawlerJob);
        jobTypes.add(JobType.LoanCalculatorJob);

        jobTypes.add(JobType.PaymentCrawlerJob);
        jobTypes.add(JobType.PaymentCalculatorJob);

        jobTypes.add(JobType.CashCrawlerJob);
        jobTypes.add(JobType.CashCalculatorJob);

        jobTypes.add(JobType.TrfCrawlerJob);
        jobTypes.add(JobType.TrfCalculatorJob);

        jobTypes.add(JobType.SaveCrawlerJob);
        jobTypes.add(JobType.SaveCalculatorJob);

        // 统计相关
        jobTypes.add(JobType.ProjectLimitCalculateJob);
        jobTypes.add(JobType.BorrowerRepayPlanStatCalculateJob);
        jobTypes.add(JobType.FundStatCalculateJob);
        jobTypes.add(JobType.FixPlanDueStatCalculateJob);
        jobTypes.add(JobType.FixProjectDueStatCalculateJob);
        jobTypes.add(JobType.ExpenditureCalculateJob);
        jobTypes.add(JobType.AssetsStatProjectCalculateJob);
        jobTypes.add(JobType.AssetsStatBalanceCalculateJob);

        jobTypes.add(JobType.UserProfitCalculateJob);


    }

    @Override
    public void execute(ExecutorContext context) {
        ShortDate accountDate = switchService.currentAccountDate();
        logger.info("prepare job, accountDate: {}", accountDate);

        for (JobType jobType : jobTypes) {
//            int databaseNum = (jobType == JobType.ACCOUNTING ? this.statisticsDatabaseNum : this.databaseNum);
//            int tableNum = (jobType == JobType.ACCOUNTING ? this.statisticsTableNum : this.tableNum);

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
    public JobControl buildJobControl(JobType jobType, Date accountDate, String databaseId) {
        JobControl control = new JobControl();

        control.setPhase(PhaseType.LOAD.type);
        control.setPhaseStatus(PhaseStatus.INIT.type);
        control.setJobType(jobType.getType());

        control.setUuid(String.valueOf(IdGenUtil.get().nextId()));
        control.setRetryTime(0);
        control.setCheckPoint(0);
        control.setAccountDate(accountDate);
        control.setDatabaseId(databaseId);

        return control;
    }

}
