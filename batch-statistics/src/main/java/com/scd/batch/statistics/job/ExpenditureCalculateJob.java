package com.scd.batch.statistics.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scd.batch.common.dao.promotion.CouponsUseRecordsDao;
import com.scd.batch.common.dao.statistics.ExpenditureStatDao;
import com.scd.batch.common.entity.statistics.ExpenditureStatEntity;
import com.scd.batch.common.job.batch.StatisticsCalculateJob;
import com.scd.batch.common.job.batch.StatisticsCalculator;
import com.scd.batch.common.job.batch.TargetStatisticsHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.statistics.service.ExpenditureStatService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 平台支出报表任务
 */
public class ExpenditureCalculateJob extends StatisticsCalculateJob {

    @Resource
    private CouponsUseRecordsDao couponsUseRecordsDao;

    @Resource
    private ExpenditureStatService statService;

    @Override
    protected JobType getJobType() {
        return JobType.ExpenditureCalculateJob;
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

        List<ExpenditureStatEntity> entityList = couponsUseRecordsDao.selectStatList(tableSpec,
                accountDate.addDays(-1).toDate(),
                accountDate.toDate());

        if (entityList == null || entityList.isEmpty()) {
            logger.info("entityList isEmpty");
            return null;
        }

        return JsonUtils.toJson(entityList);
    }

    /**
     * 批量更新到数据库
     */
    public void update2DB(String line) {
        List<ExpenditureStatEntity> entityList = JsonUtils.toBean(line,
                new TypeReference<List<ExpenditureStatEntity>>() {
                });

        // 不存在则先写入
        statService.batchUpdate2DB(entityList);
    }

}
