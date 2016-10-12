package com.scd.batch.common.job.batch.control;

import java.util.Date;

public class JobCondition {
    private long id;

    private Date accountDate;

    private int maxRetry;

    private int jobType;

    private int phase;

    public JobCondition() {
    }

    public JobCondition(Date accountDate, int jobType, int phase) {
        this.accountDate = accountDate;
        this.jobType = jobType;
        this.phase = phase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public int getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
}
