package com.scd.batch.common.entity.acct;

import java.math.BigDecimal;
import java.util.Date;

public class UserDailyProfitEntity {

    private Integer id;

    private String uid;

    private String date;

    // 每日总收益
    private BigDecimal profit;

    // 每日活期收益
    private BigDecimal currentProfit;

    public UserDailyProfitEntity() {
    }

    public UserDailyProfitEntity(Integer id, String uid, String date, BigDecimal profit, BigDecimal currentProfit) {
        this.id = id;
        this.uid = uid;
        this.date = date;
        this.profit = profit;
        this.currentProfit = currentProfit;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getCurrentProfit() {
        return currentProfit;
    }

    public void setCurrentProfit(BigDecimal currentProfit) {
        this.currentProfit = currentProfit;
    }
}