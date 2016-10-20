package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 手续费收取方
 */
public enum FeeObjType implements EnumType<FeeObjType, String> {

    USER("U", "向用户收取"),
    MERCHANT("M", "向商户收取");

    public String type;

    public String name;

    FeeObjType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getType() {
        return null;
    }
}
