package com.scd.batch.api.notice;

import com.miaoqian.framework.domain.Result;
import com.scd.batch.api.entity.RedeemNoticeReq;


public interface NoticeFacade {

    Result redeemNotice(RedeemNoticeReq req);
}
