package com.scd.batch.common.mybatis.version;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for Mybatis OptimisticLock
 * 
 * @author yutianbao
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OptimisticLock {

    /**
     * Expect affect rows, the value will compare with the actual write SQL affect rows. Default as 1
     * 
     * @return
     */
    int expect() default 1;

}