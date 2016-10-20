package com.scd.batch.common.constant.reconciliation;

import com.scd.batch.common.utils.EnumType;

/**
 * 对账差错类型
 */
public enum TransferErrorType implements EnumType<TransferErrorType, Integer> {

    // 我方单边，SCD有，HUIFU无
    SCD(1, "SCD"),

    // 对方单边，SCD无，第三方有
    THIRDPARTY(2, "THIRDPARTY"),

    // 双方都有，信息不匹配
    BOTH(3, "BOTH");

    public int type;

    public String name;

    TransferErrorType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getType() {
        return this.type;
    }

}
