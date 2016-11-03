package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.bid.LoanEntity;

/**
 * 放款还款流水差错表
 */
public class LoanErrorBase extends TransferErrorBase {

    private LoanEntity loanEntity;

    private LoanPaymentTransferEntity transferEntity;


    public LoanErrorBase(String key, LoanEntity loanEntity, LoanPaymentTransferEntity transferEntity,
                         TransferErrorType transferErrorType) {
        super(key, transferErrorType);
        this.loanEntity = loanEntity;
        this.transferEntity = transferEntity;
    }

    public LoanEntity getLoanEntity() {
        return loanEntity;
    }

    public void setLoanEntity(LoanEntity loanEntity) {
        this.loanEntity = loanEntity;
    }

    public LoanPaymentTransferEntity getTransferEntity() {
        return transferEntity;
    }

    public void setTransferEntity(LoanPaymentTransferEntity transferEntity) {
        this.transferEntity = transferEntity;
    }

}
