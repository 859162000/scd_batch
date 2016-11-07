package com.scd.batch.executor.job;

import com.miaoqian.basis.api.dubbo.SmsAPI;
import com.miaoqian.basis.api.dubbo.request.SmsInfo;
import com.miaoqian.framework.domain.Request;
import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.service.NoticeServiceImpl;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class NoticeJob extends AbstractExecutor {

    @Resource
    private NoticeServiceImpl noticeService;

    @Autowired
    private SmsAPI smsAPI;

    @Override
    public void execute(ExecutorContext context) {
        // 批跑成功通知
        smsAPI.sendMessage(noticeService.batchSuccess());
        logger.info("sendMessage : ");
    }

    @Override
    public void handleException(ExecutorContext context, Throwable t) {
        logger.error("job execute error, job desc: {}, context: {}, exception: {}",
                getName(), context, ExceptionUtils.getStackTrace(t));

        throw new RuntimeException(t);
    }
}
