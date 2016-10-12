package com.scd.batch.common.mybatis.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yutianbao
 */
public class ExceptionTransformInterceptor implements MethodInterceptor, Serializable {
    private static final long serialVersionUID = 945503830988072674L;

    /** ExceptionTransformSource */
    private ExceptionTransformSource exceptionTransformSource;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Class<?> targetClass = getTargetClass(invocation.getThis());

        // get optimistic lock from source
        ExceptionTransform exceptionTransform = exceptionTransformSource
                .getExceptionTransformSource(method, targetClass);

        if (exceptionTransform == null) {
            return invocation.proceed();
        }

        Class<? extends Throwable>[] sources = exceptionTransform.source();
        Class<? extends Throwable> target = exceptionTransform.target();

        if (sources == null || sources.length == 0 || target == null) {
            return invocation.proceed();
        }

        // invoke DB operation
        try {
            return invocation.proceed();
        } catch (Throwable e) {

            if (! hit(e, sources)) {
                throw e;
            }

            Throwable newThrowable = trans2Target(target, e, invocation.getArguments());
            if (newThrowable == null) {
                newThrowable = new UnsupportedOperationException("can't instantiate target exception");
            }

            throw newThrowable;
        }
    }

    /**
     * Get target class
     */
    private Class<?> getTargetClass(Object target) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
        return targetClass == null ? target.getClass() : targetClass;
    }

    /**
     * Setters
     */
    public void setExceptionTransformSource(ExceptionTransformSource exceptionTransformSource) {
        this.exceptionTransformSource = exceptionTransformSource;
    }

    private boolean hit(Throwable e, Class<? extends Throwable>[] sources) {
        Set<Class<?>> allSupers = new HashSet<>();

        Class<?> nextSupper = e.getClass();

        allSupers.add(nextSupper);

        nextSupper = nextSupper.getSuperclass();

        while (! Object.class.equals(nextSupper)) {
            allSupers.add(nextSupper);
            nextSupper = nextSupper.getSuperclass();
        }

        for (Class<?> c : sources) {
            if (allSupers.contains(c)) {
                return true;
            }
        }

        return false;
    }

    private Throwable trans2Target(Class<? extends Throwable> target, Throwable e, Object... methodArgs) {

        // 1. constructor (Throwable cause, Object... args)
        Throwable newThrowable = newInstance(
                target,
                new Class[] {Throwable.class, Object[].class},
                e, methodArgs);

        if (newThrowable != null) {
            return newThrowable;
        }

        // 2. constructor (Object... args)
        newThrowable = newInstance(target, new Class[] {Object[].class}, new Object[] {methodArgs});

        if (newThrowable != null) {
            return newThrowable;
        }

        // 3. constructor (String message, Throwable cause)
        newThrowable = newInstance(target, new Class[] {String.class, Throwable.class}, e.getClass() + "", e);

        if (newThrowable != null) {
            return newThrowable;
        }

        // 4. constructor (Throwable cause)
        newThrowable = newInstance(target, new Class[] {Throwable.class}, e);

        if (newThrowable != null) {
            return newThrowable;
        }

        // 5. constructor (String message)
        newThrowable = newInstance(target, new Class[] {String.class}, e.getClass() + "");

        if (newThrowable != null) {
            return newThrowable;
        }

        // 6. default constructor ()
        return newInstance(target, new Class[0]);
    }

    private Throwable newInstance(Class<? extends Throwable> target, Class<?>[] constructorArgs, Object... args) {
        try {
            return target.getConstructor(constructorArgs).newInstance(args);
        } catch (Exception e) {
            return null;
        }
    }


}
