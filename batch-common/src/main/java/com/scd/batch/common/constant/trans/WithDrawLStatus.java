package com.scd.batch.common.constant.trans;

import com.scd.batch.common.utils.EnumType;

import java.util.ArrayList;
import java.util.List;

/**
 * 提现流水状态枚举，处理状态;init:初始;process:处理中;sucess:处理成功;fail:失败
 */
public enum WithDrawLStatus implements EnumType<WithDrawLStatus, String> {

    // 初始
    INIT("1", "INIT"),

    // 处理中
    PROCESS("2", "PROCESS"),

    // 处理成功
    SUCCESS("3", "SUCCESS"),

    // 处理失败
    FAIL("4", "FAIL");

    public String type;

    public String name;

    public static List<String> successList;

    static {
        successList = new ArrayList<>();
        successList.add(SUCCESS.getType());
    }

    WithDrawLStatus(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public static List<String> getSuccessStatusList() {
        return successList;
    }

}
