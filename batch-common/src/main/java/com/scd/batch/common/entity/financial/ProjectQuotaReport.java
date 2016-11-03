package com.scd.batch.common.entity.financial;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectQuotaReport {
    private Long id;

    private Date reportDate;

    private String projectName;

    private String projectCode;

    private BigDecimal projectFinancingAmt;

    private BigDecimal issuedAmt;

    private BigDecimal remainIssueAmt;

    private BigDecimal withdrewAmt;

    private BigDecimal repaidPrn;

    private BigDecimal unrepayPrn;

    private BigDecimal repaidInterest;

    private BigDecimal unrepayInterest;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public BigDecimal getProjectFinancingAmt() {
        return projectFinancingAmt;
    }

    public void setProjectFinancingAmt(BigDecimal projectFinancingAmt) {
        this.projectFinancingAmt = projectFinancingAmt;
    }

    public BigDecimal getIssuedAmt() {
        return issuedAmt;
    }

    public void setIssuedAmt(BigDecimal issuedAmt) {
        this.issuedAmt = issuedAmt;
    }

    public BigDecimal getRemainIssueAmt() {
        return remainIssueAmt;
    }

    public void setRemainIssueAmt(BigDecimal remainIssueAmt) {
        this.remainIssueAmt = remainIssueAmt;
    }

    public BigDecimal getWithdrewAmt() {
        return withdrewAmt;
    }

    public void setWithdrewAmt(BigDecimal withdrewAmt) {
        this.withdrewAmt = withdrewAmt;
    }

    public BigDecimal getRepaidPrn() {
        return repaidPrn;
    }

    public void setRepaidPrn(BigDecimal repaidPrn) {
        this.repaidPrn = repaidPrn;
    }

    public BigDecimal getUnrepayPrn() {
        return unrepayPrn;
    }

    public void setUnrepayPrn(BigDecimal unrepayPrn) {
        this.unrepayPrn = unrepayPrn;
    }

    public BigDecimal getRepaidInterest() {
        return repaidInterest;
    }

    public void setRepaidInterest(BigDecimal repaidInterest) {
        this.repaidInterest = repaidInterest;
    }

    public BigDecimal getUnrepayInterest() {
        return unrepayInterest;
    }

    public void setUnrepayInterest(BigDecimal unrepayInterest) {
        this.unrepayInterest = unrepayInterest;
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