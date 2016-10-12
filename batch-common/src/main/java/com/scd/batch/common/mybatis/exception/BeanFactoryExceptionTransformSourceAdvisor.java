package com.scd.batch.common.mybatis.exception;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.util.Assert;

/**
 * Subclass extends from {@link AbstractBeanFactoryPointcutAdvisor}
 * 
 * @author yutianbao
 */
public class BeanFactoryExceptionTransformSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {
    private static final long serialVersionUID = -5137550734811005932L;

    /** Pointcut */
    private ExceptionTransformPointcut pointcut = new ExceptionTransformPointcut();

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    /**
     * Set the {@link ExceptionTransformSource}
     */
    public void setExceptionTransformSource(ExceptionTransformSource exceptionTransformSource) {
        Assert.notNull(exceptionTransformSource, "ExceptionTransformSource can not be null!");
        this.pointcut.setExceptionTransformSource(exceptionTransformSource);
    }

    /**
     * Set the {@link ClassFilter} to use for this pointcut. Default is {@link ClassFilter#TRUE}.
     */
    public void setClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }

}