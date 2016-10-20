package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.trade.LoanEntity;

/**
 * 放款还款流水差错表
 */
public class LoanPaymentErrorEntity {

    private String key;

    private LoanEntity loanEntity;

    private LoanPaymentTransferEntity loanPaymentTransferEntity;

    private TransferErrorType transferErrorType;

    public LoanPaymentErrorEntity(TransferErrorType transferErrorType, String key, LoanEntity loanEntity, LoanPaymentTransferEntity loanPaymentTransferEntity) {
        this.transferErrorType = transferErrorType;
        this.key = key;
        this.loanEntity = loanEntity;
        this.loanPaymentTransferEntity = loanPaymentTransferEntity;
    }

    public LoanEntity getLoanEntity() {
        return loanEntity;
    }

    public void setLoanEntity(LoanEntity loanEntity) {
        this.loanEntity = loanEntity;
    }

    public LoanPaymentTransferEntity getLoanPaymentTransferEntity() {
        return loanPaymentTransferEntity;
    }

    public void setLoanPaymentTransferEntity(LoanPaymentTransferEntity loanPaymentTransferEntity) {
        this.loanPaymentTransferEntity = loanPaymentTransferEntity;
    }

    public TransferErrorType getTransferErrorType() {
        return transferErrorType;
    }

    public void setTransferErrorType(TransferErrorType transferErrorType) {
        this.transferErrorType = transferErrorType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
