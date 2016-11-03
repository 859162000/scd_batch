package com.scd.batch.common.entity.promotion;

import java.util.Date;

public class CouponsUseRecords {
    /**
     * 使用流水号
     */
    private String seqNo;
    /**
     * 券ID
     */
    private Long couponseId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 券的总面值
     */
    private String totalValue;
    /**
     * 券使用的面值
     */
    private String useValue;

    /**
     * 当前使用面值状态：0-使用成功，1-冻结当前使用面值，2-使用失败，回滚当前使用
     */
    private Byte freezeStatus;
    /**
     * 使用的时间
     */
    private Date useTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public Long getCouponseId() {
        return couponseId;
    }

    public void setCouponseId(Long couponseId) {
        this.couponseId = couponseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUseValue() {
        return useValue;
    }

    public void setUseValue(String useValue) {
        this.useValue = useValue == null ? null : useValue.trim();
    }

    public Byte getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Byte freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }
}