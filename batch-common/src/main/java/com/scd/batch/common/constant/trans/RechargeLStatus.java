package com.scd.batch.common.constant.trans;

import com.scd.batch.common.utils.EnumType;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值流水状态枚举，处理状态;init:初始;process:处理中;sucess:处理成功;fail:失败
 */
public enum RechargeLStatus implements EnumType<RechargeLStatus, String> {

    // 初始
    INIT("init", "INIT"),

    // 处理中
    PROCESS("process", "PROCESS"),

    // 处理成功
    SUCCESS("success", "SUCCESS"),

    // 处理失败
    FAIL("fail", "FAIL");

    public String type;

    public String name;

    public static List<String> successList;

    static {
        successList = new ArrayList<>();
        successList.add(SUCCESS.getType());
    }

    RechargeLStatus(String type, String name) {
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
