package com.scd.batch.statistics.job;

import com.scd.batch.common.dao.statistics.AssetsStatDao;
import com.scd.batch.common.dao.trade.UserBalanceDao;
import com.scd.batch.common.entity.statistics.AssetsStatEntity;
import com.scd.batch.common.entity.statistics.trade.BalanceAssetsEntity;
import com.scd.batch.common.job.batch.StatisticsCalculateJob;
import com.scd.batch.common.job.batch.StatisticsCalculator;
import com.scd.batch.common.job.batch.TargetStatisticsHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.statistics.service.AssetsStatService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户余额按日统计任务
 */
public class AssetsStatBalanceCalculateJob extends StatisticsCalculateJob {

    @Resource
    private AssetsStatDao assetsStatDao;

    @Resource
    private UserBalanceDao userBalanceDao;

    @Resource
    private AssetsStatService statService;

    @Override
    protected JobType getJobType() {
        return JobType.AssetsStatBalanceCalculateJob;
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

        BalanceAssetsEntity balanceAssetsEntity = userBalanceDao.selectBalanceAssets(tableSpec);
        if (balanceAssetsEntity == null) {
            logger.info("balanceAssetsEntity is null");
            return null;
        }
        balanceAssetsEntity.setTransDate(accountDate.toDate());

        return JsonUtils.toJson(balanceAssetsEntity);
    }

    /**
     * 统计结果增量方式，更新到数据库
     */
    public void update2DB(String line) {
        BalanceAssetsEntity balanceAssetsEntity = JsonUtils.toBean(line, BalanceAssetsEntity.class);

        AssetsStatEntity assetsStatEntity = new AssetsStatEntity(balanceAssetsEntity.getTransDate()
                , 0, 0, 0,
                balanceAssetsEntity.getAmount(),
                balanceAssetsEntity.getFrozon(),
                balanceAssetsEntity.getSum());

        // 增量更新数据库，不存在则先写入
        statService.updateIncreament2DB(assetsStatEntity);
    }

}
