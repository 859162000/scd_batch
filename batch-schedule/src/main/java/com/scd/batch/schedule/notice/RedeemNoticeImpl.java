package com.scd.batch.schedule.notice;

import com.miaoqian.framework.domain.Result;
import com.miaoqian.framework.domain.ResultBuilder;
import com.scd.batch.api.entity.RedeemNoticeReq;
import com.scd.batch.api.notice.NoticeFacade;

public class RedeemNoticeImpl implements NoticeFacade {

    @Override
    public Result redeemNotice(RedeemNoticeReq req) {
        return ResultBuilder.success(null);
    }

    @Override
    public Result loanNotice(RedeemNoticeReq req) {
        return ResultBuilder.success(null);
    }

    @Override
    public Result buybackNotice(RedeemNoticeReq req) {
        return ResultBuilder.success(null);
    }
}
