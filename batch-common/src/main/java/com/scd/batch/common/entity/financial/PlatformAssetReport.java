package com.scd.batch.common.entity.financial;

import java.math.BigDecimal;
import java.util.Date;

public class PlatformAssetReport {
    private Long id;

    private Date report_date;

    private BigDecimal currentAssetTotal;

    private BigDecimal fixPlanAssetTotal;

    private BigDecimal fixProjectAssetTotal;

    private BigDecimal usableBalanceTotal;

    private BigDecimal freezeBalanceTotal;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public BigDecimal getCurrentAssetTotal() {
        return currentAssetTotal;
    }

    public void setCurrentAssetTotal(BigDecimal currentAssetTotal) {
        this.currentAssetTotal = currentAssetTotal;
    }

    public BigDecimal getFixPlanAssetTotal() {
        return fixPlanAssetTotal;
    }

    public void setFixPlanAssetTotal(BigDecimal fixPlanAssetTotal) {
        this.fixPlanAssetTotal = fixPlanAssetTotal;
    }

    public BigDecimal getFixProjectAssetTotal() {
        return fixProjectAssetTotal;
    }

    public void setFixProjectAssetTotal(BigDecimal fixProjectAssetTotal) {
        this.fixProjectAssetTotal = fixProjectAssetTotal;
    }

    public BigDecimal getUsableBalanceTotal() {
        return usableBalanceTotal;
    }

    public void setUsableBalanceTotal(BigDecimal usableBalanceTotal) {
        this.usableBalanceTotal = usableBalanceTotal;
    }

    public BigDecimal getFreezeBalanceTotal() {
        return freezeBalanceTotal;
    }

    public void setFreezeBalanceTotal(BigDecimal freezeBalanceTotal) {
        this.freezeBalanceTotal = freezeBalanceTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}