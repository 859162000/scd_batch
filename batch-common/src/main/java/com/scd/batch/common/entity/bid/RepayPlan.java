package com.scd.batch.common.entity.bid;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by taomengchun on 16/9/8.
 */
public class RepayPlan implements Serializable {

    private Long id;
    /**
     * 项目编码
     */
    private String projectCode;
    /**
     * 回款计划编号
     */
    private String planId;
    /**
     * 回款期数
     */
    private Integer repayTerm;
    /**
     * 回款日期
     */
    private Date repayDate;
    /**
     * 回款本金
     */
    private Double repayAmount;
    /**
     * 回款利息
     */
    private double repayInterest;
    /**
     * 当期债权款
     */
    private Double currentDebtAmount;
    /**
     * 当期保证金
     */
    private Double currentVouchAmount;
    /**
     * 当期合计
     */
    private Double currentSumAmount;
    /**
     * 币种:0-人民币，1-美元，2-欧元，3-其他币种
     */
    private Integer currency;

//    private RepayReal repayReal;

    /**
     * 是否有效
     */
    private Integer dataStatus;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Integer getRepayTerm() {
        return repayTerm;
    }

    public void setRepayTerm(Integer repayTerm) {
        this.repayTerm = repayTerm;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
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

    public Double getCurrentDebtAmount() {
        return currentDebtAmount;
    }

    public void setCurrentDebtAmount(Double currentDebtAmount) {
        this.currentDebtAmount = currentDebtAmount;
    }

    public Double getCurrentVouchAmount() {
        return currentVouchAmount;
    }

    public void setCurrentVouchAmount(Double currentVouchAmount) {
        this.currentVouchAmount = currentVouchAmount;
    }

    public Double getCurrentSumAmount() {
        return currentSumAmount;
    }

    public void setCurrentSumAmount(Double currentSumAmount) {
        this.currentSumAmount = currentSumAmount;
    }



    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    public RepayReal getRepayReal() {
//        return repayReal;
//    }
//
//    public void setRepayReal(RepayReal repayReal) {
//        this.repayReal = repayReal;
//    }

    public Integer getCurrency() {
        return currency;
    }
}
