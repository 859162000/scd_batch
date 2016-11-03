package com.scd.batch.common.entity.statistics.bid;

import java.util.Date;

/**
 * 定期产品到期时间统计项
 */
public class FixProductDuePlanEntity {

    private Date transDate;

    // 定期项目到期本金
    private double fixProjectPrincipal;

    // 定期项目到期利息
    private double fixProjectInterest;

    // 总计
    private double sumAmt;

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public double getFixProjectPrincipal() {
        return fixProjectPrincipal;
    }

    public void setFixProjectPrincipal(double fixProjectPrincipal) {
        this.fixProjectPrincipal = fixProjectPrincipal;
    }

    public double getFixProjectInterest() {
        return fixProjectInterest;
    }

    public void setFixProjectInterest(double fixProjectInterest) {
        this.fixProjectInterest = fixProjectInterest;
    }

    public double getSumAmt() {
        return sumAmt;
    }

    public void setSumAmt(double sumAmt) {
        this.sumAmt = sumAmt;
    }
}
