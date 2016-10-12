package com.scd.batch.trade.model;

import com.scd.batch.trade.model.loan.LoanOperationValueEntity;
import com.scd.batch.trade.model.loan.TransactionEntity;

import java.util.List;

public class TransactionOperation {

    public final List<TransactionEntity> transactions;

    public final List<LoanOperationValueEntity> operationValues;

    public TransactionOperation(List<TransactionEntity> transactions, List<LoanOperationValueEntity> operationValues) {

        this.transactions = transactions;
        this.operationValues = operationValues;
    }
}
