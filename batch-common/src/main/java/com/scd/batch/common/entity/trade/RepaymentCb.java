package com.scd.batch.common.entity.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepaymentCb implements Serializable{
    private Integer id;

    private String froSeqNo;

    private String prodId;

    private String prodType;

    private String prodName;

    private String orderId;

    private String orderDate;

    private String outType;

    private String outUid;

    private String inUid;

    private String inType;

    private BigDecimal transAmt;

    private BigDecimal principalAmt;

    private BigDecimal interestAmt;

    private BigDecimal supInterestAmt;

    private String status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFroSeqNo() {
        return froSeqNo;
    }

    public void setFroSeqNo(String froSeqNo) {
        this.froSeqNo = froSeqNo == null ? null : froSeqNo.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
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

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType == null ? null : outType.trim();
    }

    public String getOutUid() {
        return outUid;
    }

    public void setOutUid(String outUid) {
        this.outUid = outUid == null ? null : outUid.trim();
    }

    public String getInUid() {
        return inUid;
    }

    public void setInUid(String inUid) {
        this.inUid = inUid;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType == null ? null : inType.trim();
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public BigDecimal getPrincipalAmt() {
        return principalAmt;
    }

    public void setPrincipalAmt(BigDecimal principalAmt) {
        this.principalAmt = principalAmt;
    }

    public BigDecimal getInterestAmt() {
        return interestAmt;
    }

    public void setInterestAmt(BigDecimal interestAmt) {
        this.interestAmt = interestAmt;
    }

    public BigDecimal getSupInterestAmt() {
        return supInterestAmt;
    }

    public void setSupInterestAmt(BigDecimal supInterestAmt) {
        this.supInterestAmt = supInterestAmt;
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
        final StringBuffer sb = new StringBuffer("RepaymentCb{");
        sb.append("id=").append(id);
        sb.append(", froSeqNo='").append(froSeqNo).append('\'');
        sb.append(", prodId='").append(prodId).append('\'');
        sb.append(", prodType='").append(prodType).append('\'');
        sb.append(", prodName='").append(prodName).append('\'');
        sb.append(", orderId='").append(orderId).append('\'');
        sb.append(", orderDate='").append(orderDate).append('\'');
        sb.append(", outType='").append(outType).append('\'');
        sb.append(", outUid='").append(outUid).append('\'');
        sb.append(", inUid='").append(inUid).append('\'');
        sb.append(", inType='").append(inType).append('\'');
        sb.append(", transAmt=").append(transAmt);
        sb.append(", principalAmt=").append(principalAmt);
        sb.append(", interestAmt=").append(interestAmt);
        sb.append(", supInterestAmt=").append(supInterestAmt);
        sb.append(", status='").append(status).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }
}