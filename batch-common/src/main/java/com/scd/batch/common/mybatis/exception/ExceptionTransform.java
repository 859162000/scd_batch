package com.scd.batch.common.mybatis.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExceptionTransform {

    /**
     * 实际抛出的异常类型
     */
    Class<? extends Throwable>[] source() default {};

    /**
     * 转换后的异常类型, 依次按如下顺序查找构造函数, 查找失败则抛出 UnsupportedOperationException
     *
     * 1. (Throwable cause, Object... args)
     * 2. (Object... args)
     * 3. (String message, Throwable cause)
     * 4. (Throwable cause)
     * 5. (String message)
     * 6. default ()
     */
    Class<? extends Throwable> target() default Throwable.class;

}
