package com.scd.batch.common.mybatis.version;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The OptimisticLockSource maintains the class & method which {@link OptimisticLock} annotationed
 *
 * @author yutianbao
 */
public class OptimisticLockSource implements Serializable {
    private static final long serialVersionUID = 7321806917817782997L;

    /**
     * OptimisticLock cache. <br>
     * Key: AnnotatedElementKey, Value: OptimisticLock
     */
    private final Map<Object, OptimisticLock> optimisticLockCache = new ConcurrentHashMap<>(1 << 10);

    /**
     * Get {@link OptimisticLock} annotation from the invoking method & class
     * 
     * @param method
     * @param targetClass
     * @return
     */
    public OptimisticLock getOptimisticLockAnnotation(Method method, Class<?> targetClass) {
        AnnotatedElementKey cacheKey = new AnnotatedElementKey(method, targetClass);

        // get from cache if existed
        OptimisticLock cachedOptimisticLock = this.optimisticLockCache.get(cacheKey);
        if (cachedOptimisticLock != null) {
            return cachedOptimisticLock;
        }

        OptimisticLock optimisticLock = findAnnotation(method, targetClass);
        if (optimisticLock != null) {
            this.optimisticLockCache.put(cacheKey, optimisticLock);
        }

        return optimisticLock;
    }

    /**
     * Find OptimisticLock annotation
     */
    private OptimisticLock findAnnotation(Method method, Class<?> targetClass) {
        /** copy from AnnotationCacheOperationSource} */
        // The method may be on an interface, but we need attributes from the target class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        // First try is the method in the target class.
        OptimisticLock optimisticLock = specificMethod.getAnnotation(OptimisticLock.class);
        if (optimisticLock != null) {
            return optimisticLock;
        }

        if (specificMethod != method) {
            // Fall back is to look at the original method.
            optimisticLock = method.getAnnotation(OptimisticLock.class);
            if (optimisticLock != null) {
                return optimisticLock;
            }
        }

        return null;
    }

}