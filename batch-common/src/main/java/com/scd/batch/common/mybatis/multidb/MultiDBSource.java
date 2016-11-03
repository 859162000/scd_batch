package com.scd.batch.common.mybatis.multidb;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiDBSource implements Serializable {
    private static final long serialVersionUID = 7321806917817782997L;

    /**
     * MultiDB cache. <br>
     * Key: AnnotatedElementKey, Value: MultiDB
     */
    private final Map<Object, MultiDB> multiDBCache = new ConcurrentHashMap<>(1 << 10);

    /**
     * Get {@link MultiDB} annotation from the invoking method & class
     * 
     * @param method
     * @param targetClass
     * @return
     */
    public MultiDB getMultiDBAnnotation(Method method, Class<?> targetClass) {
        AnnotatedElementKey cacheKey = new AnnotatedElementKey(method, targetClass);

        // get from cache if existed
        MultiDB cachedMultiDB = this.multiDBCache.get(cacheKey);
        if (cachedMultiDB != null) {
            return cachedMultiDB;
        }

        MultiDB multiDB = findAnnotation(method, targetClass);
        if (multiDB != null) {
            this.multiDBCache.put(cacheKey, multiDB);
        }

        return multiDB;
    }

    /**
     * Find MultiDB annotation
     */
    private MultiDB findAnnotation(Method method, Class<?> targetClass) {
        /** copy from AnnotationCacheOperationSource} */
        // The method may be on an interface, but we need attributes from the target class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        // First try is the method in the target class.
        MultiDB multiDB = specificMethod.getAnnotation(MultiDB.class);
        if (multiDB != null) {
            return multiDB;
        }

        if (specificMethod != method) {
            // Fall back is to look at the original method.
            multiDB = method.getAnnotation(MultiDB.class);
            if (multiDB != null) {
                return multiDB;
            }
        }

        return null;
    }

}