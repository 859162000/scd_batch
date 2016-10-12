package com.scd.batch.common.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtils {

    /**
     * Retrieve the unsafe instance, the direct method invocation will throw {@link SecurityException}
     */
    public static Unsafe getUnsafe() {
        try {

            // Retrieve the "theUnsafe" field
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");

            // set access
            unsafeField.setAccessible(true);

            return (Unsafe) unsafeField.get(null);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
