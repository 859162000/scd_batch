package com.scd.batch.common.entity.executor;

import com.scd.batch.common.entity.Entity;

import java.util.Date;

public class FailRecordEntity extends Entity {
    private long failureId;
    private long customerId;
    private long accountId;
    private Date accountDate;
    private int status;

    public long getFailureId() {
        return failureId;
    }

    public void setFailureId(long failureId) {
        this.failureId = failureId;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
