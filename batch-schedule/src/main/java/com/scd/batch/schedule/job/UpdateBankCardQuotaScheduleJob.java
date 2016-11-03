package com.scd.batch.schedule.job;


import com.miaoqian.framework.domain.Result;
import com.miaoqian.usersvc.api.outuser.OutUserWrite;
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
 * 更新银行卡限额
 */
public class UpdateBankCardQuotaScheduleJob extends ScheduleJob {

    @Resource
    private OutUserWrite outUserWrite;

    @Override
    protected JobType getJobType() {
        return JobType.UpdateBankCardQuotaScheduleJob;
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

    /**
     * Closure: batch query DB
     */
    public String schedule(ExecutorContext context) {
        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        ShortDate accountDate = context.getAttach(ShortDate.class);

        List<Long> batchIdList = getBatchIdList(context);

        Result result = outUserWrite.updateBankCardQuota();

        if (!result.isSuccess()) {
            logger.info("updateBankCardQuota failed!, " + result.getCode() + "," + result.getMessage());
        }

        return null;
    }


    @Override
    public List<Long> getAllIdList(ExecutorContext context) {
        return null;
    }

}
