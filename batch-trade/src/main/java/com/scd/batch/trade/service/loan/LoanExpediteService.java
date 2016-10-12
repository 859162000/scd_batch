package com.scd.batch.trade.service.loan;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.trade.common.LoanUtil;
import com.scd.batch.trade.dao.BatchLoanDAO;
import com.scd.batch.trade.model.loan.ExpediteInfo;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

public class LoanExpediteService {

    @Resource
    private BatchLoanDAO batchLoanDAO;

    @Value(value = "#{configProperties['loadExpedite.productId.default']}")
    private Long productIdDefault;
    @Value(value = "#{configProperties['loadExpedite.productId.expeditesList'].split(';')}")
    private List<String> expeditesList;

    private Multimap<Long, Integer> expediteTypeMap;

    public void init() {
        expediteTypeMap = ArrayListMultimap.create();
        for (String expedites : expeditesList) {
            String[] expediteTypes = expedites.split(",");
            long productId = Long.parseLong(expediteTypes[0].trim());
            for (int i = 1; i < expediteTypes.length; ++i) {
                expediteTypeMap.put(productId, Integer.parseInt(expediteTypes[i].trim()));
            }
        }
    }

    public List<Long> getAllLoanId(TableSpec tableSpec) {
        return batchLoanDAO.getAllLoanId(tableSpec);
    }

    /**
     * 获取全部贷款分期计划
     * @param tableSpec
     * @param accountDate
     * @param loanIdList
     * @return
     */
    public List<ExpediteInfo> getExpedite(TableSpec tableSpec, ShortDate accountDate, List<Long> loanIdList) {
        List<InstallmentInfo> infos = batchLoanDAO.selectInstallmentByLoanIds(tableSpec, loanIdList);
        return calc(accountDate, infos);
    }

    /**
     * 遍历后筛选符合条件的分期计划
     * @param accountDate
     * @param infos
     * @return
     */
    private List<ExpediteInfo> calc(ShortDate accountDate, List<InstallmentInfo> infos) {
        List<ExpediteInfo> expediteInfos = Lists.newArrayList();
        if (CollectionUtils.isEmpty(infos)) {
            return expediteInfos;
        }
        Table<Long, Integer, Date> expediteDateTable = createExpediteDateMap(accountDate);

        for (InstallmentInfo installmentInfo : infos) {
            long expediteProductId = expediteDateTable.containsRow(installmentInfo.getProductId())
                    ? installmentInfo.getProductId() : productIdDefault;
            for (Map.Entry<Integer, Date> entry : expediteDateTable.row(expediteProductId).entrySet()) {
                Date expediteDate = entry.getValue();
                if (installmentInfo.getDueDate().equals(expediteDate)) {
                    int expediteDay = entry.getKey();
                    expediteInfos.add(createExpedite(installmentInfo, expediteDay));
                    break;
                }
            }
        }
        return expediteInfos;
    }

    /**
     * 封装文件对象行
     * @param installmentInfo
     * @param expediteDay
     * @return
     */
    private ExpediteInfo createExpedite(InstallmentInfo installmentInfo, int expediteDay) {
        ExpediteInfo expediteInfo = new ExpediteInfo();
        expediteInfo.setCustomerId(installmentInfo.getCustomerId());
        expediteInfo.setDueDate(installmentInfo.getDueDate());
        expediteInfo.setProductId(installmentInfo.getProductId());
        expediteInfo.setExpediteDay(expediteDay);
        expediteInfo.setLoanId(installmentInfo.getLoanId());
        expediteInfo.setMoney(LoanUtil.summaryAllUnpaid(installmentInfo));
        return expediteInfo;
    }

    private Table createExpediteDateMap(ShortDate accountDate) {
        Table<Long, Integer, Date> expediteDateTable = HashBasedTable.create();
        for (Map.Entry<Long, Integer> entry : expediteTypeMap.entries()) {
            long productId = entry.getKey();
            int expediteDay = entry.getValue();
            ShortDate expediteDate = accountDate.addDays(expediteDay);
            expediteDateTable.put(productId, expediteDay, expediteDate.toDate());
        }
        return expediteDateTable;
    }
}
