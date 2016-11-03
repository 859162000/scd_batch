package com.scd.batch.statistics.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scd.batch.common.constant.bid.ProjectStatus;
import com.scd.batch.common.dao.bid.BorrowerRelationDao;
import com.scd.batch.common.dao.bid.ProjectDao;
import com.scd.batch.common.dao.bid.RepayPlanDao;
import com.scd.batch.common.dao.bid.RepayRealDao;
import com.scd.batch.common.dao.statistics.BorrowerRepayPlanStatDao;
import com.scd.batch.common.entity.statistics.BorrowerRepayPlanStatEntity;
import com.scd.batch.common.entity.statistics.bid.BorrowerRepayEntity;
import com.scd.batch.common.entity.statistics.bid.ProjectBorrowerEntity;
import com.scd.batch.common.entity.statistics.bid.SimpleProjectEntity;
import com.scd.batch.common.job.batch.StatisticsCalculateJob;
import com.scd.batch.common.job.batch.StatisticsCalculator;
import com.scd.batch.common.job.batch.TargetStatisticsHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.statistics.service.BorrowerRepayPlanStatService;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 融资方还款计划任务
 */
public class BorrowerRepayPlanStatCalculateJob extends StatisticsCalculateJob {

    @Resource
    private ProjectDao projectDao;

    @Resource
    private RepayPlanDao repayPlanDao;

    @Resource
    private RepayRealDao repayRealDao;

    @Resource
    private BorrowerRelationDao borrowerRelationDao;

    @Resource
    private BorrowerRepayPlanStatDao borrowerRepayPlanStatDao;

    @Resource
    private BorrowerRepayPlanStatService statBatService;

    @Override
    protected JobType getJobType() {
        return JobType.BorrowerRepayPlanStatCalculateJob;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.LOAD;
    }

    @Override
    protected PhaseType getNextPhase() {
        return null;
    }

    @Override
    protected TargetStatisticsHandler getTargetStatisticsHandler() {
        return this::update2DB;
    }

    @Override
    protected StatisticsCalculator getStatisticsCalculator() {
        return () -> batchQueryDB(getExecutorContext());
    }

    /**
     * Closure: batch query DB
     */
    public String batchQueryDB(ExecutorContext context) {

        // 批量获取账号 ID
        List<Long> batchProjectId = getBatchIdList(context);

        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate accountDate = context.getAttach(ShortDate.class);

        // 项目列表
        List<SimpleProjectEntity> projectList = projectDao.getProjectListById(tableSpec,
                batchProjectId,
                ProjectStatus.getValidStatusList());
        if (projectList == null || projectList.isEmpty()) {
            logger.info("projectList isEmpty!");
            return null;
        }

        // 项目编码列表
        List<String> projectCodeList = projectList.stream().map(p -> p.getProjectCode()).collect(Collectors.toList());

        // 按照项目期序统计项目还款金额
        List<BorrowerRepayEntity> repayPlanList = repayPlanDao.getProjectRepayPlanListByTerm(tableSpec,
                projectCodeList);
        if(repayPlanList == null || repayPlanList.isEmpty()){
            logger.info("repayPlanList isEmpty!");
            return null;
        }

        // 按照项目期序统计项目实际还款金额
        List<BorrowerRepayEntity> repayRealList = repayRealDao.getProjectRealRepayListByTerm(tableSpec,
                projectCodeList);
        if(repayRealList == null || repayRealList.isEmpty()){
            logger.info("repayRealList isEmpty!");
            return null;
        }

        // 借款人项目关系列表
        List<ProjectBorrowerEntity> borrowerEntityList = borrowerRelationDao.getRelationByProjectIdList(tableSpec,
                projectCodeList);
        if(borrowerEntityList == null || borrowerEntityList.isEmpty()){
            logger.info("borrowerEntityList isEmpty!");
            return null;
        }

        // 借款人还款计划统计
        List<BorrowerRepayPlanStatEntity> planStatEntityList = buildRepayPlan(projectList, repayPlanList,
                repayRealList, borrowerEntityList);

        return JsonUtils.toJson(planStatEntityList);
    }

    @Override
    public List<Long> getAllIdList(ExecutorContext context) {

        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        return projectDao.getAllIdList(tableSpec, ProjectStatus.getValidStatusList());
    }

    /**
     * 批量更新到数据库
     */
    public void update2DB(String line) {

        List<BorrowerRepayPlanStatEntity> entityList = JsonUtils.toBean(line,
                new TypeReference<List<BorrowerRepayPlanStatEntity>>() {
                });

        // 不存在则先写入
        statBatService.batchUpdate2DB(entityList);
    }


    /**
     */
    private List<BorrowerRepayPlanStatEntity> buildRepayPlan(List<SimpleProjectEntity> projectList,
                                                             List<BorrowerRepayEntity> repayPlanList,
                                                             List<BorrowerRepayEntity> repayRealList,
                                                             List<ProjectBorrowerEntity> borrowerEntityList) {

        ConcurrentHashMap<String, SimpleProjectEntity> projectMap = new ConcurrentHashMap<>();
        projectList.forEach(p -> projectMap.put(p.getProjectCode(), p));

        ConcurrentHashMap<String, BorrowerRepayEntity> repayPlanMap = new ConcurrentHashMap<>();
        repayPlanList.forEach(p -> repayPlanMap.put(p.getProjectCode() + "," + p.getRepayTerm(), p));

        ConcurrentHashMap<String, BorrowerRepayEntity> repayRealMap = new ConcurrentHashMap<>();
        repayRealList.forEach(p -> repayRealMap.put(p.getProjectCode() + "," + p.getRepayTerm(), p));

        ConcurrentHashMap<String, ProjectBorrowerEntity> borrowerMap = new ConcurrentHashMap<>();
        borrowerEntityList.forEach(p -> {
            borrowerMap.put(p.getProjectCode(), p);
        });

        List<BorrowerRepayPlanStatEntity> planStatEntityList = new ArrayList<>();

        repayPlanList.forEach(p -> {
            BorrowerRepayPlanStatEntity planStatEntity = new BorrowerRepayPlanStatEntity(
                    // 到期日
                    p.getRepayDate(),
                    // 融资方ID
                    borrowerMap.get(p.getProjectCode()).getBorrowerId(),
                    // 融资方名称
                    borrowerMap.get(p.getProjectCode()).getBorrowerName(),
                    p.getProjectCode(),
                    projectMap.get(p.getProjectCode()).getProjectName(),
                    projectMap.get(p.getProjectCode()).getRepayType(),
                    // 到期本金
                    p.getRepayAmount(),
                    // 到期利息
                    p.getRepayInterest(),
                    // 到期本息合计
                    p.getRepayAmount() + p.getRepayInterest(),
                    // 实际还款本金
                    repayRealMap.get(p.getProjectCode() + "," + p.getRepayTerm()).getRepayAmount(),
                    // 实际还款利息
                    repayRealMap.get(p.getProjectCode() + "," + p.getRepayTerm()).getRepayInterest(),
                    // 实际还款汇总
                    repayRealMap.get(p.getProjectCode() + "," + p.getRepayTerm()).getRepayAmount() + repayRealMap.get
                            (p.getProjectCode() + "," + p.getRepayTerm()).getRepayInterest());

            planStatEntityList.add(planStatEntity);
        });

        return planStatEntityList;
    }

}
