package com.scd.batch.statistics.service;

import com.scd.batch.common.constant.promotion.CouponsType;
import com.scd.batch.common.dao.financial.PlatformExpendReportDao;
import com.scd.batch.common.dao.statistics.ExpenditureStatDao;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.financial.PlatformExpendReport;
import com.scd.batch.common.entity.statistics.ExpenditureStatEntity;
import com.scd.batch.common.utils.EnumUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenditureStatService {

    @Autowired
    private DayCutService dayCutService;

    @Autowired
    private ExpenditureStatDao expenditureStatDao;

    @Autowired
    private PlatformExpendReportDao reportDao;

    public List<ExpenditureStatEntity> getStatList(long start, long num) {
        return expenditureStatDao.getStatList(TableSpec.getDefault(), start, num);
    }

    public boolean calculate() {

        // 1. 从促销相关表获取支出类型和金额

        return true;
    }

    public void batchUpdate2DB(List<ExpenditureStatEntity> entityList) {
        entityList.forEach(p -> {
            if (expenditureStatDao.checkExists(TableSpec.getDefault(), ShortDate.today().toDate(), 1) > 0) {
                expenditureStatDao.updateIncrement2DB(TableSpec.getDefault(), p);
            } else {
                expenditureStatDao.insert(TableSpec.getDefault(), p);
            }

            PlatformExpendReport report = buildFinancial(p);
            if (reportDao.checkExists(report.getExpendDate(), report.getExpendDetail()) > 0) {
                reportDao.updateIncrement(report);
            } else {
                reportDao.insert(report);
            }
        });
    }

    public PlatformExpendReport buildFinancial(ExpenditureStatEntity entity) {
        PlatformExpendReport report = new PlatformExpendReport();

        report.setExpendDate(entity.getTransDate());
        // 数字类型转换为字符串
        report.setExpendDetail(EnumUtils.getEnum(CouponsType.class, String.valueOf(entity.getType())).getName());
        report.setExpendPrn(new BigDecimal(entity.getAmount()));

        return report;
    }
}