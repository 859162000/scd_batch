package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 汇付账户类型枚举
 */
public enum AccountType implements EnumType<AccountType, Integer> {

    EMPTY(0, ""),
    BASEDT(1, "BASEDT"),
    DEP(2, "DEP"),
    DB(3, "DB");

    public int type;

    public String name;

    AccountType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getType() {
        return this.type;
    }

}
