package com.scd.batch.common.entity.statistics;

import com.scd.batch.common.entity.Entity;

import java.util.Date;

/**
 * 平台资产管理规模报表，
 * 流动性产品、定期计划和定期项目的金额皆为投资人实际持有的资产总额
 * “财主账户余额”=所有的账户余额（不含冻结金额）-借款人借款后未提现金额
 */
public class AssetsStatEntity extends Entity {

    // 日期
    private Date transDate;

    // 流动性产品
    private double current;

    // 定期计划
    private double fixPlan;

    // 定期项目
    private double fixProject;

    // 财主账户余额，不包含【冻结金额】
    private double balance;

    // 冻结金额
    private double frozen;

    // 合计
    private double sum;

    public AssetsStatEntity() {
    }

    public AssetsStatEntity(Date transDate, double current, double fixPlan, double fixProject,
                            double balance, double frozen, double sum) {
        super();
        this.transDate = transDate;
        this.current = current;
        this.fixPlan = fixPlan;
        this.fixProject = fixProject;
        this.balance = balance;
        this.frozen = frozen;
        this.sum = sum;
    }


    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getFixPlan() {
        return fixPlan;
    }

    public void setFixPlan(double fixPlan) {
        this.fixPlan = fixPlan;
    }

    public double getFixProject() {
        return fixProject;
    }

    public void setFixProject(double fixProject) {
        this.fixProject = fixProject;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getFrozen() {
        return frozen;
    }

    public void setFrozen(double frozen) {
        this.frozen = frozen;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
