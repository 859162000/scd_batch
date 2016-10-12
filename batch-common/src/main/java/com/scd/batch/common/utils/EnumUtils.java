/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.common.utils;

public class EnumUtils {

    /**
     * Retrieves the enum instance by <b>type</b> parameter for class cz, the cz must be extends from {@link EnumType}
     *
     * @param cz   the target enum class
     * @param type the int type
     *
     * @return the enum instance of cz
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T getEnum(Class<T> cz, Object type) {

        // cz must be the derived class from EnumType
        if (!EnumType.class.isAssignableFrom(cz)) {
            throw new IllegalArgumentException("The class " + cz + " is not assign from " + EnumType.class.getName());
        }

        for (T e : cz.getEnumConstants()) {
            EnumType<T, ?> t = (EnumType<T, ?>) e;

            if (t.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }
}
