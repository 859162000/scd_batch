package com.scd.batch.common.mybatis.multidb;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MultiDB {

    /**
     * Expect affect rows, the value will compare with the actual write SQL affect rows. Default as 1
     *
     * @return
     */
    DataSourceType ds() default DataSourceType.BATCH;

}