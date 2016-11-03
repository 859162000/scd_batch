package com.scd.batch.common.entity.bid;

import java.io.Serializable;

/**
 *定期计划产品
 */
public class FixPlanProduct extends Product implements Serializable {
    /**
     * 自增id
     */
    private Long id;
    /**
     *产品标号
     */
    private String productCode;

    /**
     *定向标 投资用户uid
     */
    private String targetUid;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(String targetUid) {
        this.targetUid = targetUid;
    }
}