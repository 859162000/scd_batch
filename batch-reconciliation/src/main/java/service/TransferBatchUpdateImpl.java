package service;

import dao.TransferDao;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

@Service
public class TransferBatchUpdateImpl extends TransferBatchUpdateService implements TransferBatchUpdateIface {

    @Override
    public int[] batchInsertLoanPaymentTransfer(@Param("entityList") List<LoanPaymentTransferEntity> entityList) {
        if (isEmpty(entityList)) {
            return new int[0];
        }

        TransferDao transferDao = getDAO(TransferDao.class);

        transferDao.batchInsertLoanRepayTransfer(entityList);

        return flush2UpdateCounts0();
    }

    @Override
    public int[] batchInsertCashTransfer(@Param("entityList") List<CashTransferEntity> entityList) {
        if (isEmpty(entityList)) {
            return new int[0];
        }

        TransferDao transferDao = getDAO(TransferDao.class);

        transferDao.batchInsertCashTransfer(entityList);

        return flush2UpdateCounts0();
    }


    @Override
    public boolean delete(@Param("transDate") Date transDate) {
        TransferDao transferDao = getDAO(TransferDao.class);

        return transferDao.delete();
    }

}
