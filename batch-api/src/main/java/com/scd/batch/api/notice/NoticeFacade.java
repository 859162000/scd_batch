package com.scd.batch.api.notice;

import com.miaoqian.framework.domain.Result;
import com.scd.batch.api.entity.ScheduleNoticeReq;


public interface NoticeFacade {

    // 批量赎回通知
    Result redeemNotice(ScheduleNoticeReq req);

    // 批量放款通知
    Result loanNotice(ScheduleNoticeReq req);

    // 批量回购通知
    Result buybackNotice(ScheduleNoticeReq req);

    // 批量认购预跑
    Result preAutoBuy(ScheduleNoticeReq req);

}
