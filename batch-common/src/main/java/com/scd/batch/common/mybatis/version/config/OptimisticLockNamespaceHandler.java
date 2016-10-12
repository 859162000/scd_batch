package com.scd.batch.common.mybatis.version.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * OptimisticLockNamespaceHandler
 *
 * @author yutianbao
 */
public class OptimisticLockNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenOptimisticLockBeanDefinitionParser());
    }
}
