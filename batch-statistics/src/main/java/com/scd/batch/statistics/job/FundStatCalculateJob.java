package com.scd.batch.statistics.job;

import com.scd.batch.common.entity.statistics.FundStatEntity;
import com.scd.batch.common.job.batch.StatisticsCalculateJob;
import com.scd.batch.common.job.batch.StatisticsCalculator;
import com.scd.batch.common.job.batch.TargetStatisticsHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.statistics.service.FundStatService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 每日充值提现统计任务
 */
public class FundStatCalculateJob extends StatisticsCalculateJob {

    @Resource
    private FundStatService fundStatService;

    @Override
    protected JobType getJobType() {
        return JobType.FundStatCalculateJob;
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

        FundStatEntity fundStatEntity = fundStatService.calculate(accountDate, tableSpec);

        return JsonUtils.toJson(fundStatEntity);
    }

    /**
     * 统计结果增量方式，更新到数据库
     */
    public void update2DB(String line) {
        FundStatEntity fundStatEntity = JsonUtils.toBean(line, FundStatEntity.class);
        fundStatService.update2DB(fundStatEntity);
    }

}
