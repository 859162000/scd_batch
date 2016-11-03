package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 对账差错处理状态
 */
public enum TransferErrorStatus implements EnumType<TransferErrorStatus, Integer> {

    // 初始
    INIT(1, "INIT"),

    // 调整汇付解决
    HUIFU_DONE(2, "HUIFU"),

    // 调整SCD解决
    SCD_DONE(3, "SCD");

    public int type;

    public String name;

    TransferErrorStatus(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getType() {
        return this.type;
    }

}
