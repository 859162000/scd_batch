package com.scd.batch.common.entity.statistics;

import com.scd.batch.common.entity.Entity;

import java.util.Date;

/**
 * 融资方还款计划表
 */
public class BorrowerRepayPlanStatEntity extends Entity {

    // 到期日
    private Date dueDate;

    // 融资方ID
    private long borrowerId;

    // 融资方名称
    private String borrowerName;

    // 项目编号
    private String projectCode;

    //项目名称
    private String projectName;

    // 还款方式
    private int repayType;

    // 到期本金
    private double duePrincipal;

    // 到期利息
    private double dueInterest;

    // 到期本息合计
    private double dueSum;

    // 实际还款本金
    private double repayPrincipal;

    // 实际还款利息
    private double repayInterest;

    // 实际还款汇总
    private double repaySum;

    public BorrowerRepayPlanStatEntity() {

    }

    public BorrowerRepayPlanStatEntity(Date dueDate, long borrowerId, String borrowerName,
                                       String projectCode, String projectName,
                                       int repayType, double duePrincipal,
                                       double dueInterest, double dueSum,
                                       double repayPrincipal, double repayInterest, double repaySum) {

        super();
        this.dueDate = dueDate;
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.repayType = repayType;
        this.duePrincipal = duePrincipal;
        this.dueInterest = dueInterest;
        this.dueSum = dueSum;
        this.repayPrincipal = repayPrincipal;
        this.repayInterest = repayInterest;
        this.repaySum = repaySum;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getRepayType() {
        return repayType;
    }

    public void setRepayType(int repayType) {
        this.repayType = repayType;
    }

    public double getDuePrincipal() {
        return duePrincipal;
    }

    public void setDuePrincipal(double duePrincipal) {
        this.duePrincipal = duePrincipal;
    }

    public double getDueInterest() {
        return dueInterest;
    }

    public void setDueInterest(double dueInterest) {
        this.dueInterest = dueInterest;
    }

    public double getDueSum() {
        return dueSum;
    }

    public void setDueSum(double dueSum) {
        this.dueSum = dueSum;
    }

    public double getRepayPrincipal() {
        return repayPrincipal;
    }

    public void setRepayPrincipal(double repayPrincipal) {
        this.repayPrincipal = repayPrincipal;
    }

    public double getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(double repayInterest) {
        this.repayInterest = repayInterest;
    }

    public double getRepaySum() {
        return repaySum;
    }

    public void setRepaySum(double repaySum) {
        this.repaySum = repaySum;
    }
}
