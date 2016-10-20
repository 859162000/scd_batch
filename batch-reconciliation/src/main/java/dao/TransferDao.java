package dao;

import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferDao {

    int batchInsertLoanRepayTransfer(@Param("entityList") List<LoanPaymentTransferEntity> entityList);

    int batchInsertCashTransfer(@Param("entityList") List<CashTransferEntity> entityList);

    boolean delete();
}
