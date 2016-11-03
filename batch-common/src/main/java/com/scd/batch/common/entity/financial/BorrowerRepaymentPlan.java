package com.scd.batch.common.entity.financial;

import java.math.BigDecimal;
import java.util.Date;

public class BorrowerRepaymentPlan {
    private Long id;

    private Date repaymentDate;

    private String borrower;

    private String projectCode;

    private String repaymentType;

    private BigDecimal repaymentPrn;

    private BigDecimal repaymentInterest;

    private BigDecimal actualRepaymentPrn;

    private BigDecimal actualRepaymentInterest;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower == null ? null : borrower.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType == null ? null : repaymentType.trim();
    }

    public BigDecimal getRepaymentPrn() {
        return repaymentPrn;
    }

    public void setRepaymentPrn(BigDecimal repaymentPrn) {
        this.repaymentPrn = repaymentPrn;
    }

    public BigDecimal getRepaymentInterest() {
        return repaymentInterest;
    }

    public void setRepaymentInterest(BigDecimal repaymentInterest) {
        this.repaymentInterest = repaymentInterest;
    }

    public BigDecimal getActualRepaymentPrn() {
        return actualRepaymentPrn;
    }

    public void setActualRepaymentPrn(BigDecimal actualRepaymentPrn) {
        this.actualRepaymentPrn = actualRepaymentPrn;
    }

    public BigDecimal getActualRepaymentInterest() {
        return actualRepaymentInterest;
    }

    public void setActualRepaymentInterest(BigDecimal actualRepaymentInterest) {
        this.actualRepaymentInterest = actualRepaymentInterest;
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