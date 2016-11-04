package com.scd.batch.common.entity.interest;

import com.scd.batch.common.entity.Entity;

import java.util.Date;

public class UserAssetsEntity extends Entity {

    // uid
    private String uid;

    private Date transDate;

    private Double assets;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Double getAssets() {
        return assets;
    }

    public void setAssets(Double assets) {
        this.assets = assets;
    }
}
