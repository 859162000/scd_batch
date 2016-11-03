package com.scd.batch.common.entity.reconciliation;


/**
 * 充值流水
 */
public class SaveTransferEntity extends TransferEntity {


    // 汇付取现交易状态
    private String transStat;

    //支付网关业务代号
    private String gateBusiId;

    //  开户银行代号
    private String openBankId;

    //开户银行账号
    private String openAcctId;

    //手续费金额
    private String feeAmt;

    //手续费扣款客户号
    private String feeCustId;

    // 手续费扣款子账户号
    private String feeAcctId;


    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }

    public String getGateBusiId() {
        return gateBusiId;
    }

    public void setGateBusiId(String gateBusiId) {
        this.gateBusiId = gateBusiId;
    }

    public String getOpenBankId() {
        return openBankId;
    }

    public void setOpenBankId(String openBankId) {
        this.openBankId = openBankId;
    }

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String openAcctId) {
        this.openAcctId = openAcctId;
    }

    public String getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getFeeCustId() {
        return feeCustId;
    }

    public void setFeeCustId(String feeCustId) {
        this.feeCustId = feeCustId;
    }

    public String getFeeAcctId() {
        return feeAcctId;
    }

    public void setFeeAcctId(String feeAcctId) {
        this.feeAcctId = feeAcctId;
    }
}
