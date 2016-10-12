package com.scd.batch.common.mybatis.version;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.util.Assert;

/**
 * Subclass extends from {@link AbstractBeanFactoryPointcutAdvisor}
 * 
 * @author yutianbao
 */
public class BeanFactoryOptimisticLockSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {
    private static final long serialVersionUID = -5137550734811005932L;

    /** Pointcut */
    private OptimisticLockPointcut pointcut = new OptimisticLockPointcut();

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    /**
     * Set the {@link OptimisticLockSource}
     */
    public void setOptimisticLockSource(OptimisticLockSource optimisticLockSource) {
        Assert.notNull(optimisticLockSource, "OptimisticLockSource can not be null!");
        this.pointcut.setOptimisticLockSource(optimisticLockSource);
    }

    /**
     * Set the {@link ClassFilter} to use for this pointcut. Default is {@link ClassFilter#TRUE}.
     */
    public void setClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }

}