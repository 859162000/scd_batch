package com.scd.batch.common.constant.interest;

import com.scd.batch.common.utils.EnumType;

/**
 * 利率类型
 */
public enum InterestRateType implements EnumType<InterestRateType, Integer> {

    YEAR(1, "年利率"),
    MONTH(2, "月利率"),
    DAY(3, "日利率");

    public int type;

    public String name;

    InterestRateType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getType() {
        return type;
    }
}
