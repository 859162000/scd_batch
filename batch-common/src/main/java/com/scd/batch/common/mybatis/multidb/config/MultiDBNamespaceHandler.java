package com.scd.batch.common.mybatis.multidb.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MultiDBNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenMultiDBBeanDefinitionParser());
    }
}
