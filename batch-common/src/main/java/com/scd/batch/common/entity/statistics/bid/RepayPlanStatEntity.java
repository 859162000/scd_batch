package com.scd.batch.common.entity.statistics.bid;

/**
 * 还款计划统计项
 */
public class RepayPlanStatEntity {

    // 项目编码
    private String projectCode;

    // 回款本金
    private Double repayAmount;

    // 回款利息
    private double repayInterest;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Double getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public double getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(double repayInterest) {
        this.repayInterest = repayInterest;
    }
}
