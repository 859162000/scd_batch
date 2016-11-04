package com.scd.batch.interest.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户收益实体
 */
public class UserProfitEntity {

    private Integer id;

    private String uid;

    private Date date;

    // 昨日收益
    private BigDecimal profit;

    // 活期收益
    private BigDecimal currentProfit;

    // 累计昨日收益
    private BigDecimal totalProfit;

    // 累计活期收益
    private BigDecimal currentInvestProfit;

    public UserProfitEntity() {
    }

    public UserProfitEntity(Integer id, String uid, Date date, BigDecimal profit, BigDecimal currentProfit,
                            BigDecimal totalProfit, BigDecimal currentInvestProfit) {
        this.id = id;
        this.uid = uid;
        this.date = date;
        this.profit = profit;
        this.currentProfit = currentProfit;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
