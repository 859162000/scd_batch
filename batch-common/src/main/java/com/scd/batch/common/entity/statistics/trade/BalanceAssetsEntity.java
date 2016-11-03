package com.scd.batch.common.entity.statistics.trade;

import java.util.Date;

/**
 * 账户余额统计项
 */
public class BalanceAssetsEntity {

    // 业务日期
    private Date transDate;

    // 余额
    private double amount;

    // 冻结金额
    private double frozon;

    // 可用余额
    private double usableSa;

    // 提现冻结金额-用于提现
    private double withdrawFreezeSa;

    // 投资冻结金额
    private double investFreezeSa;

    // 还款冻结金额
    private double repayFreezeSa;

    // 活期赎回冻结金额
    private double capitalFreezeSa;

    // 活期本金
    private double currentCapital;

    // 活期待收利息
    private double currentInterest;

    // 定期赚待收本金
    private double fixendCapital;

    // 定期赚待收利息
    private double fixendInterest;

    // 定期计划待收本金
    private double fixperiodCapital;

    // 定期计划待收利息
    private double fixperiodInterest;

    public double getSum() {
        return amount + frozon;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFrozon() {
        return frozon;
    }

    public void setFrozon(double frozon) {
        this.frozon = frozon;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public double getUsableSa() {
        return usableSa;
    }

    public void setUsableSa(double usableSa) {
        this.usableSa = usableSa;
    }

    public double getWithdrawFreezeSa() {
        return withdrawFreezeSa;
    }

    public void setWithdrawFreezeSa(double withdrawFreezeSa) {
        this.withdrawFreezeSa = withdrawFreezeSa;
    }

    public double getInvestFreezeSa() {
        return investFreezeSa;
    }

    public void setInvestFreezeSa(double investFreezeSa) {
        this.investFreezeSa = investFreezeSa;
    }

    public double getRepayFreezeSa() {
        return repayFreezeSa;
    }

    public void setRepayFreezeSa(double repayFreezeSa) {
        this.repayFreezeSa = repayFreezeSa;
    }

    public double getCapitalFreezeSa() {
        return capitalFreezeSa;
    }

    public void setCapitalFreezeSa(double capitalFreezeSa) {
        this.capitalFreezeSa = capitalFreezeSa;
    }

    public double getCurrentCapital() {
        return currentCapital;
    }

    public void setCurrentCapital(double currentCapital) {
        this.currentCapital = currentCapital;
    }

    public double getCurrentInterest() {
        return currentInterest;
    }

    public void setCurrentInterest(double currentInterest) {
        this.currentInterest = currentInterest;
    }

    public double getFixendCapital() {
        return fixendCapital;
    }

    public void setFixendCapital(double fixendCapital) {
        this.fixendCapital = fixendCapital;
    }

    public double getFixendInterest() {
        return fixendInterest;
    }

    public void setFixendInterest(double fixendInterest) {
        this.fixendInterest = fixendInterest;
    }

    public double getFixperiodCapital() {
        return fixperiodCapital;
    }

    public void setFixperiodCapital(double fixperiodCapital) {
        this.fixperiodCapital = fixperiodCapital;
    }

    public double getFixperiodInterest() {
        return fixperiodInterest;
    }

    public void setFixperiodInterest(double fixperiodInterest) {
        this.fixperiodInterest = fixperiodInterest;
    }
}
