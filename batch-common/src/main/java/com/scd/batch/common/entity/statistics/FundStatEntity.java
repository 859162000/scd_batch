package com.scd.batch.common.entity.statistics;

import com.scd.batch.common.entity.Entity;

import java.util.Date;

/**
 * 财主每日充值提现报表
 */
public class FundStatEntity extends Entity {

    // 业务日期
    private Date transDate;

    // 充值金额
    private double recharge;

    // 提现金额
    private double withdraw;

    public FundStatEntity() {

    }

    public FundStatEntity(Date transDate, double recharge, double withdraw) {
        super();
        this.transDate = transDate;
        this.recharge = recharge;
        this.withdraw = withdraw;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public double getRecharge() {
        return recharge;
    }

    public void setRecharge(double recharge) {
        this.recharge = recharge;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }
}
