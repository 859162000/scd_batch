package com.scd.batch.common.constant.trans;

import com.scd.batch.common.utils.EnumType;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户类型：平台类型,invest:投资平台,finance:融资平台
 */
public enum UserType implements EnumType<UserType, String> {

    // 投资平台
    INVEST("invest", "INVEST"),

    // 融资平台
    FINANCE("finance", "FINANCE");

    public String type;

    public String name;


    UserType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getType() {
        return this.type;
    }

}
