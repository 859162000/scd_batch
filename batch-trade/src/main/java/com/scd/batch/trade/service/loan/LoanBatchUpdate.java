package com.scd.batch.trade.service.loan;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.model.loan.LoanInfo;

import java.util.List;

public interface LoanBatchUpdate {

    int[] updateLoanInstallment(List<InstallmentInfo> installments);

    int[] updateLoan(List<LoanInfo> loanInfoList);

    int[] updateAccountDebitStatus(List<LoanInfo> loanInfoList);

    int[] addFailRecord(List<LoanInfo> loanInfoList, ShortDate accountDate);

    int[] addOperation(List<LoanInfo> loanInfoList, ShortDate accountDate);
}
