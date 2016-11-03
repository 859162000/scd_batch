package com.scd.batch.interest.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.dao.bid.CreditorRelationDao;
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
import com.scd.batch.interest.service.UserProfitCalculateService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每日收益统计
 */
public class UserProfitCalculateJob extends StatisticsCalculateJob {

    @Resource
    private UserProfitCalculateService calculateService;

    @Resource
    private CreditorRelationDao relationDao;

    @Override
    protected JobType getJobType() {
        return JobType.UserProfitCalculateJob;
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
     * 统计结果增量方式，更新到数据库
     */
    public void update2DB(String line) {
        List<UserProfitEntity> profitEntityList = JsonUtils.toBean(line, new TypeReference<List<UserProfitEntity>>() {
        });

        calculateService.update2DB(profitEntityList);
    }


    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        return relationDao.getAllIdList(tableSpec);
    }


}
