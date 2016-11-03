package com.scd.batch.common.mybatis.multidb;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

public class MultiDBPointcut extends StaticMethodMatcherPointcut implements Serializable {
    private static final long serialVersionUID = -4645924473792187962L;

    /** MultiDBSource */
    private MultiDBSource multiDBSource;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return (multiDBSource != null
                && multiDBSource.getMultiDBAnnotation(method, targetClass) != null);
    }

    /**
     * Object override methods
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof MultiDBPointcut)) {
            return false;
        }

        MultiDBPointcut otherPc = (MultiDBPointcut) other;
        return ObjectUtils.nullSafeEquals(multiDBSource, otherPc.getMultiDBSource());
    }

    @Override
    public int hashCode() {
        return MultiDBPointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getMultiDBSource();
    }

    /**
     * Getters & Setters
     */
    public MultiDBSource getMultiDBSource() {
        return multiDBSource;
    }

    public void setMultiDBSource(MultiDBSource multiDBSource) {
        this.multiDBSource = multiDBSource;
    }
    
}
