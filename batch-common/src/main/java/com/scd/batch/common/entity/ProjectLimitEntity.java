package com.scd.batch.common.entity;

/**
 * 项目额度报表
 */
public class ProjectLimitEntity extends Entity {

    // 项目编号
    private String projectCode;

    // 项目标名称
    private String projectName;

    // 项目计划融资总额度
    private double total;

    // 已发标额度
    private double bid;

    // 剩余可发标额度
    private double unbid;

    // 已提现金额
    private double withdraw;

    // 已还本金
    private double paidPrincipal;

    // 未还本金
    private double unpaidPrincipal;

    // 已还利息
    private double paidInterest;

    // 未还利息
    private double unpaidInterest;

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getUnbid() {
        return unbid;
    }

    public void setUnbid(double unbid) {
        this.unbid = unbid;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }

    public double getPaidPrincipal() {
        return paidPrincipal;
    }

    public void setPaidPrincipal(double paidPrincipal) {
        this.paidPrincipal = paidPrincipal;
    }

    public double getUnpaidPrincipal() {
        return unpaidPrincipal;
    }

    public void setUnpaidPrincipal(double unpaidPrincipal) {
        this.unpaidPrincipal = unpaidPrincipal;
    }

    public double getPaidInterest() {
        return paidInterest;
    }

    public void setPaidInterest(double paidInterest) {
        this.paidInterest = paidInterest;
    }

    public double getUnpaidInterest() {
        return unpaidInterest;
    }

    public void setUnpaidInterest(double unpaidInterest) {
        this.unpaidInterest = unpaidInterest;
    }
}
