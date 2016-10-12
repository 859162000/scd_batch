/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.common.utils;

public interface EnumType<K extends Enum<K>, T> {
    /**
     * Generally, enum class represents is a safe type for parameter passing, but it also represents a stored int
     * value in db or
     * others storage, so it must can be mapped to a int value
     *
     * @return the mapped int value
     */
    T getType(); // FIXME 枚举实现类的type如果是public的 这个方法就没有必要了的 逃....
}
