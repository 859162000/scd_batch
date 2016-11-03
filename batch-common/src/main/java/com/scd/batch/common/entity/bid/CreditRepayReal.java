package com.scd.batch.common.entity.bid;

import com.google.common.base.Joiner;
import com.scd.batch.common.entity.reconciliation.TransferValue;

import java.io.Serializable;
import java.util.Date;

public class CreditRepayReal implements Serializable, TransferValue {

    private Long id;

    private String projectPlanId;
    /**
     * 借款人还款计划Id
     */
    private String repayPlanId;

    /**
     * 批量还款记录
     */
    private String batchNo;
    /**
     * repayreal的编号
     * 用于对应借款人资金冻结
     */
    private String repayNo;


    /**
     * 还款编号,对应汇付的还款单号
     * 记录的编号
     */
    private String repayFlowNo;

    /**
     * 投资人认购订单编号
     */
    private String orderSeqNo;

    private String orderFlowNo;

    private String loanFlowNo;

    private String projectCode;

    private String productCode;

    private String investUid;

    private String borrowerId;

    private Date repayDate;

    private Double repayAmount;

    private Double repayInterest;

    private Double receiptAmount;

    private Double receiptInterest;
    /**
     *
     */
    private Integer status;

    private Integer dataStatus;

    private Date createTime;

    private Date successTime;

    private String repayType;

    private String repayTimeType;

    private Double receiptInterestRate;

    private Double repayInterestRate;

    /**
     * 实际还款人UID 补息 垫资情况下 跟borrowerID不一致
     */
    private String repayUid;
    /**
     * 还款资金流水类型
     */
    private Integer repayAmountType;
    /**
     * 还款用户类型
     */
    private String repayUserType;

    /**
     * 汇付回调返回结果字符串
     */
    private String resultMessage;

    /**
     * 债权的时实际计息日期
     */
    private Date interestedDate;

    /**
     * 放款时间
     */
    private Date loanDate;

    /**
     * 债权用来计算本期还息的本金
     */
    private Double creditAmount;

    /**
     * 交易订单认购时间
     */
    private Date tradeTime;

    private String frozenFlowNo;

    public String getFrozenFlowNo() {
        return frozenFlowNo;
    }

    public void setFrozenFlowNo(String frozenFlowNo) {
        this.frozenFlowNo = frozenFlowNo;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public void setRepayAmountType(Integer repayAmountType) {
        this.repayAmountType = repayAmountType;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Date getInterestedDate() {
        return interestedDate;
    }

    public void setInterestedDate(Date interestedDate) {
        this.interestedDate = interestedDate;
    }

    public String getRepayFlowNo() {
        return repayFlowNo;
    }

    public void setRepayFlowNo(String repayFlowNo) {
        this.repayFlowNo = repayFlowNo;
    }

    public String getRepayPlanId() {
        return repayPlanId;
    }

    public void setRepayPlanId(String repayPlanId) {
        this.repayPlanId = repayPlanId;
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
        this.projectCode = projectCode;
    }

    public String getProjectPlanId() {
        return projectPlanId;
    }

    public void setProjectPlanId(String projectPlanId) {
        this.projectPlanId = projectPlanId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
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

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getRepayTimeType() {
        return repayTimeType;
    }

    public void setRepayTimeType(String repayTimeType) {
        this.repayTimeType = repayTimeType;
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

    public String getRepayUid() {
        return repayUid;
    }

    public void setRepayUid(String repayUid) {
        this.repayUid = repayUid;
    }

    public Integer getRepayAmountType() {
        return repayAmountType;
    }

    public String getRepayUserType() {
        return repayUserType;
    }

    public void setRepayUserType(String repayUserType) {
        this.repayUserType = repayUserType;
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

    @Override
    public String getValue() {

        // TODO adjust
        String[] keys = new String[]{investUid, borrowerId, String.valueOf(repayAmount), orderSeqNo};
        return Joiner.on(",").join(keys);
    }
}
