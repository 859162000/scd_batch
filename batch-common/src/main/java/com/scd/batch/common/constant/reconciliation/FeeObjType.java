package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 手续费收取方
 */
public enum FeeObjType implements EnumType<FeeObjType, String> {

    USER("U", "1"),
    MERCHANT("M", "2");

    public String type;

    public String desc;

    FeeObjType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String getType() {
        return type;
    }
}
