package com.scd.batch.common.logger;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.WeakHashMap;

public class FLoggerFactory {

    // key: desc (String), value: a Log4jLoggerAdapter;
    private static final Map<String, Object> loggerMap = new WeakHashMap<String, Object>();
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getFrameworkPackages().add("com.baidu.fbu.fcore.common.logger.LoggerHandler");
        loggerContext.getFrameworkPackages().add("com.sun.proxy.$Proxy");
    }

    @SuppressWarnings("unchecked")
    public static <T> T getLogger(Class<?> cz) {
        return (T) getLogger(cz.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getLogger(String name) {
        Object logger = loggerMap.get(name);

        if (logger == null) {
            synchronized (name.intern()) {
                logger = loggerMap.get(name);
                if (logger == null) {
                    Logger target = LoggerFactory.getLogger(name);
                    logger = new LoggerHandler(target).getFLogger();
                    loggerMap.put(name, logger);
                }
            }
        }

        return (T) logger;
    }
}
