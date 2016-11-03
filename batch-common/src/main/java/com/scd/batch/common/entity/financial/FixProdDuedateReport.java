package com.scd.batch.common.entity.financial;

import java.math.BigDecimal;
import java.util.Date;

public class FixProdDuedateReport {
    private Long id;

    private Date dueDate;

    private BigDecimal fixPlanPrn;

    private BigDecimal fixPlanInterest;

    private BigDecimal fixProjectPrn;

    private BigDecimal fixProjectInterest;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getFixPlanPrn() {
        return fixPlanPrn;
    }

    public void setFixPlanPrn(BigDecimal fixPlanPrn) {
        this.fixPlanPrn = fixPlanPrn;
    }

    public BigDecimal getFixPlanInterest() {
        return fixPlanInterest;
    }

    public void setFixPlanInterest(BigDecimal fixPlanInterest) {
        this.fixPlanInterest = fixPlanInterest;
    }

    public BigDecimal getFixProjectPrn() {
        return fixProjectPrn;
    }

    public void setFixProjectPrn(BigDecimal fixProjectPrn) {
        this.fixProjectPrn = fixProjectPrn;
    }

    public BigDecimal getFixProjectInterest() {
        return fixProjectInterest;
    }

    public void setFixProjectInterest(BigDecimal fixProjectInterest) {
        this.fixProjectInterest = fixProjectInterest;
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