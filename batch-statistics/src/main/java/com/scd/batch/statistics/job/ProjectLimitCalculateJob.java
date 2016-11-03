package com.scd.batch.statistics.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scd.batch.common.constant.bid.ProjectStatus;
import com.scd.batch.common.dao.bid.ProjectDao;
import com.scd.batch.common.dao.bid.RepayPlanDao;
import com.scd.batch.common.dao.bid.RepayRealDao;
import com.scd.batch.common.entity.statistics.ProjectLimitEntity;
import com.scd.batch.common.entity.statistics.bid.RepayPlanStatEntity;
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
import com.scd.batch.statistics.service.ProjectLimitService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProjectLimitCalculateJob extends StatisticsCalculateJob {

    @Resource
    private ProjectLimitService projectLimitService;

    @Resource
    private ProjectDao projectDao;

    @Resource
    private RepayPlanDao repayPlanDao;

    @Resource
    private RepayRealDao repayRealDao;

    @Override
    protected JobType getJobType() {
        return JobType.ProjectLimitCalculateJob;
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
            logger.info("projectList isEmpty");
            return null;
        }

        List<String> projectCodeList = projectList.stream().map(p -> p.getProjectCode()).collect(Collectors.toList());


        // 项目还款计划汇总
        List<RepayPlanStatEntity> repayPlanList = repayPlanDao.getProjectLimitList(tableSpec, projectCodeList);
        if (repayPlanList == null || repayPlanList.isEmpty()) {
            logger.info("repayPlanList isEmpty");
            return null;
        }

        // 项目实际还款汇总
        List<RepayPlanStatEntity> repayRealList = repayRealDao.getProjectLimitList(tableSpec, projectCodeList);
        if (repayRealList == null || repayRealList.isEmpty()) {
            logger.info("repayRealList isEmpty");
            return null;
        }

        List<ProjectLimitEntity> projectLimitEntityList = buildProjectLimit(projectList, repayPlanList, repayRealList);

        return JsonUtils.toJson(projectLimitEntityList);
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
        List<ProjectLimitEntity> projectLimitEntityList = JsonUtils.toBean(line,
                new TypeReference<List<ProjectLimitEntity>>() {
                });

        // 存在则更新，不存在则先写入
        projectLimitService.batchUpdate2DB(projectLimitEntityList);
    }


    /**
     * 构建项目额度数据
     *
     * @param projectList
     * @param repayPlanList
     * @param repayRealList
     * @return
     */
    private List<ProjectLimitEntity> buildProjectLimit(List<SimpleProjectEntity> projectList, List<RepayPlanStatEntity>
            repayPlanList,
                                                       List<RepayPlanStatEntity> repayRealList) {

        ConcurrentHashMap<String, RepayPlanStatEntity> repayPlanMap = new ConcurrentHashMap<>();
        repayPlanList.forEach(p -> repayPlanMap.put(p.getProjectCode(), p));

        ConcurrentHashMap<String, RepayPlanStatEntity> repayRealMap = new ConcurrentHashMap<>();
        repayRealList.forEach(p -> repayRealMap.put(p.getProjectCode(), p));

        List<ProjectLimitEntity> projectLimitEntityList = new ArrayList<>();

        projectList.forEach(p -> {

            double paidPrincipal = repayRealMap.get(p.getProjectCode()) == null ? 0.0 : repayRealMap.get(p
                    .getProjectCode()).getRepayAmount();
            double unpaidPrincipal = (repayPlanMap.get(p.getProjectCode()) == null ? 0.0 : repayPlanMap.get(p
                    .getProjectCode()).getRepayAmount()) -
                    (repayRealMap.get(p.getProjectCode()) == null ? 0.0 : repayRealMap.get(p.getProjectCode())
                            .getRepayAmount());
            double paidInterest = repayRealMap.get(p.getProjectCode()) == null ? 0.0 : repayRealMap.get(p
                    .getProjectCode()).getRepayInterest();
            double unpaidInterest = (repayPlanMap.get(p.getProjectCode()) == null ? 0.0 : repayPlanMap.get(p
                    .getProjectCode()).getRepayInterest()) -
                    (repayRealMap.get(p.getProjectCode()) == null ? 0.0 : repayRealMap.get(p.getProjectCode())
                            .getRepayInterest());

            ProjectLimitEntity projectLimitEntity = new ProjectLimitEntity(
                    p.getProjectCode(),
                    p.getProjectName(),
                    // 总额度
                    p.getBidAmount(),
                    // 已发标额度，但是回购不恢复这个字段
                    p.getOccupyAmount(),
                    // 可用发标额度
                    p.getBidAmount() - p.getOccupyAmount(),

                    // TODO 已提现金额，目前取不到
                    0,
                    // 已还本金
                    paidPrincipal,
                    // 未还本金
                    unpaidPrincipal,
                    // 已还利息
                    paidInterest,
                    // 未还利息
                    unpaidInterest);
            projectLimitEntityList.add(projectLimitEntity);
        });

        return projectLimitEntityList;
    }

}
