package com.scd.batch.common.constant.promotion;

import com.scd.batch.common.utils.EnumType;

public enum CouponsType implements EnumType<CouponsType, String> {

    ENVELOPE("0", "红包"),

    MQ_CARD("1", "秒钱卡"),

    INTEREST_COUPONS("2", "加息券"),

    OUBLE_INTEREST_COUPONS("3", "双倍加息券");

    private String type;
    private String name;

    private CouponsType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
