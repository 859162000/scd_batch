package com.scd.batch.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

public abstract class ReflectionUtils {

    /**
     * Void
     */
    public static boolean isVoid(Class<?> clazz) {
        return clazz == void.class || clazz == Void.class;
    }

    /**
     * Property
     */
    public static Object getProperty(Object target, String name) throws Exception {
        Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(target);
    }

    public static void setProperty(Object target, String name, Object value) throws Exception {
        Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(target, value);
    }

    /**
     * Field
     */
    public static List<Field> getAllFields(Class<?> clazz) throws Exception {
        return getAllFields(clazz, null, true);
    }

    public static List<Field> getAllFields(Class<?> clazz, boolean excludeStaticFileds) throws Exception {
        return getAllFields(clazz, null, excludeStaticFileds);
    }

    public static List<Field> getAllFields(Class<?> clazz, Class<? extends Annotation> annotation) throws Exception {
        return getAllFields(clazz, annotation, true);
    }

    public static List<Field> getAllFields(Class<?> clazz, Class<? extends Annotation> annotation,
            boolean excludeStaticFileds) throws Exception {
        // Precondition checking
        if (clazz == null) {
            return null;
        }

        //
        List<Field> r = new LinkedList<Field>();
        Class<?> parent = clazz;
        while (parent != null) {
            for (Field f : parent.getDeclaredFields()) {
                f.setAccessible(true);

                //
                if (excludeStaticFileds && (f.getModifiers() & Modifier.STATIC) != 0) {
                    continue;
                }
                if (annotation != null && !f.isAnnotationPresent(annotation)) {
                    continue;
                }

                r.add(f);
            }

            //
            parent = parent.getSuperclass();
        }
        return r;
    }

}
