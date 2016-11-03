package com.scd.batch.common.entity.acct;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccumulativeProfitEntity {

    private Integer id;

    private String uid;

    private BigDecimal totalProfit;

    private BigDecimal currentInvestProfit;

    public UserAccumulativeProfitEntity() {
    }

    public UserAccumulativeProfitEntity(Integer id, String uid, BigDecimal totalProfit, BigDecimal
            currentInvestProfit) {
        this.id = id;
        this.uid = uid;
        this.totalProfit = totalProfit;
        this.currentInvestProfit = currentInvestProfit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getCurrentInvestProfit() {
        return currentInvestProfit;
    }

    public void setCurrentInvestProfit(BigDecimal currentInvestProfit) {
        this.currentInvestProfit = currentInvestProfit;
    }

}