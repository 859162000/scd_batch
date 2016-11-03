package com.scd.batch.common.entity.statistics.bid;

import java.util.Date;

/**
 * 按照项目+期序的还款统计项
 */
public class BorrowerRepayEntity {

    // 到期日期
    private Date repayDate;

    // 项目编号
    private String projectCode;

    // 期序
    private int repayTerm;

    // 还款本金
    private double repayAmount;

    // 还款利息
    private double repayInterest;

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getRepayTerm() {
        return repayTerm;
    }

    public void setRepayTerm(int repayTerm) {
        this.repayTerm = repayTerm;
    }

    public double getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(double repayAmount) {
        this.repayAmount = repayAmount;
    }

    public double getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(double repayInterest) {
        this.repayInterest = repayInterest;
    }
}
