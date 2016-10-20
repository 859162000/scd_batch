package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 放款还款状态枚举
 */
public enum LoanPaymentTransStat implements EnumType<LoanPaymentTransStat, String> {

    SUCCESS("S", "成功"),
    FAIL("F", "失败"),
    INIT("I", "初始"),
    PART("P", "部分成功"),
    HANDLE("H", "经办"),
    REJECT("R", "拒绝");

    public String status;

    public String name;

    LoanPaymentTransStat(String status, String name) {
        this.status = status;
        this.name = name;
    }

    @Override
    public String getType() {
        return this.status;
    }

}
