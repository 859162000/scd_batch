package com.scd.batch.reconciliation.dao;

import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.entity.reconciliation.SaveTransferEntity;
import com.scd.batch.common.entity.reconciliation.TransferEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TransferBatchUpdateDao {

    int batchInsertLoanPaymentTransfer(@Param("entityList") List<LoanPaymentTransferEntity> entityList);

    int[] deleteLoanPaymentTransfer(@Param("transDate") Date transDate,
                                    @Param("transferType") int transferType);

    int batchInsertCashTransfer(@Param("entityList") List<CashTransferEntity> entityList);

    int[] deleteCashTransfer(@Param("transDate") Date transDate);

    int batchInsertTrfTransfer(@Param("entityList") List<TransferEntity> entityList);

    int[] deleteTrfTransfer(@Param("transDate") Date transDate);

    int batchInsertSaveTransfer(@Param("entityList") List<SaveTransferEntity> entityList);

    int[] deleteSaveTransfer(@Param("transDate") Date transDate);
}
