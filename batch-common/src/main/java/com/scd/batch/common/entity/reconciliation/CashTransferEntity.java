package com.scd.batch.common.entity.reconciliation;


/**
 * 取现流水
 */
public class CashTransferEntity extends TransferEntity {


    // 汇付取现交易状态
    private String transStat;

    // 手续费收取方
    private int feeObjType;

    // 手续费金额
    private double feeAmt;

    // 商户收取服务费金额（取现手续费+垫资手续费）
    private double servFee;

    // 商户子账户号
    private String ServFeeAcctId;


    public double getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(double feeAmt) {
        this.feeAmt = feeAmt;
    }

    public double getServFee() {
        return servFee;
    }

    public void setServFee(double servFee) {
        this.servFee = servFee;
    }

    public String getServFeeAcctId() {
        return ServFeeAcctId;
    }

    public void setServFeeAcctId(String servFeeAcctId) {
        ServFeeAcctId = servFeeAcctId;
    }

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }

    public int getFeeObjType() {
        return feeObjType;
    }

    public void setFeeObjType(int feeObjType) {
        this.feeObjType = feeObjType;
    }
}
