package com.scd.batch.statistics.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scd.batch.common.dao.bid.CreditorRelationDao;
import com.scd.batch.common.dao.statistics.FixProjectDueStatDao;
import com.scd.batch.common.entity.statistics.FixDueStatEntity;
import com.scd.batch.common.entity.statistics.bid.FixPlanDuePlanEntity;
import com.scd.batch.common.job.batch.StatisticsCalculateJob;
import com.scd.batch.common.job.batch.StatisticsCalculator;
import com.scd.batch.common.job.batch.TargetStatisticsHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.statistics.service.FixProjectDueStatService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 定期产品还款到期统计任务
 */
public class FixPlanDueStatCalculateJob extends StatisticsCalculateJob {

    @Resource
    private FixProjectDueStatService statService;

    @Resource
    private CreditorRelationDao relationDao;


    @Override
    protected JobType getJobType() {
        return JobType.FixPlanDueStatCalculateJob;
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

        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate accountDate = context.getAttach(ShortDate.class);

        List<FixPlanDuePlanEntity> fixPlanDuePlanEntityList = relationDao.selectFixPlanDueStatList(tableSpec);
        if (fixPlanDuePlanEntityList == null || fixPlanDuePlanEntityList.isEmpty()) {
            logger.info("fixPlanDuePlanEntityList isEmpty");
            return null;
        }

        List<FixDueStatEntity> fixProductDueStatEntityList = buildDueStatEntity(fixPlanDuePlanEntityList);

        return JsonUtils.toJson(fixProductDueStatEntityList);
    }

    /**
     * 批量更新到数据库
     */
    public void update2DB(String line) {
        List<FixDueStatEntity> fixProductDueStatEntityList = JsonUtils.toBean(line,
                new TypeReference<List<FixDueStatEntity>>() {
                });

        // 不存在则先写入
        statService.batchUpdate2DB(fixProductDueStatEntityList);
    }

    public List<FixDueStatEntity> buildDueStatEntity(List<FixPlanDuePlanEntity> fixPlanDuePlanEntityList) {

        List<FixDueStatEntity> dueStatEntityList = new ArrayList<>();

        fixPlanDuePlanEntityList.forEach(p -> {
            FixDueStatEntity dueStatEntity = new FixDueStatEntity(p.getTransDate(),
                    p.getFixPlanPrincipal(),
                    p.getFixPlanInterest(),
                    0,
                    0,
                    p.getFixPlanPrincipal() + p.getFixPlanInterest());

            dueStatEntityList.add(dueStatEntity);
        });

        return dueStatEntityList;
    }

}
