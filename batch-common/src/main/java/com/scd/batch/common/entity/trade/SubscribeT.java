package com.scd.batch.common.entity.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SubscribeT implements Serializable{
    private Integer id;

    private String seqNo;

    private String freezeSeqNo;

    private String uid;

    private String userCustId;

    private String mobile;

    private String productType;

    private String productBitType;

    private String productCode;

    private String productName;

    private BigDecimal productRate;

    private String productChannel;

    private Integer productPeriod;

    private Date productExpireTime;

    private Date productStartTime;

    private Date productFinishTime;

    private BigDecimal smallCount;

    private BigDecimal bigCount;

    private BigDecimal unitCount;

    private String interestType;

    private String repayType;

    private Long ticketId;

    private Long ticketDistriId;

    private String ticketType;

    private String ticketMethod;

    private BigDecimal ticketCount;

    private BigDecimal amount;

    private BigDecimal actualAmount;

    private BigDecimal ticketReduceAmount;

    private String status;

    private Date createTime;

    private Date updateTime;

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

    public String getFreezeSeqNo() {
        return freezeSeqNo;
    }

    public void setFreezeSeqNo(String freezeSeqNo) {
        this.freezeSeqNo = freezeSeqNo == null ? null : freezeSeqNo.trim();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getProductBitType() {
        return productBitType;
    }

    public void setProductBitType(String productBitType) {
        this.productBitType = productBitType == null ? null : productBitType.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getProductRate() {
        return productRate;
    }

    public void setProductRate(BigDecimal productRate) {
        this.productRate = productRate;
    }

    public String getProductChannel() {
        return productChannel;
    }

    public void setProductChannel(String productChannel) {
        this.productChannel = productChannel == null ? null : productChannel.trim();
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public Date getProductExpireTime() {
        return productExpireTime;
    }

    public void setProductExpireTime(Date productExpireTime) {
        this.productExpireTime = productExpireTime;
    }

    public Date getProductStartTime() {
        return productStartTime;
    }

    public void setProductStartTime(Date productStartTime) {
        this.productStartTime = productStartTime;
    }

    public Date getProductFinishTime() {
        return productFinishTime;
    }

    public void setProductFinishTime(Date productFinishTime) {
        this.productFinishTime = productFinishTime;
    }

    public BigDecimal getSmallCount() {
        return smallCount;
    }

    public void setSmallCount(BigDecimal smallCount) {
        this.smallCount = smallCount;
    }

    public BigDecimal getBigCount() {
        return bigCount;
    }

    public void setBigCount(BigDecimal bigCount) {
        this.bigCount = bigCount;
    }

    public BigDecimal getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(BigDecimal unitCount) {
        this.unitCount = unitCount;
    }

    public String getInterestType() {
        return interestType;
    }

    public void setInterestType(String interestType) {
        this.interestType = interestType == null ? null : interestType.trim();
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType == null ? null : repayType.trim();
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTicketDistriId() {
        return ticketDistriId;
    }

    public void setTicketDistriId(Long ticketDistriId) {
        this.ticketDistriId = ticketDistriId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType == null ? null : ticketType.trim();
    }

    public String getTicketMethod() {
        return ticketMethod;
    }

    public void setTicketMethod(String ticketMethod) {
        this.ticketMethod = ticketMethod == null ? null : ticketMethod.trim();
    }

    public BigDecimal getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(BigDecimal ticketCount) {
        this.ticketCount = ticketCount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getTicketReduceAmount() {
        return ticketReduceAmount;
    }

    public void setTicketReduceAmount(BigDecimal ticketReduceAmount) {
        this.ticketReduceAmount = ticketReduceAmount;
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
        return "SubscribeT{" +
                "id=" + id +
                ", seqNo='" + seqNo + '\'' +
                ", freezeSeqNo='" + freezeSeqNo + '\'' +
                ", uid='" + uid + '\'' +
                ", userCustId='" + userCustId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", productType='" + productType + '\'' +
                ", productBitType='" + productBitType + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productRate=" + productRate +
                ", productChannel='" + productChannel + '\'' +
                ", productPeriod=" + productPeriod +
                ", productExpireTime=" + productExpireTime +
                ", productStartTime=" + productStartTime +
                ", productFinishTime=" + productFinishTime +
                ", smallCount=" + smallCount +
                ", bigCount=" + bigCount +
                ", unitCount=" + unitCount +
                ", interestType='" + interestType + '\'' +
                ", repayType='" + repayType + '\'' +
                ", ticketId=" + ticketId +
                ", ticketDistriId=" + ticketDistriId +
                ", ticketType='" + ticketType + '\'' +
                ", ticketMethod='" + ticketMethod + '\'' +
                ", ticketCount=" + ticketCount +
                ", amount=" + amount +
                ", actualAmount=" + actualAmount +
                ", ticketReduceAmount=" + ticketReduceAmount +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}