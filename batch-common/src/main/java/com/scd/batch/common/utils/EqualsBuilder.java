/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.common.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class EqualsBuilder extends org.apache.commons.lang.builder.EqualsBuilder {

    /**
     * If the fields tested are equals.
     * The default value is <code>true</code>.
     */
    private boolean isEquals = true;

    /**
     * <p>This method uses reflection to determine if the two <code>Object</code>s
     * are equal in provided fields.</p>
     * @param lhs  <code>this</code> object
     * @param rhs  the other object
     * @param includeFields  array of fields name to include from tests
     * @return <code>true</code> if the two Objects have tested equals.
     */
    public static boolean isEqualsInclude(final Object lhs, final Object rhs, final String[] includeFields) {

        if (lhs == rhs) {
            return true;
        }
        if (lhs == null || rhs == null) {
            return false;
        }

        // validate the type of two class
        final Class<?> lhsClass = lhs.getClass();
        final Class<?> rhsClass = rhs.getClass();
        Class<?> testClass;
        if (lhsClass.isInstance(rhs)) {
            testClass = lhsClass;
            if (!rhsClass.isInstance(lhs)) {
                // rhsClass is a subclass of lhsClass
                testClass = rhsClass;
            }
        } else if (rhsClass.isInstance(lhs)) {
            testClass = rhsClass;
            if (!lhsClass.isInstance(rhs)) {
                // lhsClass is a subclass of rhsClass
                testClass = lhsClass;
            }
        } else {
            // The two classes are not related.
            return false;
        }

        // classes are related and no includeFields return true
        if (includeFields == null || includeFields.length == 0) {
            return true;
        }

        ArrayList<String> includeList = new ArrayList<>();
        Collections.addAll(includeList, includeFields);

        EqualsBuilder equalsBuilder = new EqualsBuilder();

        try {
            //
            if (testClass.isArray()) {
                equalsBuilder.append(lhs, rhs);
            } else {
                reflectionIncludedAppend(lhs, rhs, testClass, equalsBuilder, includeList);
                while (equalsBuilder.isEquals && testClass.getSuperclass() != null && !includeList.isEmpty()) {
                    testClass = testClass.getSuperclass();
                    reflectionIncludedAppend(lhs, rhs, testClass, equalsBuilder, includeList);
                }
            }
        } catch (final IllegalArgumentException e) {
            // In this case, we tried to test a subclass vs. a superclass and
            // the subclass has ivars or the ivars are transient and
            // we are testing transients.
            // If a subclass has ivars that we are trying to test them, we get an
            // exception and we know that the objects are not equal.
            return false;
        }
        return equalsBuilder.isEquals();

    }

    /**
     * <p>Appends the fields and values defined by the given object of the
     * given Class.</p>
     *
     * @param lhs  <code>this</code> object
     * @param rhs  the other object
     * @param clazz the class to append details of(current class type to append)
     * @param builder the builder to append to
     * @param includeList array of field names to exclude from testing
     */
    private static void reflectionIncludedAppend(final Object lhs,
                                                 final Object rhs,
                                                 Class clazz,
                                                 EqualsBuilder builder,
                                                 ArrayList<String> includeList) {

        Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);

        // try to get the fields existing in current class
        for (Iterator<String> it = includeList.iterator(); it.hasNext() && builder.isEquals; ) {
            String s = it.next();
            try {
                Field tempField = clazz.getDeclaredField(s);
                it.remove();
                builder.append(tempField.get(lhs), tempField.get(rhs));
            } catch (Exception e) {
                // Can't get any corresponding filed and return
            }
        }
    }

}
