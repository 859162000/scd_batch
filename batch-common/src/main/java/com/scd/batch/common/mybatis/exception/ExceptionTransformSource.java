package com.scd.batch.common.mybatis.exception;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The ExceptionTransformSource maintains the class & method which {@link ExceptionTransform} annotationed
 *
 * @author yutianbao
 */
public class ExceptionTransformSource implements Serializable {
    private static final long serialVersionUID = 7321806917817782997L;

    /**
     * ExceptionTransform cache. <br>
     * Key: AnnotatedElementKey, Value: OptimisticLock
     */
    private final Map<Object, ExceptionTransform> exceptionTransformCache = new ConcurrentHashMap<>(1 << 10);

    /**
     * Get {@link ExceptionTransform} annotation from the invoking method & class
     */
    public ExceptionTransform getExceptionTransformSource(Method method, Class<?> targetClass) {
        AnnotatedElementKey cacheKey = new AnnotatedElementKey(method, targetClass);

        // get from cache if existed
        ExceptionTransform exceptionTransformLock = this.exceptionTransformCache.get(cacheKey);
        if (exceptionTransformLock != null) {
            return exceptionTransformLock;
        }

        ExceptionTransform optimisticLock = findAnnotation(method, targetClass);
        if (optimisticLock != null) {
            this.exceptionTransformCache.put(cacheKey, optimisticLock);
        }

        return optimisticLock;
    }

    /**
     * Find OptimisticLock annotation
     */
    private ExceptionTransform findAnnotation(Method method, Class<?> targetClass) {
        /** copy from AnnotationCacheOperationSource} */
        // The method may be on an interface, but we need attributes from the target class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        // First try is the method in the target class.
        ExceptionTransform transformLock = specificMethod.getAnnotation(ExceptionTransform.class);
        if (transformLock != null) {
            return transformLock;
        }

        if (specificMethod != method) {
            // Fall back is to look at the original method.
            transformLock = method.getAnnotation(ExceptionTransform.class);
            if (transformLock != null) {
                return transformLock;
            }
        }

        return null;
    }

}