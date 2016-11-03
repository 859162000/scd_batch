package com.scd.batch.statistics.service;

import com.google.common.collect.Lists;
import com.scd.batch.common.dao.financial.UserRwDailyReportDao;
import com.scd.batch.common.dao.statistics.FundStatDao;
import com.scd.batch.common.dao.trade.RechargeLDao;
import com.scd.batch.common.dao.trade.WithdrawLDao;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.financial.UserRwDailyReport;
import com.scd.batch.common.entity.statistics.FundStatEntity;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FundStatService {

    @Autowired
    private DayCutService dayCutService;

    @Autowired
    private FundStatDao fundStatDao;

    @Autowired
    private RechargeLDao rechargeLDao;

    @Autowired
    private WithdrawLDao withdrawLDao;

    @Autowired
    private UserRwDailyReportDao reportDao;


    public List<FundStatEntity> getStatList(long start, long num) {
        return fundStatDao.getStatList(TableSpec.getDefault(), start, num);
    }

    public FundStatEntity calculate(ShortDate accountingDate, TableSpec tableSpec) {

        // 读取前一天的汇总数据
        ShortDate lastDate = accountingDate.addDays(-1);

        // TODO 状态枚举
        List<String> successStatsList = Lists.newArrayList("1", "2", "3", "4", "5");

        // 数据库读取提现汇总金额
        double rechargeSum = rechargeLDao.selectRechargeSumByDate(tableSpec, successStatsList, lastDate.toDate(),
                accountingDate.toDate());

        double withdrawSum = withdrawLDao.selectWithdrawSumByDate(tableSpec,
                successStatsList, lastDate.toDate(), accountingDate.toDate());

        // 写入到数据库
        FundStatEntity fundStatEntity = new FundStatEntity(accountingDate.toDate(), rechargeSum, withdrawSum);

        return fundStatEntity;
    }

    /**
     * 更新到数据库， 不存在则插入
     */
    public void update2DB(FundStatEntity fundStatEntity) {

        if (fundStatDao.checkExists(TableSpec.getDefault(), fundStatEntity.getTransDate()) == 1) {
            fundStatDao.updateIncrement(TableSpec.getDefault(), fundStatEntity);
        } else {
            fundStatDao.insert(TableSpec.getDefault(), fundStatEntity);
        }

        UserRwDailyReport report = buildFinancial(fundStatEntity);
        if (reportDao.checkExists(fundStatEntity.getTransDate()) > 0) {
            reportDao.updateIncrement(report);
        }
        else {
            reportDao.insert(report);
        }
    }


    public UserRwDailyReport buildFinancial(FundStatEntity entity) {
        UserRwDailyReport report = new UserRwDailyReport();
        report.setReportDate(entity.getTransDate());
        report.setRechargeAmt(new BigDecimal(entity.getRecharge()));
        report.setWithdrawAmt(new BigDecimal(entity.getWithdraw()));

        return report;
    }

}