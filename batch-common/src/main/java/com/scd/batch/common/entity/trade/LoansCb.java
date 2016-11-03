package com.scd.batch.common.entity.trade;

import com.scd.batch.common.entity.reconciliation.TransferValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoansCb implements Serializable, TransferValue {
    private Integer id;

    private String loansSeqNo;

    private String seqNo;

    private String orderId;

    private String orderDate;

    private String investUid;

    private String borrUid;

    private BigDecimal transAmt;

    private BigDecimal feeAmt;

    private BigDecimal realAmt;

    private String status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoansSeqNo() {
        return loansSeqNo;
    }

    public void setLoansSeqNo(String loansSeqNo) {
        this.loansSeqNo = loansSeqNo == null ? null : loansSeqNo.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
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

    public String getInvestUid() {
        return investUid;
    }

    public void setInvestUid(String investUid) {
        this.investUid = investUid == null ? null : investUid.trim();
    }

    public String getBorrUid() {
        return borrUid;
    }

    public void setBorrUid(String borrUid) {
        this.borrUid = borrUid == null ? null : borrUid.trim();
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public BigDecimal getRealAmt() {
        return realAmt;
    }

    public void setRealAmt(BigDecimal realAmt) {
        this.realAmt = realAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    @Override
    public String toString() {
        return "LoansCb{" +
                "id=" + id +
                ", loansSeqNo='" + loansSeqNo + '\'' +
                ", seqNo='" + seqNo + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", investUid='" + investUid + '\'' +
                ", borrUid='" + borrUid + '\'' +
                ", transAmt=" + transAmt +
                ", feeAmt=" + feeAmt +
                ", realAmt=" + realAmt +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public String getValue() {

        // TODO
        return null;
    }
}