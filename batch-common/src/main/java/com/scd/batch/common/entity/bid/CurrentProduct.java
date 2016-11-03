package com.scd.batch.common.entity.bid;

import java.io.Serializable;

/**
 * 活期产品
 */
public class CurrentProduct extends Product implements Serializable {
    /**
     * 自增id
     */
    private Long id;
    /**
     *产品编码
     */
    private String productCode;

    private String targetProjectId;
    /**
     *每天赎回次数限制
     */
    private Integer redemCountPerDay;
    /**
     *每次赎回金额限制
     */
    private Double redemAmountPerTime;
    /**
     *每月赎回金额限制
     */
    private Double redemAmountPerMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetProjectId() {
        return targetProjectId;
    }

    public void setTargetProjectId(String targetProjectId) {
        this.targetProjectId = targetProjectId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Integer getRedemCountPerDay() {
        return redemCountPerDay;
    }

    public void setRedemCountPerDay(Integer redemCountPerDay) {
        this.redemCountPerDay = redemCountPerDay;
    }

    public Double getRedemAmountPerTime() {
        return redemAmountPerTime;
    }

    public void setRedemAmountPerTime(Double redemAmountPerTime) {
        this.redemAmountPerTime = redemAmountPerTime;
    }

    public Double getRedemAmountPerMonth() {
        return redemAmountPerMonth;
    }

    public void setRedemAmountPerMonth(Double redemAmountPerMonth) {
        this.redemAmountPerMonth = redemAmountPerMonth;
    }

    @Override
    public String toString() {
        return "CurrentProduct{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", redemCountPerDay=" + redemCountPerDay +
                ", redemAmountPerTime=" + redemAmountPerTime +
                ", redemAmountPerMonth=" + redemAmountPerMonth +
                '}';
    }
}