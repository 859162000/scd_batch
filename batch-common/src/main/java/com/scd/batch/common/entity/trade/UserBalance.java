package com.scd.batch.common.entity.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserBalance implements Serializable{
    private Integer id;

    private String uid;

    private String operateType;

    private BigDecimal usableSa;

    private BigDecimal withdrawFreezeSa;

    private BigDecimal investFreezeSa;

    private BigDecimal repayFreezeSa;

    private BigDecimal currentCapital;

    private BigDecimal currentInterest;

    private BigDecimal currentTotalCapital;

    private BigDecimal currentTotalInterest;

    private BigDecimal fixendCapital;

    private BigDecimal fixendInterest;

    private BigDecimal fixendTotalCapital;

    private BigDecimal fixendTotalInterest;

    private BigDecimal fixperiodCapital;

    private BigDecimal fixperiodInterest;

    private BigDecimal fixperiodTotalCapital;

    private BigDecimal fixperiodTotalInterest;

    private BigDecimal rechargeTotalAmt;

    private Integer rechargeTotalCount;

    private BigDecimal withdrawTotalAmt;

    private Integer withdrawTotalCount;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public BigDecimal getUsableSa() {
        return usableSa;
    }

    public void setUsableSa(BigDecimal usableSa) {
        this.usableSa = usableSa;
    }

    public BigDecimal getWithdrawFreezeSa() {
        return withdrawFreezeSa;
    }

    public void setWithdrawFreezeSa(BigDecimal withdrawFreezeSa) {
        this.withdrawFreezeSa = withdrawFreezeSa;
    }

    public BigDecimal getInvestFreezeSa() {
        return investFreezeSa;
    }

    public void setInvestFreezeSa(BigDecimal investFreezeSa) {
        this.investFreezeSa = investFreezeSa;
    }

    public BigDecimal getRepayFreezeSa() {
        return repayFreezeSa;
    }

    public void setRepayFreezeSa(BigDecimal repayFreezeSa) {
        this.repayFreezeSa = repayFreezeSa;
    }

    public BigDecimal getCurrentCapital() {
        return currentCapital;
    }

    public void setCurrentCapital(BigDecimal currentCapital) {
        this.currentCapital = currentCapital;
    }

    public BigDecimal getCurrentInterest() {
        return currentInterest;
    }

    public void setCurrentInterest(BigDecimal currentInterest) {
        this.currentInterest = currentInterest;
    }

    public BigDecimal getCurrentTotalCapital() {
        return currentTotalCapital;
    }

    public void setCurrentTotalCapital(BigDecimal currentTotalCapital) {
        this.currentTotalCapital = currentTotalCapital;
    }

    public BigDecimal getCurrentTotalInterest() {
        return currentTotalInterest;
    }

    public void setCurrentTotalInterest(BigDecimal currentTotalInterest) {
        this.currentTotalInterest = currentTotalInterest;
    }

    public BigDecimal getFixendCapital() {
        return fixendCapital;
    }

    public void setFixendCapital(BigDecimal fixendCapital) {
        this.fixendCapital = fixendCapital;
    }

    public BigDecimal getFixendInterest() {
        return fixendInterest;
    }

    public void setFixendInterest(BigDecimal fixendInterest) {
        this.fixendInterest = fixendInterest;
    }

    public BigDecimal getFixendTotalCapital() {
        return fixendTotalCapital;
    }

    public void setFixendTotalCapital(BigDecimal fixendTotalCapital) {
        this.fixendTotalCapital = fixendTotalCapital;
    }

    public BigDecimal getFixendTotalInterest() {
        return fixendTotalInterest;
    }

    public void setFixendTotalInterest(BigDecimal fixendTotalInterest) {
        this.fixendTotalInterest = fixendTotalInterest;
    }

    public BigDecimal getFixperiodCapital() {
        return fixperiodCapital;
    }

    public void setFixperiodCapital(BigDecimal fixperiodCapital) {
        this.fixperiodCapital = fixperiodCapital;
    }

    public BigDecimal getFixperiodInterest() {
        return fixperiodInterest;
    }

    public void setFixperiodInterest(BigDecimal fixperiodInterest) {
        this.fixperiodInterest = fixperiodInterest;
    }

    public BigDecimal getFixperiodTotalCapital() {
        return fixperiodTotalCapital;
    }

    public void setFixperiodTotalCapital(BigDecimal fixperiodTotalCapital) {
        this.fixperiodTotalCapital = fixperiodTotalCapital;
    }

    public BigDecimal getFixperiodTotalInterest() {
        return fixperiodTotalInterest;
    }

    public void setFixperiodTotalInterest(BigDecimal fixperiodTotalInterest) {
        this.fixperiodTotalInterest = fixperiodTotalInterest;
    }

    public BigDecimal getRechargeTotalAmt() {
        return rechargeTotalAmt;
    }

    public void setRechargeTotalAmt(BigDecimal rechargeTotalAmt) {
        this.rechargeTotalAmt = rechargeTotalAmt;
    }

    public Integer getRechargeTotalCount() {
        return rechargeTotalCount;
    }

    public void setRechargeTotalCount(Integer rechargeTotalCount) {
        this.rechargeTotalCount = rechargeTotalCount;
    }

    public BigDecimal getWithdrawTotalAmt() {
        return withdrawTotalAmt;
    }

    public void setWithdrawTotalAmt(BigDecimal withdrawTotalAmt) {
        this.withdrawTotalAmt = withdrawTotalAmt;
    }

    public Integer getWithdrawTotalCount() {
        return withdrawTotalCount;
    }

    public void setWithdrawTotalCount(Integer withdrawTotalCount) {
        this.withdrawTotalCount = withdrawTotalCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
