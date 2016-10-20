package com.scd.batch.common.entity.reconciliation;

import com.google.common.base.Joiner;
import constant.TransferType;

import java.util.Date;

/**
 * 流水基类
 * <p>
 * 商户扣款
 */
public class TransferEntity {

    // 交易查询类型
    private TransferType transferType;

    // 订单号
    private String ordId;

    // 订单日期
    private Date ordDate;

    // 商户客户号
    private String merCustId;

    // 投资人客户号
    private String investCustId;

    // 借款人客户号
    private String borrCustId;

    // 交易金额
    private double transAmt;

    // 汇付交易日期
    private Date pnrDate;

    // 汇付交易流水
    private String pnrSeqId;

    public TransferEntity(TransferType transferType, String ordId, Date ordDate,
                          String merCustId, String investCustId, String borrCustId,
                          double transAmt, Date pnrDate, String pnrSeqId) {

        this.transferType = transferType;
        this.ordId = ordId;
        this.ordDate = ordDate;
        this.merCustId = merCustId;
        this.investCustId = investCustId;
        this.borrCustId = borrCustId;
        this.transAmt = transAmt;
        this.pnrDate = pnrDate;
        this.pnrSeqId = pnrSeqId;
    }

    public String getValue() {
        String[] params = new String[]{getBorrCustId(), getBorrCustId(), getMerCustId(), String.valueOf(getTransAmt()),
                getPnrSeqId()};
        return Joiner.on(",").join(params);
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getMerCustId() {
        return merCustId;
    }

    public void setMerCustId(String merCustId) {
        this.merCustId = merCustId;
    }

    public String getInvestCustId() {
        return investCustId;
    }

    public void setInvestCustId(String investCustId) {
        this.investCustId = investCustId;
    }

    public String getBorrCustId() {
        return borrCustId;
    }

    public void setBorrCustId(String borrCustId) {
        this.borrCustId = borrCustId;
    }

    public double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(double transAmt) {
        this.transAmt = transAmt;
    }

    public String getPnrSeqId() {
        return pnrSeqId;
    }

    public void setPnrSeqId(String pnrSeqId) {
        this.pnrSeqId = pnrSeqId;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }

    public Date getPnrDate() {
        return pnrDate;
    }

    public void setPnrDate(Date pnrDate) {
        this.pnrDate = pnrDate;
    }
}
