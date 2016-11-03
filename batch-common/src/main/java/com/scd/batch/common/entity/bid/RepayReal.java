package com.scd.batch.common.entity.bid;

import java.io.Serializable;
import java.util.Date;

public class RepayReal implements Serializable {
    private Long id;

    private String projectCode;

    private String planId;

    private Integer repayTerm;

    private Date repayDate;

    /**
     * 还款编号
     * 传递给交易作为资金冻结的标记号
     */
    private String repayNo;

    private Double repayAmount;

    private Double repayInterest;

//    private Double receiptAmount;
//
//    private Double receiptInterest;

    private Date createTime;
    /**
     * 还款流程流程状态
     */
    private Integer dataStatus;

    private Integer status;

    private String repayType;

    private String repayUserType;

    private String repayTimeType;

    private String borrowerId;

    /**
     * 实际还款人UID 补息 垫资情况下 跟borrowerID不一致
     */
    private String repayUid;
    /**
     * 还款资金流水类型
     *
     */
    private Integer repayAmountType;

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Integer getRepayAmountType() {
        return repayAmountType;
    }

    public void setRepayAmountType(Integer repayAmountType) {
        this.repayAmountType = repayAmountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepayNo() {
        return repayNo;
    }

    public void setRepayNo(String repayNo) {
        this.repayNo = repayNo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Integer getRepayTerm() {
        return repayTerm;
    }

    public void setRepayTerm(Integer repayTerm) {
        this.repayTerm = repayTerm;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public Double getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Double getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(Double repayInterest) {
        this.repayInterest = repayInterest;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType == null ? null : repayType.trim();
    }

    public String getRepayUid() {
        return repayUid;
    }

    public void setRepayUid(String repayUid) {
        this.repayUid = repayUid == null ? null : repayUid.trim();
    }

    public String getRepayUserType() {
        return repayUserType;
    }

    public void setRepayUserType(String repayUserType) {
        this.repayUserType = repayUserType == null ? null : repayUserType.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getRepayTimeType() {
        return repayTimeType;
    }

    public void setRepayTimeType(String repayTimeType) {
        this.repayTimeType = repayTimeType;
    }

    public int getRepayStyle() {
        return repayAmountType;
    }

    public void setRepayStyle(int repayAmountType) {
        this.repayAmountType = repayAmountType;
    }
//
//    public Double getReceiptAmount() {
//        return receiptAmount;
//    }
//
//    public void setReceiptAmount(Double receiptAmount) {
//        this.receiptAmount = receiptAmount;
//    }
//
//    public Double getReceiptInterest() {
//        return receiptInterest;
//    }
//

//    public void setReceiptInterest(Double receiptInterest) {
//        this.receiptInterest = receiptInterest;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RepayReal{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", repayTerm=" + repayTerm +
                ", repayDate=" + repayDate +
                ", repayAmount=" + repayAmount +
                ", repayInterest=" + repayInterest +
//                ", receiptAmount=" + receiptAmount +
//                ", receiptInterest=" + receiptInterest +
                ", createTime=" + createTime +
                ", dataStatus=" + dataStatus +
                ", repayType='" + repayType + '\'' +
                ", repayUserType='" + repayUserType + '\'' +
                ", planId='" + planId + '\'' +
                ", repayTimeType=" + repayTimeType +
                ", repayNo='" + repayNo + '\'' +
                ", repayUid='" + repayUid + '\'' +
                ", repayAmountType=" + repayAmountType +
                '}';
    }
}