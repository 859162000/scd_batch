package com.scd.batch.common.entity.bid;

import com.scd.batch.common.entity.Entity;


public class ProjectLoanEntity extends Entity {

    private String projectCode;

    private double tradeAmount;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

}
