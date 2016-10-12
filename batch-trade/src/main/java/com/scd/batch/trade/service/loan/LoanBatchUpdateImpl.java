package com.scd.batch.trade.service.loan;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.List;

import javax.annotation.Resource;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.common.LoanUtil;
import com.scd.batch.trade.dao.BatchLoanDAO;
import com.scd.batch.trade.dao.FailRecordDAO;
import com.scd.batch.trade.model.FailRecordEntity;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.service.BatchUpdateService;
import org.springframework.stereotype.Service;

@Service
public class LoanBatchUpdateImpl extends BatchUpdateService implements LoanBatchUpdate {

    @Resource
    private LoanService loanService;

    @Override
    public int[] updateLoanInstallment(List<InstallmentInfo> installments) {

        if (isEmpty(installments)) {
            return new int[0];
        }

        BatchLoanDAO batchLoanDAO = getDAO(BatchLoanDAO.class);

        installments.forEach(i -> batchLoanDAO.updateInstallmentSchedule(i.getCustomerId(), i));

        return flush2UpdateCounts0();
    }

    @Override
    public int[] updateLoan(List<LoanInfo> loanInfoList) {

        if (isEmpty(loanInfoList)) {
            return new int[0];
        }

        BatchLoanDAO batchLoanDAO = getDAO(BatchLoanDAO.class);

        loanInfoList.forEach(loanInfo -> batchLoanDAO.updateLoan(loanInfo.getCustomerId(), loanInfo));

        return flush2UpdateCounts0();
    }

    @Override
    public int[] updateAccountDebitStatus(List<LoanInfo> loanInfoList) {

        if (isEmpty(loanInfoList)) {
            return new int[0];
        }

        BatchLoanDAO batchLoanDAO = getDAO(BatchLoanDAO.class);

        return flush2UpdateCounts0();
    }

    @Override
    public int[] addFailRecord(List<LoanInfo> loanInfoList, ShortDate accountDate) {
        if (isEmpty(loanInfoList)) {
            return new int[0];
        }

        List<FailRecordEntity> fails = LoanUtil.trans2FailRecord(loanInfoList, accountDate);

        FailRecordDAO failRecordDAO = getDAO(FailRecordDAO.class);

        failRecordDAO.insert(fails);

        return flush2UpdateCounts0();
    }

    @Override
    public int[] addOperation(List<LoanInfo> loanInfoList, ShortDate accountDate) {
        if (isEmpty(loanInfoList)) {
            return new int[0];
        }
        flushStatements();

        return new int[0];
    }
}
