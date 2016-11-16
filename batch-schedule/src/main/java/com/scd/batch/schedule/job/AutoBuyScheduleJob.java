package com.scd.batch.schedule.job;


import com.miaoqian.bid.api.trade.scheduler.TradeScheduleService;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.job.batch.ScheduleCalculator;
import com.scd.batch.common.job.batch.ScheduleJob;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.Settings;
import com.scd.batch.schedule.notice.NoticeUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * 批量认购
 */
public class AutoBuyScheduleJob extends ScheduleJob {

    @Resource
    private TradeScheduleService tradeScheduleService;

    @Override
    protected JobType getJobType() {
        return JobType.AutoBuyScheduleJob;
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

        Result<String> result = tradeScheduleService.processAutoBuy();
        logger.info("result:" + result);

        if (!result.isSuccess()) {
            logger.info("loan failed!, " + result.getCode() + "," + result.getMessage());
            return null;
        }

        int retry = Settings.getInstance().getAutoBuyRetry();

        NoticeUtil noticeUtil = new NoticeUtil();
        noticeUtil.wait4Notice(retry,
                Settings.getInstance().getAutoBuyName(),
                Settings.getInstance().getAutoBuyTimeout());

        return null;
    }

    @Override
    public List<Long> getAllIdList(ExecutorContext context) {
        return null;
    }

}
