package com.scd.batch.common.entity.reconciliation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ScdUserBalanceEntity implements Serializable {

    private String uid;

    private Double usableSa;

    private Double freezeSa;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

}
