package com.scd.batch.trade.service.loan;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.google.common.base.Stopwatch;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.trade.common.LoanUtil;
import com.scd.batch.trade.dao.BatchLoanDAO;
import com.scd.batch.trade.model.TransactionOperation;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.model.loan.LoanInstallmentInfo;
import com.scd.batch.trade.model.loan.LoanOperationValueEntity;
import com.scd.batch.trade.model.loan.TransactionEntity;
import com.scd.batch.trade.service.FailureRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Resource
    private BatchLoanDAO batchLoanDAO;

    @Resource
    private FailureRecordService failureRecordService;

    public List<Long> getAllOverdueLoanId(TableSpec ts, ShortDate accountDate) {

        return batchLoanDAO.getAllOverdueLoanId(ts, accountDate.toDate());
    }

    public List<LoanInstallmentInfo> getInstallmentInfo(TableSpec ts,
                                                        List<Long> loanIdList, ShortDate accountDate) {

        return isEmpty(loanIdList)
                ? new ArrayList<>()
                : batchLoanDAO.selectInstallmentInfoByLoanId(ts, loanIdList, accountDate.toDate());
    }

    private LoanInfo transform2LoanInfo(List<LoanInstallmentInfo> installments) {

        if (isEmpty(installments)) {
            return null;
        }

        LoanInfo loanInfo = installments.get(0).transform2LoanInfo();
        loanInfo.setInstallments(new ArrayList<>());

        installments.forEach(i -> loanInfo.getInstallments().add(i.transform2InstallmentInfo()));

        return loanInfo;
    }

    public LoanInfo loanCalculate(LoanInfo loanInfo, ShortDate accountDate) {

        // 滞纳金定价因子
        int mOverdue = loanInfo.getmOverdue();
        // 罚息定价因子
        int mPenalty = loanInfo.getmPenalty();

        long penaltyTotal = 0;
        long overdueTotal = 0;

        int age = 0;

        for (InstallmentInfo installment : loanInfo.getInstallments()) {

            // 宽限日
            ShortDate graceDate = ShortDate.valueOf(installment.getGraceDate());

            // 大部分情况下只增加一天的罚息
            int penaltyDay = 1;
            // 滞纳金
            long overdue = 0;
            // 罚息
            long penalty;

            if (CommonUtil.expiringPoint(graceDate, accountDate)) {

                penaltyDay = CommonUtil.betweenTwoDays(
                        installment.getDueDate(),
                        installment.getGraceDate());

                // 未还金额
                long baseAmount = LoanUtil.summaryUnpaid(installment);

                Stopwatch stopwatch = Stopwatch.createStarted();
            }

            // 未还本金
            long baseAmount = LoanUtil.summaryPrincipalUnpaid(installment);
            Stopwatch stopwatch = Stopwatch.createStarted();

            age = Math.max(age, installmentAge(graceDate, accountDate));

            installment.setOverdueDelta(overdue);

            overdueTotal += overdue;
        } // for installments

        loanInfo.setPenaltyDelta(penaltyTotal);
        loanInfo.setOverdueDelta(overdueTotal);
        loanInfo.setAge(age);

        return loanInfo;
    }

    private int installmentAge(ShortDate graceDate, ShortDate accountDate) {

        ShortDate preAccountDate = accountDate.addDays(-1);

        if (preAccountDate.isBefore(graceDate)) {
            return 0;
        }

        return CommonUtil.betweenTwoMonths(graceDate, preAccountDate) + 1;
    }

    public TransactionOperation trans2TransactionOperation(List<LoanInfo> loanInfoList, ShortDate accountDate) {

        final Date preAccountDate = accountDate.addDays(-1).toDate();

        List<TransactionEntity> transactions = new ArrayList<>(loanInfoList.size());
        List<LoanOperationValueEntity> operationValues = new ArrayList<>(loanInfoList.size());

        loanInfoList.forEach(loan -> {
            TransactionEntity t = new TransactionEntity();

            long customerId = loan.getCustomerId();


            t.setCustomerId(customerId);
            t.setAccountId(loan.getAccountId());
            t.setLoanId(loan.getLoanId());
            t.setTransactionDate(preAccountDate);
            t.setProductId(loan.getProductId());
            t.setPaymentChannel(loan.getLoanChannel());
            t.setFinancingSource(loan.getFinancialSource());


            List<LoanOperationValueEntity> values = new ArrayList<>();


            transactions.add(t);
            operationValues.addAll(values);
        });

        return new TransactionOperation(transactions, operationValues);
    }


}
