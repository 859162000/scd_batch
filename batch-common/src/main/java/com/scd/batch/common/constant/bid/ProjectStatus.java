package com.scd.batch.common.constant.bid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taomengchun on 16/9/14.
 */
public enum ProjectStatus {
    UN_COMMIT(1, "未提交审核"),
    WAIT_COMMIT(2, "待提交审核"),
    COMMITTED(3, "已提交审核"),
    AUDIT_FAIL(99, "审核失败"),
    WAIT_PUB(4, "待发布产品"),
    PUBLISHED(5, "已发布产品"),
    FULL_BID(6, "已满标"),
    CLOSED(7, "已结清");

    private final int code;

    private final String name;

    ProjectStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ProjectStatus getByCode(Integer code) {
        if (code != null) {
            ProjectStatus[] statuses = ProjectStatus.values();
            for (ProjectStatus status : statuses) {
                if (code.equals(status.code)) {
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

    public static List<Integer> getValidStatusList() {

        List<Integer> list = new ArrayList<>();
        list.add(WAIT_COMMIT.getCode());
        list.add(PUBLISHED.getCode());
        list.add(FULL_BID.getCode());
        list.add(CLOSED.getCode());

        return list;
    }

}
