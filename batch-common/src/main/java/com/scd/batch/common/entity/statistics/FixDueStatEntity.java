package com.scd.batch.common.entity.statistics;

import com.scd.batch.common.entity.Entity;

import java.util.Date;

/**
 * 定期产品到期时间表
 */
public class FixDueStatEntity extends Entity {

    // 到期日期
    private Date dueDate;

    //定期计划到期本金
    private double fixPlanPrincipal;

    // 定期计划到期利息
    private double fixPlanInterest;

    // 定期项目到期本金
    private double fixProjectPrincipal;

    // 定期项目到期利息
    private double fixProjectInterest;

    // 总计
    private double sumAmt;

    public FixDueStatEntity() {

    }

    public FixDueStatEntity(Date dueDate, double fixPlanPrincipal, double fixPlanInterest,
                            double fixProjectPrincipal, double fixProjectInterest, double sumAmt) {
        super();
        this.dueDate = dueDate;
        this.fixPlanPrincipal = fixPlanPrincipal;
        this.fixPlanInterest = fixPlanInterest;
        this.fixProjectPrincipal = fixProjectPrincipal;
        this.fixProjectInterest = fixProjectInterest;
        this.sumAmt = sumAmt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    public double getFixProjectPrincipal() {
        return fixProjectPrincipal;
    }

    public void setFixProjectPrincipal(double fixProjectPrincipal) {
        this.fixProjectPrincipal = fixProjectPrincipal;
    }

    public double getSumAmt() {
        return sumAmt;
    }

    public void setSumAmt(double sumAmt) {
        this.sumAmt = sumAmt;
    }

    public double getFixProjectInterest() {
        return fixProjectInterest;
    }

    public void setFixProjectInterest(double fixProjectInterest) {
        this.fixProjectInterest = fixProjectInterest;
    }
}
