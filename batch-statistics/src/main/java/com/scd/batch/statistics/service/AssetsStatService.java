package com.scd.batch.statistics.service;

import com.scd.batch.common.dao.financial.PlatformAssetReportDao;
import com.scd.batch.common.dao.statistics.AssetsStatDao;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.financial.PlatformAssetReport;
import com.scd.batch.common.entity.statistics.AssetsStatEntity;
import com.scd.batch.common.utils.TableSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AssetsStatService {

    @Autowired
    private AssetsStatDao statDao;

    @Autowired
    private DayCutService dayCutService;

    @Autowired
    private PlatformAssetReportDao reportDao;

    public boolean calculate() {

        // 1. 从bid_loan和Product表，统计流动性产品（元）,定期计划（元）,定期项目（元）

        // 2. 从UserBalance表，统计财主账户余额（元），冻结金额（元），合计

        return true;
    }

    public void updateIncreament2DB(AssetsStatEntity entity) {
        if (statDao.checkExists(TableSpec.getDefault(), entity.getTransDate()) > 0) {
            statDao.update2DB(TableSpec.getDefault(), entity);
        } else {
            statDao.insert(TableSpec.getDefault(), entity);
        }

        PlatformAssetReport report = buildFinancial(entity);
        if (reportDao.checkExists(report.getReport_date()) > 0) {
            reportDao.updateIncrement(report);
        } else {
            reportDao.insert(report);
        }

    }

    public PlatformAssetReport buildFinancial(AssetsStatEntity entity) {
        PlatformAssetReport report = new PlatformAssetReport();

        report.setReport_date(entity.getTransDate());
        report.setCurrentAssetTotal(new BigDecimal(entity.getCurrent()));
        report.setFixPlanAssetTotal(new BigDecimal(entity.getFixPlan()));
        report.setFixProjectAssetTotal(new BigDecimal(entity.getFixProject()));
        report.setUsableBalanceTotal(new BigDecimal(entity.getBalance()));
        report.setFreezeBalanceTotal(new BigDecimal(entity.getFrozen()));

        return report;
    }
}