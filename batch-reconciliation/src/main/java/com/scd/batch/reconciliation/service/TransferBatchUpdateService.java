package com.scd.batch.reconciliation.service;

import com.scd.batch.common.dao.reconciliation.CashTransferDao;
import com.scd.batch.common.dao.reconciliation.LoanPaymentTransferDao;
import com.scd.batch.common.dao.reconciliation.SaveTransferDao;
import com.scd.batch.common.dao.reconciliation.TrfTransferDao;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.entity.reconciliation.SaveTransferEntity;
import com.scd.batch.common.entity.reconciliation.TransferEntity;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.reconciliation.dao.TransferBatchUpdateBase;
import com.scd.batch.reconciliation.dao.TransferBatchUpdateDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

@Service
public class TransferBatchUpdateService extends TransferBatchUpdateBase implements TransferBatchUpdateDao {

    @Override
    public int batchInsertLoanPaymentTransfer(@Param("entityList") List<LoanPaymentTransferEntity> entityList) {
        if (isEmpty(entityList)) {
            return 0;
        }

        LoanPaymentTransferDao transferDao = getDAO(LoanPaymentTransferDao.class);

        int result = transferDao.batchInsert(TableSpec.getDefault(), entityList);

        return result;
//        return flush2UpdateCounts0();
    }

    @Override
    public int[] deleteLoanPaymentTransfer(@Param("transDate") Date transDate,
                                           @Param("transferType") int transferType) {
        LoanPaymentTransferDao loanPaymentTransferDao = getDAO(LoanPaymentTransferDao.class);

        loanPaymentTransferDao.deleteLoanPaymentTransfer(TableSpec.getDefault(),
                transDate, transferType);

        return flush2UpdateCounts0();
    }

    @Override
    public int batchInsertCashTransfer(@Param("entityList") List<CashTransferEntity> entityList) {
        if (isEmpty(entityList)) {
            return 0;
        }

        CashTransferDao transferDao = getDAO(CashTransferDao.class);

        int result = transferDao.batchInsert(TableSpec.getDefault(), entityList);

        return result;
//        return flush2UpdateCounts0();
    }


    @Override
    public int[] deleteCashTransfer(@Param("transDate") Date transDate) {
        CashTransferDao transferDao = getDAO(CashTransferDao.class);

        transferDao.deleteCashTransfer(TableSpec.getDefault(), transDate);

        return flush2UpdateCounts0();
    }


    @Override
    public int batchInsertTrfTransfer(@Param("entityList") List<TransferEntity> entityList) {
        if (isEmpty(entityList)) {
            return 0;
        }

        TrfTransferDao transferDao = getDAO(TrfTransferDao.class);

        int result = transferDao.batchInsert(TableSpec.getDefault(), entityList);

        return result;
//        return flush2UpdateCounts0();
    }


    @Override
    public int[] deleteTrfTransfer(@Param("transDate") Date transDate) {
        TrfTransferDao transferDao = getDAO(TrfTransferDao.class);

        transferDao.deleteTrfTransfer(TableSpec.getDefault(), transDate);

        return flush2UpdateCounts0();
    }


    @Override
    public int batchInsertSaveTransfer(@Param("entityList") List<SaveTransferEntity> entityList) {
        if (isEmpty(entityList)) {
            return 0;
        }

        SaveTransferDao transferDao = getDAO(SaveTransferDao.class);

        int result = transferDao.batchInsert(TableSpec.getDefault(), entityList);

        return result;
//        return flush2UpdateCounts0();
    }


    @Override
    public int[] deleteSaveTransfer(@Param("transDate") Date transDate) {
        SaveTransferDao transferDao = getDAO(SaveTransferDao.class);

        transferDao.deleteSaveTransfer(TableSpec.getDefault(), transDate);

        return flush2UpdateCounts0();
    }
}
