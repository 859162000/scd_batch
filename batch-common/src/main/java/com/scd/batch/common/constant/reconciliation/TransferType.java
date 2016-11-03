package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 流水类型
 */
public enum TransferType implements EnumType<TransferType, Integer> {

    // 放款交易
    LOANS(1, "LOANS"),

    // 还款交易
    REPAYMENT(2, "REPAYMENT"),

    // 取现交易
    CASH(3,"CASH"),

    RECHARGE(4, "RECHARGE"),

    WITHDRAW(5, "WITHDRAW");

    public int type;

    public String name;

    TransferType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getType() {
        return this.type;
    }

}
