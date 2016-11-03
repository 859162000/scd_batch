package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.trade.WithdrawL;

/**
 * 商户扣款流水差错表
 */
public class TrfErrorBase extends TransferErrorBase {

    // TODO
    private WithdrawL withdrawL;

    private TransferEntity transferEntity;

    public TrfErrorBase(String key, WithdrawL withdrawL, TransferEntity transferEntity, TransferErrorType
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

    public TransferEntity getTransferEntity() {
        return transferEntity;
    }

    public void setTransferEntity(TransferEntity transferEntity) {
        this.transferEntity = transferEntity;
    }
}
