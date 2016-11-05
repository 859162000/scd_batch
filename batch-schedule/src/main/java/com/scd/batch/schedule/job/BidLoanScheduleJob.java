package com.scd.batch.schedule.job;


import com.miaoqian.bid.api.trade.scheduler.TradeScheduleService;
import com.miaoqian.framework.domain.Result;
import com.miaoqian.transvc.api.business.Business;
import com.scd.batch.common.job.batch.ScheduleCalculator;
import com.scd.batch.common.job.batch.ScheduleJob;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;

import javax.annotation.Resource;
import java.util.List;

/**
 * 批量放款操作
 */
public class BidLoanScheduleJob extends ScheduleJob {

    @Resource
    private TradeScheduleService tradeScheduleService;

    @Override
    protected JobType getJobType() {
        return JobType.BidLoanScheduleJob;
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

        Result<String> result = tradeScheduleService.loan();
        logger.info("result:" + result);

        if (!result.isSuccess()) {
            logger.info("loan failed!, " + result.getCode() + "," + result.getMessage());
        }

        return null;
    }

    @Override
    public List<Long> getAllIdList(ExecutorContext context) {
        return null;
    }

}
