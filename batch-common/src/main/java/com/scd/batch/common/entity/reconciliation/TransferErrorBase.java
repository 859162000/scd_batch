package com.scd.batch.common.entity.reconciliation;


import com.scd.batch.common.constant.reconciliation.TransferErrorType;

public class TransferErrorBase {

    private String key;

    private TransferErrorType transferErrorType;

    public TransferErrorBase(String key, TransferErrorType transferErrorType) {
        this.key = key;
        this.transferErrorType = transferErrorType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TransferErrorType getTransferErrorType() {
        return transferErrorType;
    }

    public void setTransferErrorType(TransferErrorType transferErrorType) {
        this.transferErrorType = transferErrorType;
    }
}
