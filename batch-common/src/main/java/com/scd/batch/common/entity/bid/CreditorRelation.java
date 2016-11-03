package com.scd.batch.common.entity.bid;

import java.io.Serializable;
import java.util.Date;

public class CreditorRelation implements Serializable {

    private Long id;

    /**
     * 认购交易单号
     */
    private String orderSeqNo;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 前一个交易单号
     */
    private String previousSeqNo;

    /**
     * 交易流水号
     */
    private String orderFlowNo;

    /**
     * 前一个交易流水号
     */
    private String previousFlowNo;

    /**
     * 购买者Id
     */
    private String buyerUid;

    /**
     * 出让者Id
     */
    private String sellerUid;

    /**
     * 借款人Id
     */
    private String borrowerId;

    /**
     * 剩余金额
     */
    private Double remainAmount;

    /**
     * 投资类型
     */
    private Integer investType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 交易金额
     */
    private Double tradeAmount;

    /**
     * 已收本金
     */
    private Double receiptedAmount;

    /**
     *已转出本金
     */
    private Double transferredAmount;

    /**
     * 已收利息
     */
    private Double receiptedInterest;

    /**
     * 总利息
     */
    private Double totalInterest;


    /**
     * 第几手
     */
    private Integer handTimes;

    private Integer dataStatus;

    /**
     * 交易时间
     */
    private Date tradeTime;
    /**
     *产品类型
     */
    private Integer productType;

    /**
     *原始认购单号
     */
    private String originFlowNo;


    /**
     *交易类型
     */
    private Integer tradeType;

    /**
     * 计息开始时间
     */
    private Date interestDate;

    /**
     * 利率 TODO
     */
    private Double interestRate;
    /**
     * 到期时间 TODO
     */
    private Date expireDate;

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getInvestType() {
        return investType;
    }

    public void setInvestType(Integer investType) {
        this.investType = investType;
    }

    public String getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(String buyerUid) {
        this.buyerUid = buyerUid;
    }

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSeqNo() {
        return orderSeqNo;
    }

    public void setOrderSeqNo(String orderSeqNo) {
        this.orderSeqNo = orderSeqNo == null ? null : orderSeqNo.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getPreviousSeqNo() {
        return previousSeqNo;
    }

    public void setPreviousSeqNo(String previousSeqNo) {
        this.previousSeqNo = previousSeqNo == null ? null : previousSeqNo.trim();
    }

    public Double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Double remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }



    public Integer getHandTimes() {
        return handTimes;
    }

    public void setHandTimes(Integer handTimes) {
        this.handTimes = handTimes;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getOrderFlowNo() {
        return orderFlowNo;
    }

    public void setOrderFlowNo(String orderFlowNo) {
        this.orderFlowNo = orderFlowNo;
    }

    public String getPreviousFlowNo() {
        return previousFlowNo;
    }

    public void setPreviousFlowNo(String previousFlowNo) {
        this.previousFlowNo = previousFlowNo;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getOriginFlowNo() {
        return originFlowNo;
    }

    public void setOriginFlowNo(String originFlowNo) {
        this.originFlowNo = originFlowNo;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Double getReceiptedAmount() {
        return receiptedAmount;
    }

    public void setReceiptedAmount(Double receiptedAmount) {
        this.receiptedAmount = receiptedAmount;
    }

    public Double getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(Double transferredAmount) {
        this.transferredAmount = transferredAmount;
    }

    public Double getReceiptedInterest() {
        return receiptedInterest;
    }

    public void setReceiptedInterest(Double receiptedInterest) {
        this.receiptedInterest = receiptedInterest;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }
}