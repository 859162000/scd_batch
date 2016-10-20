package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.entity.Entity;

/**
 * 商户查询其用户在本平台专属账户的可用余额
 */
public class CustomerBalanceEntity extends Entity {

    // 商户客户号
    private String merCustId;

    // 用户客户号
    private String usrCustId;

    private AccountBalanceEntity accountBalanceEntity;

    public String getMerCustId() {
        return merCustId;
    }

    public void setMerCustId(String merCustId) {
        this.merCustId = merCustId;
    }

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }

    public AccountBalanceEntity getAccountBalanceEntity() {
        return accountBalanceEntity;
    }

    public void setAccountBalanceEntity(AccountBalanceEntity accountBalanceEntity) {
        this.accountBalanceEntity = accountBalanceEntity;
    }
}
