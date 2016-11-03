package com.scd.batch.common.entity.bid;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by taomengchun on 16/9/28.
 */
public class CreditRepayPlan implements Serializable {

    private Long id;

    private String projectPlanId;

    private String repayPlanId;

    private String orderFlowNo;

    private String orderSeqNo;

    private String loanFlowNo;

    private String projectCode;

    private String productCode;

    private String borrowerId;

    private String investUid;

    private Date repayDate;

    private Double repayAmount;

    /**
     * 债权用来计算本期还息的本金
     */
    private Double creditAmount;

    private Double repayInterest;

    private Double receiptAmount;

    private Double receiptInterest;

    /**
     *还款计划的状态
     */
    private String status;

    private Integer dataStatus;
    /**
     * 还款方式
     */
    private String repayType;

    private Double receiptInterestRate;

    private Double repayInterestRate;

    /**
     * 计息日期
     */
    private Date interestedDate;

    /**
     * 放款时间
     */
    private Date loanDate;
    /**
     * 还款执行类型
     */
    private Integer repayKind;
    /**
     * 交易订单认购时间
     */
    private Date tradeTime;


    public String getRepayPlanId() {
        return repayPlanId;
    }

    public void setRepayPlanId(String repayPlanId) {
        this.repayPlanId = repayPlanId;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getLoanFlowNo() {
        return loanFlowNo;
    }

    public void setLoanFlowNo(String loanFlowNo) {
        this.loanFlowNo = loanFlowNo;
    }

    public Double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Double getReceiptInterest() {
        return receiptInterest;
    }

    public void setReceiptInterest(Double receiptInterest) {
        this.receiptInterest = receiptInterest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectPlanId() {
        return projectPlanId;
    }

    public void setProjectPlanId(String projectPlanId) {
        this.projectPlanId = projectPlanId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getOrderFlowNo() {
        return orderFlowNo;
    }

    public void setOrderFlowNo(String orderFlowNo) {
        this.orderFlowNo = orderFlowNo;
    }

    public String getInvestUid() {
        return investUid;
    }

    public void setInvestUid(String investUid) {
        this.investUid = investUid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getReceiptInterestRate() {
        return receiptInterestRate;
    }

    public void setReceiptInterestRate(Double receiptInterestRate) {
        this.receiptInterestRate = receiptInterestRate;
    }

    public Double getRepayInterestRate() {
        return repayInterestRate;
    }

    public void setRepayInterestRate(Double repayInterestRate) {
        this.repayInterestRate = repayInterestRate;
    }

    public Date getInterestedDate() {
        return interestedDate;
    }

    public void setInterestedDate(Date interestedDate) {
        this.interestedDate = interestedDate;
    }

    public Integer getRepayKind() {
        return repayKind;
    }

    public void setRepayKind(Integer repayKind) {
        this.repayKind = repayKind;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getOrderSeqNo() {
        return orderSeqNo;
    }

    public void setOrderSeqNo(String orderSeqNo) {
        this.orderSeqNo = orderSeqNo;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }
}
