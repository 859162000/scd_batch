package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.trade.WithdrawL;

/**
 * 取现流水差错表
 */
public class SaveErrorBase extends TransferErrorBase {

    private WithdrawL withdrawL;

    private SaveTransferEntity transferEntity;

    public SaveErrorBase(String key, WithdrawL withdrawL, SaveTransferEntity transferEntity, TransferErrorType
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

    public SaveTransferEntity getTransferEntity() {
        return transferEntity;
    }

    public void setTransferEntity(SaveTransferEntity transferEntity) {
        this.transferEntity = transferEntity;
    }
}
