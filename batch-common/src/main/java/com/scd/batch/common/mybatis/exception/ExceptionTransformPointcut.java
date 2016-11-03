package com.scd.batch.common.mybatis.exception;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

public class ExceptionTransformPointcut extends StaticMethodMatcherPointcut implements Serializable {
    private static final long serialVersionUID = -4645924473792187962L;

    /** ExceptionTransformSource */
    private ExceptionTransformSource exceptionTransformSource;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return (exceptionTransformSource != null
                && exceptionTransformSource.getExceptionTransformSource(method, targetClass) != null);
    }

    /**
     * Object override methods
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ExceptionTransformPointcut)) {
            return false;
        }

        ExceptionTransformPointcut otherPc = (ExceptionTransformPointcut) other;
        return ObjectUtils.nullSafeEquals(exceptionTransformSource, otherPc.getExceptionTransformSource());
    }

    @Override
    public int hashCode() {
        return ExceptionTransformPointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getExceptionTransformSource();
    }

    /**
     * Getters & Setters
     */
    public ExceptionTransformSource getExceptionTransformSource() {
        return exceptionTransformSource;
    }

    public void setExceptionTransformSource(ExceptionTransformSource exceptionTransformSource) {
        this.exceptionTransformSource = exceptionTransformSource;
    }
    
}
