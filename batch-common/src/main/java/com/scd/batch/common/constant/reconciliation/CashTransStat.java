package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 取现流水状态枚举
 */
public enum CashTransStat implements EnumType<CashTransStat, String> {

    SUCCESS("S", "成功"),
    FAIL("F", "失败"),
    INIT("I", "初始"),
    PART("P", "部分成功"),
    HANDLE("H", "经办"),
    REJECT("R", "拒绝");

    public String status;

    public String name;

    CashTransStat(String status, String name) {
        this.status = status;
        this.name = name;
    }

    @Override
    public String getType() {
        return this.status;
    }

}
