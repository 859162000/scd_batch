package com.scd.batch.common.constant.bid;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by taomengchun on 16/9/1.
 */
public enum ProjectThdAuditStatus {
    //01-通过 02-拒绝 03-待上传证照 04-待审核 05-待审核证照 06-状态异常 99-参数错误'
    PASS("01", "通过"),
    REFUSE("02", "拒绝"),
    WAITING_PIC("03", "待上传证照"),
    AUDITING("04", "待审核"),
    AUDIT_PIC("05", "待审核证照"),
    STATUS_ERROR("06", "状态异常"),
    PARAM_ERROR("99", "参数错误");


    private final String code;

    private final String name;

    ProjectThdAuditStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ProjectThdAuditStatus getByCode(String code) {
        if (StringUtils.isNotEmpty(code)) {
            ProjectThdAuditStatus[] borrowerTypes = ProjectThdAuditStatus.values();
            for (ProjectThdAuditStatus borrowerType : borrowerTypes) {
                if (code.equals(borrowerType.code)) {
                    return borrowerType;
                }
            }
        }
        return null;
    }

    public boolean equalsCode(String code) {
        if (StringUtils.isNotEmpty(code)) {
            return this.code.equals(code);
        }
        return false;
    }
}
