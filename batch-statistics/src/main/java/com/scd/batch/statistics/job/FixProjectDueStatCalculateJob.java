package com.scd.batch.statistics.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scd.batch.common.dao.bid.CreditorRelationDao;
import com.scd.batch.common.dao.statistics.FixProjectDueStatDao;
import com.scd.batch.common.entity.statistics.FixDueStatEntity;
import com.scd.batch.common.entity.statistics.bid.FixProductDuePlanEntity;
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
public class FixProjectDueStatCalculateJob extends StatisticsCalculateJob {

    @Resource
    private FixProjectDueStatDao fixProjectDueStatDao;

    @Resource
    private CreditorRelationDao relationDao;

    @Resource
    private FixProjectDueStatService statService;

    @Override
    protected JobType getJobType() {
        return JobType.FixProjectDueStatCalculateJob;
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

        List<FixProductDuePlanEntity> fixProductDuePlanEntityList = relationDao.selectFixProjectDueStatList(tableSpec);
        if(fixProductDuePlanEntityList == null || fixProductDuePlanEntityList.isEmpty()) {
            logger.info("fixProductDuePlanEntityList isEmpty!");
            return null;
        }

        List<FixDueStatEntity> fixProductDueStatEntityList = buildDueStatEntity(fixProductDuePlanEntityList);

        return JsonUtils.toJson(fixProductDueStatEntityList);
    }

    /**
     * 批量更新到数据库
     */
    public void update2DB(String line) {
        List<FixDueStatEntity> statEntityList = JsonUtils.toBean(line,
                new TypeReference<List<FixDueStatEntity>>() {
                });

        statService.batchUpdate2DB(statEntityList);
    }

    public List<FixDueStatEntity> buildDueStatEntity(List<FixProductDuePlanEntity> fixProductDuePlanEntityList) {

        List<FixDueStatEntity> dueStatEntityList = new ArrayList<>();

        fixProductDuePlanEntityList.forEach(p -> {
            FixDueStatEntity dueStatEntity = new FixDueStatEntity(p.getTransDate(),
                    0,
                    0,
                    p.getFixProjectPrincipal(),
                    p.getFixProjectInterest(),
                    p.getFixProjectPrincipal() + p.getFixProjectInterest());

            dueStatEntityList.add(dueStatEntity);
        });

        return dueStatEntityList;
    }

}
