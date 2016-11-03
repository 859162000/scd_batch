package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.bid.CreditRepayReal;
import com.scd.batch.common.entity.bid.LoanEntity;

/**
 * 还款流水差错表
 */
public class PaymentErrorBase extends TransferErrorBase {

    private CreditRepayReal creditRepayReal;

    private LoanPaymentTransferEntity transferEntity;


    public PaymentErrorBase(String key, CreditRepayReal repayReal, LoanPaymentTransferEntity transferEntity,
                            TransferErrorType transferErrorType) {
        super(key, transferErrorType);
        this.creditRepayReal = repayReal;
        this.transferEntity = transferEntity;
    }

    public CreditRepayReal getCreditRepayReal() {
        return creditRepayReal;
    }

    public void setCreditRepayReal(CreditRepayReal creditRepayReal) {
        this.creditRepayReal = creditRepayReal;
    }

    public LoanPaymentTransferEntity getTransferEntity() {
        return transferEntity;
    }

    public void setTransferEntity(LoanPaymentTransferEntity transferEntity) {
        this.transferEntity = transferEntity;
    }

}
