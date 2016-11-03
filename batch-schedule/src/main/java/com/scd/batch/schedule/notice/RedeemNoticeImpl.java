package com.scd.batch.schedule.notice;

import com.miaoqian.framework.domain.Result;
import com.miaoqian.framework.domain.ResultBuilder;
import com.scd.batch.api.notice.NoticeFacade;

public class RedeemNoticeImpl implements NoticeFacade {

    @Override
    public Result redeemNotice() {

        // TODO 更新任务状态为成功

        return ResultBuilder.success(null);
    }
}
