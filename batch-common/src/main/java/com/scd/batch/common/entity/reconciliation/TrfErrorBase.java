package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.constant.reconciliation.TransferErrorType;
import com.scd.batch.common.entity.trade.WithdrawL;

/**
 * 商户扣款流水差错表
 */
public class TrfErrorBase extends TransferErrorBase {

    private TrfTransferEntity scdTransfer;

    private TransferEntity huifuTransfer;

    public TrfErrorBase(String key,
                        TrfTransferEntity scdTransfer,
                        TransferEntity huifuTransfer,
                        TransferErrorType transferErrorType) {
        super(key, transferErrorType);
        this.scdTransfer = scdTransfer;
        this.huifuTransfer = huifuTransfer;
    }

    public TrfTransferEntity getScdTransfer() {
        return scdTransfer;
    }

    public void setScdTransfer(TrfTransferEntity scdTransfer) {
        this.scdTransfer = scdTransfer;
    }

    public TransferEntity getHuifuTransfer() {
        return huifuTransfer;
    }

    public void setHuifuTransfer(TransferEntity huifuTransfer) {
        this.huifuTransfer = huifuTransfer;
    }
}
