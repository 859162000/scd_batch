package com.scd.batch.common.mybatis.exception.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ExceptionTransformNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenExceptionTransformBeanDefinitionParser());
    }
}
