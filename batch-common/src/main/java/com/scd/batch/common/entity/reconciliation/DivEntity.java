package com.scd.batch.common.entity.reconciliation;

/**
 * 分账实体
 */
public class DivEntity {

    // 分账客户号
    private String divCustId;

    // 分账账户号
    private String divAcctId;

    // 分账金额
    private double divAmt;

    public DivEntity(String divCustId, String divAcctId, double divAmt) {
        this.divCustId = divCustId;
        this.divAcctId = divAcctId;
        this.divAmt = divAmt;
    }

    public String getDivCustId() {
        return divCustId;
    }

    public void setDivCustId(String divCustId) {
        this.divCustId = divCustId;
    }

    public String getDivAcctId() {
        return divAcctId;
    }

    public void setDivAcctId(String divAcctId) {
        this.divAcctId = divAcctId;
    }

    public double getDivAmt() {
        return divAmt;
    }

    public void setDivAmt(double divAmt) {
        this.divAmt = divAmt;
    }
}
