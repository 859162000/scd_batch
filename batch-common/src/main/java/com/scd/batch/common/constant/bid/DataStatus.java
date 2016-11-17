package com.scd.batch.common.constant.bid;

/**
 * Created by taomengchun on 16/9/4.
 */
public enum  DataStatus {
    VALID(1, "有效"),
    INVALID(0, "无效");

    private final int code;

    private final String name;

    DataStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static DataStatus getByCode(Integer code) {
        if (code != null) {
            DataStatus[] statuses = DataStatus.values();
            for (DataStatus status : statuses) {
                if (code == status.code) {
                    return status;
                }
            }
        }
        return null;
    }

    public boolean equalsCode(Integer code) {
        if (code != null) {
            return this.code == code;
        }
        return false;
    }
}
