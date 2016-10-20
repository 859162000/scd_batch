package com.scd.batch.common.entity.reconciliation;

import constant.AccountLevelEnum;
import constant.AccountType;

/**
 * Created by liwei on 2016-10-14.
 */
public class AccountBalanceEntity {

    // 账号级别
    private AccountLevelEnum accountLevelEnum;

    // 账号类型
    private AccountType accountType;

    // 账号ID
    private String accountId;

    // 可用余额
    private double avlBal;

    // 账户余额
    private double acctBal;

    // 冻结余额
    private double frzBal;
    

    public AccountLevelEnum getAccountLevelEnum() {
        return accountLevelEnum;
    }

    public void setAccountLevelEnum(AccountLevelEnum accountLevelEnum) {
        this.accountLevelEnum = accountLevelEnum;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAvlBal() {
        return avlBal;
    }

    public void setAvlBal(double avlBal) {
        this.avlBal = avlBal;
    }

    public double getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(double acctBal) {
        this.acctBal = acctBal;
    }

    public double getFrzBal() {
        return frzBal;
    }

    public void setFrzBal(double frzBal) {
        this.frzBal = frzBal;
    }
}
