package com.scd.batch.trade.service.loan;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.trade.common.LoanUtil;
import com.scd.batch.trade.dao.BatchLoanDAO;
import com.scd.batch.trade.model.loan.AutoRepayInfo;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanAutoRepayService {
    @Resource
    private BatchLoanDAO batchLoanDAO;

    public List<Long> getAllOverdueCustomerId(TableSpec ts, ShortDate accountDate) {
        return batchLoanDAO.selectAllOverdueCustomerId(ts, accountDate.toDate());
    }

    public List<InstallmentInfo> getInstallmentsByCustomerId(TableSpec ts, List<Long> customerIdList,
                                                             ShortDate accountDate) {
        if (CollectionUtils.isEmpty(customerIdList)) {
            return new ArrayList<>();
        }
        return batchLoanDAO.selectOverdueInstallmentByCustomerId(ts, customerIdList, accountDate.toDate());
    }

    /**
     * 返回的 map 的 key 是 customerId
     */
    public Map<Long, List<AutoRepayInfo>> groupAndSort(List<InstallmentInfo> batch, ShortDate accountDate) {
        Map<Long, List<InstallmentInfo>> customer2installment = new HashMap<>();
        batch.forEach(installment -> {
            long customerId = installment.getCustomerId();
            if (customer2installment.get(customerId) == null) {
                customer2installment.put(customerId, new ArrayList<>());
            }

            customer2installment.get(customerId).add(installment);
        });

        Map<Long, List<AutoRepayInfo>> customer2loan = new HashMap<>();

        customer2installment.forEach((customerId, installments) -> {
            AutoRepayInfo item = null;
            for (InstallmentInfo installment : installments) {
                if (item == null) {                 // 第一次循环时
                    item = buildInitialAutoRepayInfo(installment, accountDate);
                } else if (item.getLoanId() == installment.getLoanId()) { // 相同借据的不同分期累加应还
                    item.setAmount(LoanUtil.summaryAllUnpaid(installment) + item.getAmount());
                    item.setScheduleNo(1 + item.getScheduleNo());

                    // 当前账务日期在还款日之后则为有逾期的分期
                    if (accountDate.isAfter(ShortDate.valueOf(installment.getDueDate()))) {
                        item.setOverdue(true);
                    }
                } else {
                    if (customer2loan.get(item.getCustomerId()) == null) {
                        customer2loan.put(item.getCustomerId(), new ArrayList<>());
                    }
                    customer2loan.get(item.getCustomerId()).add(item);

                    // 开始新的一个借据
                    item = buildInitialAutoRepayInfo(installment, accountDate);
                }
            } // fori
            if (item != null) {
                if ( customer2loan.get(item.getCustomerId()) == null) {
                    customer2loan.put(item.getCustomerId(), new ArrayList<>());
                }
                customer2loan.get(item.getCustomerId()).add(item);
            }
        }); // foreach

        // 排序
        sortMapValueList(customer2loan);

        return customer2loan;

    }

    public List<AutoRepayInfo> mergeMapValue(Map<Long, List<AutoRepayInfo>> customer2loan) {
        List<AutoRepayInfo> result = new ArrayList<>();
        customer2loan.forEach((customerId, repayInfoList) -> result.addAll(repayInfoList));

        return result;
    }

    private void sortMapValueList(Map<Long, List<AutoRepayInfo>> customer2loan) {
        customer2loan.forEach((customerId, repayInfoList) -> Collections.sort(repayInfoList,
                (repay1, repay2) -> {
                    long delta = repay2.getAmount() - repay1.getAmount();
                    int result = delta > 0 ? 1 : (delta < 0 ? -1 : 0);
                    // 都是逾期金额大的在前面
                    if (repay1.isOverdue() && repay2.isOverdue()) {
                        return result;
                    }

                    // 都不是逾期金额大的在前面
                    if (!(repay1.isOverdue() || repay2.isOverdue())) {
                        return result;
                    }

                    return repay1.isOverdue() ? -1 : 1;
                }));
    }

    private AutoRepayInfo buildInitialAutoRepayInfo(InstallmentInfo installment, ShortDate accountDate) {
        AutoRepayInfo item = new AutoRepayInfo();
        item.setScheduleNo(1);
        item.setCustomerId(installment.getCustomerId());
        item.setLoanId(installment.getLoanId());
        item.setAmount(LoanUtil.summaryAllUnpaid(installment));
        item.setProductId(installment.getProductId());

        // 当前账务日期在还款日之后则为有逾期的分期
        if (accountDate.isAfter(ShortDate.valueOf(installment.getDueDate()))) {
            item.setOverdue(true);
        }

        return item;
    }
}
