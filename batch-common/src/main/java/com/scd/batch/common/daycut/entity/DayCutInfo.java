package com.scd.batch.common.daycut.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

public class DayCutInfo {
    /**
     * PRIMARY key id
     */
    private Integer id;
    /**
     * 账务日期
     */
    private Date accountDate;
    /**
     * dayCutCreate
     */
    private Date dayCutCreated;
    /**
     * dayCutModified
     */
    private Date dayCutModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public Date getDayCutCreated() {
        return dayCutCreated;
    }

    public void setDayCutCreated(Date dayCutCreated) {
        this.dayCutCreated = dayCutCreated;
    }

    public Date getDayCutModified() {
        return dayCutModified;
    }

    public void setDayCutModified(Date dayCutModified) {
        this.dayCutModified = dayCutModified;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
