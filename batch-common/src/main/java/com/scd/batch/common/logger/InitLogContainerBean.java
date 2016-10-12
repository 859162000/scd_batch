package com.scd.batch.common.logger;

import org.springframework.beans.factory.InitializingBean;

/**
 * 读取日志码和错误码配置信息
 *
 * @author zhukun on 16/4/26
 */
public class InitLogContainerBean implements InitializingBean {
    private String logLocation;
    private String errorLocation;

    @Override
    public void afterPropertiesSet() throws Exception {
        LogMessageContainers.initMessageContainer(LogMessageContainers.MESSAGE_KEY_LOG, logLocation);
        LogMessageContainers.initMessageContainer(LogMessageContainers.MESSAGE_KEY_ERROR, errorLocation);
    }

    public void setLogLocation(String logLocation) {
        this.logLocation = logLocation;
    }

    public void setErrorLocation(String errorLocation) {
        this.errorLocation = errorLocation;
    }
}
