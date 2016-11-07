package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 删除标记
 */
public enum DeleteFlagType implements EnumType<DeleteFlagType, Integer> {

    NOT(0, "未删除"),
    DELETE(1, "已删除");

    public Integer type;

    public String desc;

    DeleteFlagType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public Integer getType() {
        return type;
    }
}
