package com.scd.batch.common.entity.statistics.bid;

import java.util.Date;

/**
 * 定期计划到期时间统计项
 */
public class FixPlanDuePlanEntity {

    private Date transDate;

    //定期计划到期本金
    private double fixPlanPrincipal;

    // 定期计划到期利息
    private double fixPlanInterest;

    // 总计
    private double sumAmt;

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public double getFixPlanPrincipal() {
        return fixPlanPrincipal;
    }

    public void setFixPlanPrincipal(double fixPlanPrincipal) {
        this.fixPlanPrincipal = fixPlanPrincipal;
    }

    public double getFixPlanInterest() {
        return fixPlanInterest;
    }

    public void setFixPlanInterest(double fixPlanInterest) {
        this.fixPlanInterest = fixPlanInterest;
    }

    public double getSumAmt() {
        return sumAmt;
    }

    public void setSumAmt(double sumAmt) {
        this.sumAmt = sumAmt;
    }
}
