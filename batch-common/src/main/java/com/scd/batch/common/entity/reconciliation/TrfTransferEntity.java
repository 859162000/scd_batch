package com.scd.batch.common.entity.reconciliation;


/**
 * 商户扣款流水
 */
public class TrfTransferEntity extends TransferEntity {

    // 汇付取现交易状态
    private String transStat;

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }

}
