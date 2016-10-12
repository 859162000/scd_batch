package com.scd.batch.common.mybatis.version;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Represents an optimistic lock {@link Pointcut}
 * 
 * @author yutianbao
 */
public class OptimisticLockPointcut extends StaticMethodMatcherPointcut implements Serializable {
    private static final long serialVersionUID = -4645924473792187962L;

    /** OptimisticLockSource */
    private OptimisticLockSource optimisticLockSource;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return (optimisticLockSource != null
                && optimisticLockSource.getOptimisticLockAnnotation(method, targetClass) != null);
    }

    /**
     * Object override methods
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof OptimisticLockPointcut)) {
            return false;
        }

        OptimisticLockPointcut otherPc = (OptimisticLockPointcut) other;
        return ObjectUtils.nullSafeEquals(optimisticLockSource, otherPc.getOptimisticLockSource());
    }

    @Override
    public int hashCode() {
        return OptimisticLockPointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getOptimisticLockSource();
    }

    /**
     * Getters & Setters
     */
    public OptimisticLockSource getOptimisticLockSource() {
        return optimisticLockSource;
    }

    public void setOptimisticLockSource(OptimisticLockSource optimisticLockSource) {
        this.optimisticLockSource = optimisticLockSource;
    }
    
}
