/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scd.batch.common.mybatis.version.config;

import com.scd.batch.common.mybatis.version.BeanFactoryOptimisticLockSourceAdvisor;
import com.scd.batch.common.mybatis.version.OptimisticLockInterceptor;
import org.springframework.aop.config.AopNamespaceUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * AnnotationDrivenOptimisticLockBeanDefinitionParser
 *
 * @author yutianbao
 */
class AnnotationDrivenOptimisticLockBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String mode = element.getAttribute("mode");
        if ("aspectj".equals(mode)) {
            // mode="aspectj"
            throw new RuntimeException("Unsupported aspectj mode!");
        } else {
            // mode="proxy"
            AopNamespaceUtils.registerAutoProxyCreatorIfNecessary(parserContext, element);
            SpringCachingConfigurer.registerCacheAdvisor(element, parserContext);
        }

        return null;
    }

    /**
     * Configure the necessary infrastructure to support the OptimisticLock annotation.
     */
    private static class SpringCachingConfigurer {
        private static final String DEFAULT_ADVISOR =
                "com.baidu.fbu.fcore.common.mybatis.version.internalOptimisticLockAdvisor";

        private static void registerCacheAdvisor(Element element, ParserContext parserContext) {
            // pre-check
            if (parserContext.getRegistry().containsBeanDefinition(DEFAULT_ADVISOR)) {
                return;
            }

            Object eleSource = parserContext.extractSource(element);

            // Create the OptimisticLockSource definition.
            RootBeanDefinition sourceDef =
                    new RootBeanDefinition("com.baidu.fbu.fcore.common.mybatis.version.OptimisticLockSource");
            sourceDef.setSource(eleSource);
            sourceDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            String sourceName = parserContext.getReaderContext().registerWithGeneratedName(sourceDef);

            // Create the OptimisticLockInterceptor definition.
            RootBeanDefinition interceptorDef = new RootBeanDefinition(OptimisticLockInterceptor.class);
            RuntimeBeanReference sourceRefer = new RuntimeBeanReference(sourceName);
            interceptorDef.setSource(eleSource);
            interceptorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            interceptorDef.getPropertyValues().add("optimisticLockSource", sourceRefer);
            String interceptorName = parserContext.getReaderContext().registerWithGeneratedName(interceptorDef);

            // Create the BeanFactoryOptimisticLockSourceAdvisor definition.
            RootBeanDefinition advisorDef = new RootBeanDefinition(BeanFactoryOptimisticLockSourceAdvisor.class);
            advisorDef.setSource(eleSource);
            advisorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            advisorDef.getPropertyValues().add("optimisticLockSource", sourceRefer);
            advisorDef.getPropertyValues().add("adviceBeanName", interceptorName);
            if (element.hasAttribute("order")) {
                advisorDef.getPropertyValues().add("order", element.getAttribute("order"));
            }
            parserContext.getRegistry().registerBeanDefinition(DEFAULT_ADVISOR, advisorDef);

            CompositeComponentDefinition compositeDef =
                    new CompositeComponentDefinition(element.getTagName(), eleSource);
            compositeDef.addNestedComponent(new BeanComponentDefinition(sourceDef, sourceName));
            compositeDef.addNestedComponent(new BeanComponentDefinition(interceptorDef, interceptorName));
            compositeDef.addNestedComponent(new BeanComponentDefinition(advisorDef, DEFAULT_ADVISOR));
            parserContext.registerComponent(compositeDef);
        }

    }

}
