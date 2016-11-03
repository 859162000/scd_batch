package com.scd.batch.common.entity.reconciliation;


import java.util.List;

/**
 * 放还款流水
 */
public class LoanPaymentTransferEntity extends TransferEntity {

    // 汇付交易状态
    private String transStat;

    // 分账串
    private List<DivEntity> divDetails;

    public List<DivEntity> getDivDetails() {
        return divDetails;
    }

    public void setDivDetails(List<DivEntity> divDetails) {
        this.divDetails = divDetails;
    }

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }
}
