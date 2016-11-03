package com.scd.batch.common.entity.trade;

import java.math.BigDecimal;
import java.util.Date;

public class RedeemT {
    private Integer id;

    private String seqNo;

    private String freezeSeqNo;

    private String uid;

    private String userCustId;

    private String productType;

    private String productCode;

    private BigDecimal redeemAmount;

    private BigDecimal redemSrvFee;

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public BigDecimal getRedeemAmount() {
        return redeemAmount;
    }

    public void setRedeemAmount(BigDecimal redeemAmount) {
        this.redeemAmount = redeemAmount;
    }

    public BigDecimal getRedemSrvFee() {
        return redemSrvFee;
    }

    public void setRedemSrvFee(BigDecimal redemSrvFee) {
        this.redemSrvFee = redemSrvFee;
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
        return "RedeemT{" +
                "id=" + id +
                ", seqNo='" + seqNo + '\'' +
                ", freezeSeqNo='" + freezeSeqNo + '\'' +
                ", uid='" + uid + '\'' +
                ", userCustId='" + userCustId + '\'' +
                ", productType='" + productType + '\'' +
                ", productCode='" + productCode + '\'' +
                ", redeemAmount=" + redeemAmount +
                ", redemSrvFee=" + redemSrvFee +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}