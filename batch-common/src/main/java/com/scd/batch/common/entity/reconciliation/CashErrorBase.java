package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.trade.WithdrawL;

/**
 * 取现流水差错表
 */
public class CashErrorBase extends TransferErrorBase {

    private WithdrawL withdrawL;

    private CashTransferEntity transferEntity;

    public CashErrorBase(String key, WithdrawL withdrawL, CashTransferEntity transferEntity, TransferErrorType
            transferErrorType) {
        super(key, transferErrorType);
        this.withdrawL = withdrawL;
        this.transferEntity = transferEntity;
    }

    public WithdrawL getWithdrawL() {
        return withdrawL;
    }

    public void setWithdrawL(WithdrawL withdrawL) {
        this.withdrawL = withdrawL;
    }

    public CashTransferEntity getTransferEntity() {
        return transferEntity;
    }

    public void setTransferEntity(CashTransferEntity transferEntity) {
        this.transferEntity = transferEntity;
    }
}
