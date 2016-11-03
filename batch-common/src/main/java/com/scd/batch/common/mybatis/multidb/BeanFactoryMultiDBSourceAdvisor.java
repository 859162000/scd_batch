package com.scd.batch.common.mybatis.multidb;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.util.Assert;

public class BeanFactoryMultiDBSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private static final long serialVersionUID = -5137550734811005932L;

    /**
     * Pointcut
     */
    private MultiDBPointcut pointcut = new MultiDBPointcut();

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    /**
     * Set the {@link MultiDBSource}
     */
    public void setMultiDBSource(MultiDBSource multiDBSource) {
        Assert.notNull(multiDBSource, "MultiDBSource can not be null!");
        this.pointcut.setMultiDBSource(multiDBSource);
    }

    /**
     * Set the {@link ClassFilter} to use for this pointcut. Default is {@link ClassFilter#TRUE}.
     */
    public void setClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }

}