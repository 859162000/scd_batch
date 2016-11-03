package com.scd.batch.common.mybatis.version;

import com.scd.batch.common.exception.OptimisticLockingException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Method;

public class OptimisticLockInterceptor implements MethodInterceptor, Serializable {
    private static final long serialVersionUID = 945503830988072674L;
    private static final Logger LOGGER = LoggerFactory.getLogger(OptimisticLockInterceptor.class);

    /** OptimisticLockSource */
    private OptimisticLockSource optimisticLockSource;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Class<?> targetClass = getTargetClass(invocation.getThis());

        // get optimistic lock from source
        OptimisticLock optimisticLock = optimisticLockSource.getOptimisticLockAnnotation(method, targetClass);
        if (optimisticLock != null) {

            // invoke DB operation
            Object res = invocation.proceed();
            Assert.isInstanceOf(Number.class, res,
                    "OptimisticLockInterceptor target method return type must be Numer!");

            // check expect of annotation
            Number affectRow = (Number) res;
            if (optimisticLock.expect() != affectRow.intValue()) {
                LOGGER.debug("Optimistic lock failed. Method:{}, affect rows:{}, expect:{}", method, affectRow,
                        optimisticLock.expect());
                throw new OptimisticLockingException(method.getName());
            }
            
            return res;
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
    public void setOptimisticLockSource(OptimisticLockSource optimisticLockSource) {
        this.optimisticLockSource = optimisticLockSource;
    }

}
