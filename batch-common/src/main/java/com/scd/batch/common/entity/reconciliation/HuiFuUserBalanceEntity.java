package com.scd.batch.common.entity.reconciliation;


import com.scd.batch.common.entity.Entity;

/**
 * 汇付账户余额
 */
public class HuiFuUserBalanceEntity extends Entity {

    /**
     * 平台用户id
     */
    private String userCustId;

    /**
     * 可用余额
     */
    private Double avlBal;

    /**
     * 账户资金余额
     */
    private Double acctBal;

    /**
     * 冻结金额
     */
    private Double frzBal;


    public String getUserCustId() {
        return userCustId;
    }

    public void setUserCustId(String userCustId) {
        this.userCustId = userCustId;
    }

    public Double getAvlBal() {
        return avlBal;
    }

    public void setAvlBal(Double avlBal) {
        this.avlBal = avlBal;
    }

    public Double getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(Double acctBal) {
        this.acctBal = acctBal;
    }

    public Double getFrzBal() {
        return frzBal;
    }

    public void setFrzBal(Double frzBal) {
        this.frzBal = frzBal;
    }
}
