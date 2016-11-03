package com.scd.batch.common.mybatis.multidb;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

public class MultiDBInterceptor implements MethodInterceptor, Serializable {

    private static final long serialVersionUID = 945503830988072674L;

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiDBInterceptor.class);

    /**
     * MultiDBSource
     */
    private MultiDBSource multiDBSource;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Class<?> targetClass = getTargetClass(invocation.getThis());

        // get multidb from source
        MultiDB multiDB = multiDBSource.getMultiDBAnnotation(method, targetClass);
        if (multiDB != null) {

            // check ds of annotation
            DataSourceTypeManager.set(multiDB.ds());
            LOGGER.info("change datasource to:" + multiDB.ds().name);

            // invoke DB operation
            Object result = invocation.proceed();

            return result;
        }

        return invocation.proceed();
    }

    /**
     * Get target class
     */
    private Class<?> getTargetClass(Object target) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
        if (targetClass == null && target != null) {
            targetClass = target.getClass();
        }
        return targetClass;
    }

    /**
     * Setters
     */
    public void setMultiDBSource(MultiDBSource multiDBSource) {
        this.multiDBSource = multiDBSource;
    }

}
