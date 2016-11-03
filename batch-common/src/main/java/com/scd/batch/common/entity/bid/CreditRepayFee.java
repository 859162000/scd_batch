package com.scd.batch.common.entity.bid;

import java.util.Date;

public class CreditRepayFee {
    private Long id;

    private String feeUid;

    private String repayFlowNo;

    private String repayRealNo;

    private Date repayDate;

    private String feeType;

    private double feeAmount;

    private Date createTime;

    private Date successTime;

    private Integer dataStatus;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeUid() {
        return feeUid;
    }

    public void setFeeUid(String feeUid) {
        this.feeUid = feeUid == null ? null : feeUid.trim();
    }

    public String getRepayFlowNo() {
        return repayFlowNo;
    }

    public void setRepayFlowNo(String repayFlowNo) {
        this.repayFlowNo = repayFlowNo == null ? null : repayFlowNo.trim();
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRepayRealNo() {
        return repayRealNo;
    }

    public void setRepayRealNo(String repayRealNo) {
        this.repayRealNo = repayRealNo;
    }
}