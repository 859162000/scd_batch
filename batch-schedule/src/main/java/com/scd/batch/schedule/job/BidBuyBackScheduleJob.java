package com.scd.batch.schedule.job;


import com.miaoqian.bid.api.trade.scheduler.TradeScheduleService;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.job.batch.ScheduleCalculator;
import com.scd.batch.common.job.batch.ScheduleJob;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * 批量回购操作
 */
public class BidBuybackScheduleJob extends ScheduleJob {

    @Resource
    private TradeScheduleService tradeScheduleService;

    @Override
    protected JobType getJobType() {
        return JobType.BidBuybackScheduleJob;
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
    protected ScheduleCalculator getScheduleCalculator(ExecutorContext context) {
        return (p) -> schedule(context);
    }

    public String schedule(ExecutorContext context) {

        Result<String> result = tradeScheduleService.buyback();

        logger.info("result:" + result);
        if (!result.isSuccess()) {
            logger.info("buyback failed!, " + result.getCode() + "," + result.getMessage());
        }

        return null;
    }

    @Override
    public List<Long> getAllIdList(ExecutorContext context) {
        return null;
    }

}
