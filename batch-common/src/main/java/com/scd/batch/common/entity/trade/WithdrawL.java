package com.scd.batch.common.entity.trade;

import com.scd.batch.common.entity.reconciliation.TransferValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WithdrawL implements Serializable, TransferValue {

    private Integer id;

    private String seqNo;

    private String uid;

    private String userCustId;

    private String remark;

    private String pageType;

    private String responseCode;

    private String responseDesc;

    private BigDecimal transAmt;

    private String withdrawType;

    private BigDecimal arriveAmt;

    private String orderId;

    private String openAcctId;

    private String openBankId;

    private BigDecimal feeAmt;

    private String feeCustId;

    private String feeAcctId;

    private BigDecimal serviceFee;

    private String serviceFeeAcctId;

    private String returnUrl;

    private String bgReturnUrl;

    private Date createTime;

    private Date updateTime;

    private String status;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType == null ? null : pageType.trim();
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode == null ? null : responseCode.trim();
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc == null ? null : responseDesc.trim();
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType == null ? null : withdrawType.trim();
    }

    public BigDecimal getArriveAmt() {
        return arriveAmt;
    }

    public void setArriveAmt(BigDecimal arriveAmt) {
        this.arriveAmt = arriveAmt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String openAcctId) {
        this.openAcctId = openAcctId == null ? null : openAcctId.trim();
    }

    public String getOpenBankId() {
        return openBankId;
    }

    public void setOpenBankId(String openBankId) {
        this.openBankId = openBankId == null ? null : openBankId.trim();
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

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getServiceFeeAcctId() {
        return serviceFeeAcctId;
    }

    public void setServiceFeeAcctId(String serviceFeeAcctId) {
        this.serviceFeeAcctId = serviceFeeAcctId == null ? null : serviceFeeAcctId.trim();
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public String getBgReturnUrl() {
        return bgReturnUrl;
    }

    public void setBgReturnUrl(String bgReturnUrl) {
        this.bgReturnUrl = bgReturnUrl == null ? null : bgReturnUrl.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String getValue() {
        // TODO
        return userCustId + "|" + transAmt + "|" + feeAcctId;
    }
}
