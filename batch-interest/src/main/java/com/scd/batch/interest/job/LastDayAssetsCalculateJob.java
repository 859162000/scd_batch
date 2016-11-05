package com.scd.batch.interest.job;

import com.scd.batch.common.dao.trade.UserBalanceDao;
import com.scd.batch.common.job.batch.StatisticsCalculateJob;
import com.scd.batch.common.job.batch.StatisticsCalculator;
import com.scd.batch.common.job.batch.TargetStatisticsHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.interest.entity.UserProfitEntity;
import com.scd.batch.interest.service.LastDayAssetsCalculateService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * 昨日总资产
 */
public class LastDayAssetsCalculateJob extends StatisticsCalculateJob {

    @Resource
    private LastDayAssetsCalculateService calculateService;

    @Autowired
    private UserBalanceDao balanceDao;

    @Override
    protected JobType getJobType() {
        return JobType.LastDayAssetsCalculateJob;
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

        List<Long> batchIdList = getBatchIdList(context);

        List<UserProfitEntity> profitEntityList = calculateService.calculateProfit(tableSpec,
                accountDate.toDate(),
                batchIdList);

        return JsonUtils.toJson(profitEntityList);
    }

    /**
     *
     */
    public void update2DB(String line) {
    }


    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        return balanceDao.getAllIds(tableSpec);
    }


}
