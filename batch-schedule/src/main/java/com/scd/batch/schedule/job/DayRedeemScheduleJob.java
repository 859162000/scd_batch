package com.scd.batch.schedule.job;


import com.miaoqian.framework.domain.Result;
import com.miaoqian.transvc.api.business.Business;
import com.scd.batch.common.job.batch.ScheduleCalculator;
import com.scd.batch.common.job.batch.ScheduleJob;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.Settings;
import com.scd.batch.schedule.notice.NoticeUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * 批量赎回
 */
public class DayRedeemScheduleJob  extends AbstractExecutor {

    @Resource
    private Business business;

    @Override
    public List<Long> getAllIdList(ExecutorContext context) {
        return null;
    }

    @Override
    public void execute(ExecutorContext context) {

        Result<String> result = business.redeemBatch();
        logger.info("result:" + result);

        if (!result.isSuccess()) {
            logger.info("loan failed!, " + result.getCode() + "," + result.getMessage());
        }

        int retry = Settings.getInstance().getRedeemRetry();
        logger.info("name:" + Settings.getInstance().getBidLoanName() +
                ", retry:" + retry +
                ", timeout:" + Settings.getInstance().getRedeemTimeout());

        NoticeUtil noticeUtil = new NoticeUtil();
        noticeUtil.wait4Notice(retry,
                Settings.getInstance().getRedeemName(),
                Settings.getInstance().getRedeemTimeout());

    }
}
