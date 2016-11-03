package com.scd.batch.common;


import com.scd.batch.common.constant.reconciliation.LoanPaymentTransStat;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.entity.reconciliation.TransferErrorEntity;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;

import java.util.ArrayList;
import java.util.List;

public class TestReconciliationUtil {

    public static List<LoanPaymentTransferEntity> buildLoanPaymentTransferList() {

        List<LoanPaymentTransferEntity> list = new ArrayList<>();
        list.add(buildLoanPaymentTransferEntity());
        list.add(buildLoanPaymentTransferEntity());
        list.add(buildLoanPaymentTransferEntity());
        list.add(buildLoanPaymentTransferEntity());
        list.add(buildLoanPaymentTransferEntity());
        list.add(buildLoanPaymentTransferEntity());
        list.add(buildLoanPaymentTransferEntity());

        return list;
    }

    public static LoanPaymentTransferEntity buildLoanPaymentTransferEntity() {
        LoanPaymentTransferEntity entity = new LoanPaymentTransferEntity();
        entity.setTransferType(TransferType.LOANS.type);
        entity.setTransStat(LoanPaymentTransStat.SUCCESS.getType());
        entity.setOrdDate(ShortDate.today().toDate());
        entity.setOrdId("orderId");
        entity.setOrdDate(ShortDate.today().toDate());
        entity.setBorrCustId("custId");
        entity.setMerCustId("merCustId");

        return entity;
    }

    public static CashTransferEntity buildCashTransferEntity() {
        CashTransferEntity entity = new CashTransferEntity();
        entity.setTransferType(TransferType.LOANS.type);
        entity.setTransStat(LoanPaymentTransStat.SUCCESS.getType());
        entity.setOrdDate(ShortDate.today().toDate());
        entity.setOrdId("orderId");
        entity.setOrdDate(ShortDate.today().toDate());
        entity.setBorrCustId("custId");
        entity.setMerCustId("merCustId");

        return entity;
    }

    public static List<CashTransferEntity> buildCashTransferList() {
        List<CashTransferEntity> list = new ArrayList<>();
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());
        list.add(buildCashTransferEntity());

        return list;
    }

    public static TransferErrorEntity buildTransferErrorEntity() {

        TransferErrorEntity entity = new TransferErrorEntity();
        entity.setErrorType(1);
        entity.setErrorStatus(1);
        entity.setaJson(JsonUtils.toJson(buildCashTransferEntity()));
        entity.setbJson(JsonUtils.toJson(buildCashTransferEntity()));
        entity.setaValue("11111");
        entity.setbValue("bbbb");
        entity.setTransId("key");

        return entity;
    }

    public static List<TransferErrorEntity> buildTransferErrorList() {
        List<TransferErrorEntity> list = new ArrayList<>();
        list.add(buildTransferErrorEntity());
        list.add(buildTransferErrorEntity());
        list.add(buildTransferErrorEntity());
        list.add(buildTransferErrorEntity());
        list.add(buildTransferErrorEntity());
        list.add(buildTransferErrorEntity());
        list.add(buildTransferErrorEntity());

        return list;
    }
}
