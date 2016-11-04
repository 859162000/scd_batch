package com.scd.batch.statistics.service;


import com.scd.batch.common.dao.financial.FixProdDueDateReportDao;
import com.scd.batch.common.dao.statistics.FixProjectDueStatDao;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.financial.FixProdDueDateReport;
import com.scd.batch.common.entity.statistics.FixDueStatEntity;
import com.scd.batch.common.utils.TableSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class FixProjectDueStatService {

    @Autowired
    private DayCutService dayCutService;

    @Resource
    private FixProjectDueStatDao statDao;

    @Autowired
    private FixProdDueDateReportDao reportDao;

    public boolean calculate() {

        // FixPlanProduct和FixProjectProduct表
        // 到期时间为产品结束时间endTime
        // 本金和利息，已出售额度investedAmount，总利息无法计算。

        return true;
    }

    public boolean batchUpdate2DB(List<FixDueStatEntity> statEntityList) {

        statEntityList.forEach(p -> {
            if (statDao.checkExists(TableSpec.getDefault(), p.getDueDate()) == 1) {
                statDao.update2DB(TableSpec.getDefault(), p);
            } else {
                statDao.insert(TableSpec.getDefault(), p);
            }

            FixProdDueDateReport report = buildFinancial(p);
            if (reportDao.checkExists(report.getDueDate()) > 0) {
                reportDao.updateIncrement(report);
            } else {
                reportDao.insert(report);
            }
        });

        return true;
    }

    public FixProdDueDateReport buildFinancial(FixDueStatEntity entity) {

        FixProdDueDateReport report = new FixProdDueDateReport();

        report.setDueDate(entity.getDueDate());
        report.setFixPlanPrn(new BigDecimal(entity.getFixPlanPrincipal()));
        report.setFixPlanInterest(new BigDecimal(entity.getFixPlanInterest()));
        report.setFixProjectPrn(new BigDecimal(entity.getFixProjectPrincipal()));
        report.setFixProjectInterest(new BigDecimal(entity.getFixProjectInterest()));

        return report;
    }
}