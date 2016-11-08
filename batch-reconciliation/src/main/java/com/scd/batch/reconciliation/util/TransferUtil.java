package com.scd.batch.reconciliation.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.miaoqian.api.dto.CashReconciliationDto;
import com.miaoqian.api.dto.ReconciliationDto;
import com.miaoqian.api.dto.SaveReconciliationDto;
import com.miaoqian.api.dto.TrfReconciliationDto;
import com.scd.batch.common.constant.reconciliation.DeleteFlagType;
import com.scd.batch.common.constant.reconciliation.FeeObjType;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.entity.reconciliation.SaveTransferEntity;
import com.scd.batch.common.entity.reconciliation.TransferEntity;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.EnumUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
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
    public static List<CashTransferEntity> buildCashTransfer(Date transDate,
                                                             List<CashReconciliationDto> reconciliationDtoList,
                                                             String feeObj) {
        List<CashTransferEntity> entityList = new ArrayList<>();

        for (CashReconciliationDto dto : reconciliationDtoList) {
            CashTransferEntity transfer = new CashTransferEntity();
            transfer.setTransDate(transDate);
            transfer.setDeleteFlag(DeleteFlagType.NOT.getType());
            transfer.setTransferType(TransferType.CASH.getType());
            transfer.setOrdId(dto.getOrdId());
            transfer.setOrdDate(DateUtil.StringToDate(dto.getPnrDate(), DateStyle.YYYYMMDD));
            transfer.setMerCustId(dto.getMerCustId());
            transfer.setInvestCustId(dto.getUsrCustId());
            transfer.setBorrCustId("");
            transfer.setTransAmt(StringUtils.isEmpty(dto.getTransAmt()) ? 0.0 : Double.valueOf(dto.getTransAmt()));
            transfer.setPnrDate(StringUtils.isEmpty(dto.getPnrDate()) ?
                    null : DateUtil.StringToDate(dto.getPnrDate(), DateStyle.YYYYMMDD));
            transfer.setPnrSeqId(dto.getPnrSeqId());

            transfer.setTransStat(dto.getTransStat());
            transfer.setFeeObjType(StringUtils.isEmpty(EnumUtils.getEnum(FeeObjType.class, feeObj).desc) ?
                    0 : Integer.valueOf(EnumUtils.getEnum(FeeObjType.class, feeObj).desc));
            transfer.setFeeAmt(StringUtils.isEmpty(dto.getFeeAmt()) ? 0.0 : Double.valueOf(dto.getFeeAmt()));
            transfer.setServFee(StringUtils.isEmpty(dto.getServFee()) ? 0.0 : Double.valueOf(dto.getServFee()));
            transfer.setServFeeAcctId(StringUtils.isEmpty(dto.getServFeeAcctId()) ? "" : dto.getServFeeAcctId());

            entityList.add(transfer);
        }

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


