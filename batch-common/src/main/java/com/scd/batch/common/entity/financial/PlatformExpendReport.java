package com.scd.batch.common.entity.financial;

import java.math.BigDecimal;
import java.util.Date;

public class PlatformExpendReport {
    private Long id;

    private Date expendDate;

    private String expendDetail;

    private BigDecimal expendPrn;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpendDate() {
        return expendDate;
    }

    public void setExpendDate(Date expendDate) {
        this.expendDate = expendDate;
    }

    public String getExpendDetail() {
        return expendDetail;
    }

    public void setExpendDetail(String expendDetail) {
        this.expendDetail = expendDetail == null ? null : expendDetail.trim();
    }

    public BigDecimal getExpendPrn() {
        return expendPrn;
    }

    public void setExpendPrn(BigDecimal expendPrn) {
        this.expendPrn = expendPrn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}