package com.scd.batch.executor.job;

import com.scd.batch.common.job.executor.AbstractExecutor;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.executor.service.NoticeServiceImpl;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.annotation.Resource;

public class NoticeJob extends AbstractExecutor {

    @Resource
    private NoticeServiceImpl noticeService;


    @Override
    public void execute(ExecutorContext context) {
        // 批跑成功通知
        noticeService.batchSuccess();
        logger.info("sendMessage : ");
    }

    @Override
    public void handleException(ExecutorContext context, Throwable t) {
        logger.error("job execute error, job desc: {}, context: {}, exception: {}",
                getName(), context, ExceptionUtils.getStackTrace(t));

        throw new RuntimeException(t);
    }
}
