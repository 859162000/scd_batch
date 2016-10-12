package com.scd.batch.trade.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scd.batch.common.utils.ShortDate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.scd.batch.trade.model.FailRecordEntity;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.model.loan.LoanInstallmentInfo;
import org.apache.commons.lang.time.DateFormatUtils;

public class LoanUtil {

    public static final String TIME_PATTERN = "HH:mm:ss";

    public static long summaryUnpaid(InstallmentInfo e) {
        return e.getPrincipal() - e.getPrincipalRepay()
                // + e.getPenalty() - e.getPenaltyRepay()
                + e.getInterest() - e.getInterestRepay()
                + e.getOverdue() - e.getOverdueRepay()
                + e.getViolate() - e.getViolateRepay()
                + e.getCharges() - e.getChargesRepay()
                + e.getManagement() - e.getManagementRepay()
                + e.getService() - e.getServiceRepay();
    }

    public static long summaryAllUnpaid(InstallmentInfo e) {
        return e.getPrincipal() - e.getPrincipalRepay()
                + e.getPenalty() - e.getPenaltyRepay()
                + e.getInterest() - e.getInterestRepay()
                + e.getOverdue() - e.getOverdueRepay()
                + e.getViolate() - e.getViolateRepay()
                + e.getCharges() - e.getChargesRepay()
                + e.getManagement() - e.getManagementRepay()
                + e.getService() - e.getServiceRepay();
    }

    public static long summaryPrincipalUnpaid(InstallmentInfo e) {
        return e.getPrincipal() - e.getPrincipalRepay();
    }

    public static List<FailRecordEntity> trans2FailRecord(List<LoanInfo> loanInfoList, ShortDate accountDate) {
        Date accDate = accountDate.toDate();

        return Lists.newArrayList(
                Iterables.transform(loanInfoList, loanInfo -> {

                    FailRecordEntity fail = new FailRecordEntity();
                    fail.setCustomerId(loanInfo.getCustomerId());
                    fail.setAccountId(loanInfo.getAccountId());
                    fail.setFailureId(loanInfo.getLoanId());
                    fail.setStatus(FailureStatus.INIT.type);
                    fail.setAccountDate(accDate);

                    return fail;
                }));
    }


    /**
     * 将分期按借据分组重新组织
     */
    public static List<LoanInfo> transform2LoanInfo(List<LoanInstallmentInfo> installments) {
        List<LoanInfo> loanInfoList = new ArrayList<>();

        LoanInfo loanInfo = null;

        for (LoanInstallmentInfo installment : installments) {

            if (loanInfo == null || installment.getLoanId() != loanInfo.getLoanId()) {

                if (loanInfo != null) {
                    loanInfoList.add(loanInfo);
                }

                loanInfo = installment.transform2LoanInfo();

                loanInfo.setInstallments(new ArrayList<>());

            }

            loanInfo.getInstallments().add(installment.transform2InstallmentInfo());

        }

        if (loanInfo != null) {
            loanInfoList.add(loanInfo);
        }

        return loanInfoList;
    }

    public static String formatByDateTimePattern(Date date) {
        return DateFormatUtils.format(date, TIME_PATTERN);
    }

}
