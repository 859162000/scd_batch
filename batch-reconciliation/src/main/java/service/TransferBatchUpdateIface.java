package service;

import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TransferBatchUpdateIface {

    int[] batchInsertLoanPaymentTransfer(@Param("entityList") List<LoanPaymentTransferEntity> entityList);

    int[] batchInsertCashTransfer(@Param("entityList") List<CashTransferEntity> entityList);

    boolean delete(@Param("transDate") Date transDate);
}
