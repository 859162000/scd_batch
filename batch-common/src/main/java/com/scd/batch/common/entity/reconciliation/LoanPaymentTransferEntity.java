package com.scd.batch.common.entity.reconciliation;

import constant.LoanPaymentTransStat;
import constant.TransferType;

import java.util.Date;
import java.util.List;

/**
 * 放还款流水
 */
public class LoanPaymentTransferEntity extends TransferEntity {

    // 汇付交易状态
    private LoanPaymentTransStat transStat;

    // 分账串
    private List<DivEntity> divDetails;

    public LoanPaymentTransferEntity(TransferType transferType, String ordId, Date ordDate,
                                     String merCustId, String investCustId, String borrCustId,
                                     double transAmt, Date pnrDate, String pnrSeqId, LoanPaymentTransStat transStat, List<DivEntity> divDetails) {

        super(transferType, ordId, ordDate, merCustId, investCustId, borrCustId, transAmt, pnrDate, pnrSeqId);

        this.transStat = transStat;
        this.divDetails = divDetails;
    }

    public List<DivEntity> getDivDetails() {
        return divDetails;
    }

    public void setDivDetails(List<DivEntity> divDetails) {
        this.divDetails = divDetails;
    }

    public LoanPaymentTransStat getTransStat() {
        return transStat;
    }

    public void setTransStat(LoanPaymentTransStat transStat) {
        this.transStat = transStat;
    }
}
