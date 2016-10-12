package com.scd.batch.trade.service.loan;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.model.loan.LoanInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanUpdateService {
    private static final Logger logger = LoggerFactory.getLogger(LoanUpdateService.class);

    @Resource
    private LoanBatchUpdate loanBatchUpdate;

    @Transactional
    public void updateLoanInfo(List<LoanInfo> loanInfoList, ShortDate accountDate) {

        // 记录未更新成功的借据
        List<LoanInfo> failedLoanInfoList = new ArrayList<>();

        // 记录成功的借据
        List<LoanInfo> successLoanInfoList = new ArrayList<>();

        // 更新借据
        int[] loanCount = loanBatchUpdate.updateLoan(loanInfoList);

        logger.info("updateLoan count: {}", ArrayUtils.toString(loanCount));

        // 记录要更新的还款计划
        List<InstallmentInfo> updateInstallments = new ArrayList<>();

        for (int i = 0; i < loanCount.length; i++) {

            LoanInfo loanInfo = loanInfoList.get(i);

            // 更新成功的
            if (loanCount[i] == 1) {

                successLoanInfoList.add(loanInfo);

                updateInstallments.addAll(loanInfo.getInstallments());

            } else {
                failedLoanInfoList.add(loanInfo);
            }
        }

        // 更新还款计划
        int[] installmentCount = loanBatchUpdate.updateLoanInstallment(updateInstallments);

        logger.info("updateLoanInstallment count: {}", ArrayUtils.toString(installmentCount));

        // 更新账号
        int[] accountCount = loanBatchUpdate.updateAccountDebitStatus(successLoanInfoList);

        logger.info("updateAccountDebitStatus count: {}", ArrayUtils.toString(accountCount));

        // 未更新成功的入错误记录表
        loanBatchUpdate.addFailRecord(failedLoanInfoList, accountDate);

        // 入流水
        loanBatchUpdate.addOperation(successLoanInfoList, accountDate);

    }
}
