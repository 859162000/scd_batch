package com.scd.batch.schedule.notice;

import com.miaoqian.framework.domain.Result;
import com.miaoqian.framework.domain.ResultBuilder;
import com.scd.batch.api.entity.ScheduleNoticeReq;
import com.scd.batch.api.notice.NoticeFacade;
import com.scd.batch.common.utils.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ScheduleNoticeImpl implements NoticeFacade {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public Result redeemNotice(ScheduleNoticeReq req) {

        logger.info("req: errorcode:" + req.getErrorCode() + ", msg:" + req.getErrorMsg());

        if (req.isSuccess()) {
            // 写入消息
            template.opsForList().rightPush(Settings.getInstance().getRedeemName(),
                    Settings.getInstance().getRedeemMsg());
        }

        return ResultBuilder.success(null);
    }

    @Override
    public Result loanNotice(ScheduleNoticeReq req) {

        logger.info("req: errorcode:" + req.getErrorCode() + ", msg:" + req.getErrorMsg());

        if (req.isSuccess()) {
            // 写入消息
            template.opsForList().rightPush(Settings.getInstance().getBidLoanName(),
                    Settings.getInstance().getBidLoanMsg());
        }

        return ResultBuilder.success(null);
    }

    @Override
    public Result buybackNotice(ScheduleNoticeReq req) {
        logger.info("req: errorcode:" + req.getErrorCode() + ", msg:" + req.getErrorMsg());

        if (req.isSuccess()) {
            // 写入消息
            template.opsForList().rightPush(Settings.getInstance().getBuyBackName(),
                    Settings.getInstance().getBuyBackMsg());
        }

        return ResultBuilder.success(null);
    }

    @Override
    public Result preAutoBuy(ScheduleNoticeReq req) {
        logger.info("req: errorcode:" + req.getErrorCode() + ", msg:" + req.getErrorMsg());

        if (req.isSuccess()) {
            // 写入消息
            template.opsForList().rightPush(Settings.getInstance().getPreAutoBuyName(),
                    Settings.getInstance().getPreAutoBuyMsg());
        }

        return ResultBuilder.success(null);
    }
}
