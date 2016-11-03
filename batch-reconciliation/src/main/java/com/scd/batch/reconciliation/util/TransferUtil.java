package com.scd.batch.reconciliation.util;

import com.miaoqian.api.dto.CashReconciliationDto;
import com.miaoqian.api.dto.ReconciliationDto;
import com.miaoqian.api.dto.SaveReconciliationDto;
import com.miaoqian.api.dto.TrfReconciliationDto;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.entity.reconciliation.SaveTransferEntity;
import com.scd.batch.common.entity.reconciliation.TransferEntity;
import com.scd.batch.common.utils.ShortDate;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


public class TransferUtil {

    /**
     * 用汇付放款还款流水构建对账数据
     *
     * @param reconciliationDtoList
     * @return
     */
    public static List<LoanPaymentTransferEntity> buildLoanRepaymentTransfer(List<ReconciliationDto>
                                                                                     reconciliationDtoList) {
        List<LoanPaymentTransferEntity> entityList = new ArrayList<>();


        for (ReconciliationDto dto : reconciliationDtoList) {

            LoanPaymentTransferEntity transfer = new LoanPaymentTransferEntity();
            transfer.setMerCustId(dto.getMerCustId());
            transfer.setBorrCustId(dto.getBorrCustId());
//            transfer.setOrdDate(ShortDate.valueOf(dto.getOrdDate()).toDate());
            transfer.setTransStat(dto.getTransStat());
            transfer.setDeleteFlag(0);
            transfer.setInvestCustId(dto.getInvestCustId());
//            transfer.setPnrDate(ShortDate.valueOf(dto.getPnrDate()).toDate());
            transfer.setOrdId(dto.getOrdId());
            transfer.setPnrSeqId(dto.getPnrSeqId());
//            transfer.setTransAmt(Double.valueOf(dto.getTransAmt()));
            transfer.setTransferType(TransferType.LOANS.getType());
            entityList.add(transfer);
        }


        return entityList;
    }


    /**
     * 用汇付取现流水构建对账数据
     *
     * @param reconciliationDtoList
     * @return
     */
    public static List<CashTransferEntity> buildCashTransfer(List<CashReconciliationDto> reconciliationDtoList) {
        List<CashTransferEntity> entityList = new ArrayList<>();

        reconciliationDtoList.forEach(p -> {
            CashTransferEntity transfer = new CashTransferEntity();
            BeanUtils.copyProperties(p, transfer);
            entityList.add(transfer);
        });

        return entityList;
    }


    /**
     * 商户扣款流水
     */
    public static List<TransferEntity> buildTrfTransfer(List<TrfReconciliationDto> reconciliationDtoList) {
        List<TransferEntity> entityList = new ArrayList<>();

        reconciliationDtoList.forEach(p -> {
            TransferEntity transfer = new TransferEntity();
            BeanUtils.copyProperties(p, transfer);
            entityList.add(transfer);
        });


        return entityList;
    }


    /**
     * 充值流水
     */
    public static List<SaveTransferEntity> buildSaveTransfer(List<SaveReconciliationDto> reconciliationDtoList) {
        List<SaveTransferEntity> entityList = new ArrayList<>();

        reconciliationDtoList.forEach(p -> {
            SaveTransferEntity transfer = new SaveTransferEntity();
            BeanUtils.copyProperties(p, transfer);
            entityList.add(transfer);
        });


        return entityList;
    }

}


