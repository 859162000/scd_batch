package com.scd.batch.common.entity.reconciliation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.Entity;

import java.util.Date;

/**
 * 流水基类
 * <p>
 * 商户扣款
 */
public class TransferEntity extends Entity implements TransferValue {

    // 业务日期
    private Date transDate;

    // 删除标记 0未删除，1删除
    private int deleteFlag;

    // 交易类型
    private int transferType;

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

    @JsonIgnore
    public String getValue() {
        String[] params = new String[]{getMerCustId(), String.valueOf(getTransAmt()),
                getOrdId()};
        return Joiner.on(",").join(params);
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
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

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
