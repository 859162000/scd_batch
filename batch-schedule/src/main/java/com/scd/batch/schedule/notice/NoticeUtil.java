package com.scd.batch.schedule.notice;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class NoticeUtil {

    @Autowired
    private RedisTemplate<String, String> template;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean wait4Notice(int retry, String name, int timeout) {

        while (retry > 0) {

            String noticeMsg = null;
            try {
                noticeMsg = template.opsForList().leftPop(name, timeout, TimeUnit.SECONDS);
            } catch (Exception e) {

            }

            logger.info("wait4Notice:" + noticeMsg);
            if (StringUtils.isNotEmpty(noticeMsg)) {
                return true;
            }

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                logger.info("thread.sleep timeout!");
            }

            // TODO process msg
            retry--;
        }

        return false;
    }

}
