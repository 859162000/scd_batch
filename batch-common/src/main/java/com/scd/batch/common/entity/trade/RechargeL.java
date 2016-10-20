package com.scd.batch.common.entity.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RechargeL implements Serializable {
    private Integer id;

    private String seqNo;

    private String uid;

    private String userCustId;

    private BigDecimal amt;

    private BigDecimal arriveAmt;

    private String rechargeType;

    private String orderId;

    private String orderDate;

    private String gateBusinessId;

    private String openBankId;

    private String dcFlag;

    private String returnUrl;

    private String openAcctId;

    private String idCard;

    private String pageType;

    private BigDecimal feeAmt;

    private String feeCustId;

    private String feeAcctId;

    private Date createTime;

    private Date updateTime;

    private Date startTime;

    private Date finishTime;

    private String reworkYn;

    private String status;

    private String rechargeManYn;

    private String rechargeManReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUserCustId() {
        return userCustId;
    }

    public void setUserCustId(String userCustId) {
        this.userCustId = userCustId == null ? null : userCustId.trim();
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getArriveAmt() {
        return arriveAmt;
    }

    public void setArriveAmt(BigDecimal arriveAmt) {
        this.arriveAmt = arriveAmt;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType == null ? null : rechargeType.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate == null ? null : orderDate.trim();
    }

    public String getGateBusinessId() {
        return gateBusinessId;
    }

    public void setGateBusinessId(String gateBusinessId) {
        this.gateBusinessId = gateBusinessId == null ? null : gateBusinessId.trim();
    }

    public String getOpenBankId() {
        return openBankId;
    }

    public void setOpenBankId(String openBankId) {
        this.openBankId = openBankId == null ? null : openBankId.trim();
    }

    public String getDcFlag() {
        return dcFlag;
    }

    public void setDcFlag(String dcFlag) {
        this.dcFlag = dcFlag == null ? null : dcFlag.trim();
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String openAcctId) {
        this.openAcctId = openAcctId == null ? null : openAcctId.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType == null ? null : pageType.trim();
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getFeeCustId() {
        return feeCustId;
    }

    public void setFeeCustId(String feeCustId) {
        this.feeCustId = feeCustId == null ? null : feeCustId.trim();
    }

    public String getFeeAcctId() {
        return feeAcctId;
    }

    public void setFeeAcctId(String feeAcctId) {
        this.feeAcctId = feeAcctId == null ? null : feeAcctId.trim();
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getReworkYn() {
        return reworkYn;
    }

    public void setReworkYn(String reworkYn) {
        this.reworkYn = reworkYn == null ? null : reworkYn.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRechargeManYn() {
        return rechargeManYn;
    }

    public void setRechargeManYn(String rechargeManYn) {
        this.rechargeManYn = rechargeManYn == null ? null : rechargeManYn.trim();
    }

    public String getRechargeManReason() {
        return rechargeManReason;
    }

    public void setRechargeManReason(String rechargeManReason) {
        this.rechargeManReason = rechargeManReason == null ? null : rechargeManReason.trim();
    }
}
