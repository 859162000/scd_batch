package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 汇付账户类型枚举
 */
public enum AccountLevelEnum {

    MAIN(1), // 一级账号
    SUB(2); // 二级账号

    public int type;

    AccountLevelEnum(int type) {
        this.type = type;
    }
}
