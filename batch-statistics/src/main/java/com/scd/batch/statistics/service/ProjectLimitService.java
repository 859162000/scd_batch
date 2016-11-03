package com.scd.batch.statistics.service;

import com.scd.batch.common.dao.financial.ProjectQuotaReportDao;
import com.scd.batch.common.dao.statistics.ProjectLimitDao;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.financial.ProjectQuotaReport;
import com.scd.batch.common.entity.statistics.ProjectLimitEntity;
import com.scd.batch.common.utils.TableSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProjectLimitService {

    @Autowired
    private DayCutService dayCutService;

    @Autowired
    private ProjectLimitDao projectLimitDao;

    @Autowired
    private ProjectQuotaReportDao reportDao;

    public List<ProjectLimitEntity> getList(long start, long num) {
        return projectLimitDao.getList(TableSpec.getDefault(), start, num);
    }

    public boolean calculate() {

        // 1. 从bid_project获取字段：项目标名称,项目标编号,项目计划融资总额度(元),已发标额度,剩余可发标额度(元)

        // 2. 从放款相关表，获取字段：融资方已提现金额(元)

        // 3. 从bid_realrepay获取字段：已还本金(元)，未还本金(元)，已还利息(元)，未还利息(元)

        return true;
    }

    // 存在则更新，不存在则插入
    public void batchUpdate2DB(List<ProjectLimitEntity> projectLimitEntityList) {

        projectLimitEntityList.forEach(p -> {

            if (projectLimitDao.checkExists(TableSpec.getDefault(), p.getProjectCode()) > 0) {
                projectLimitDao.update2DB(TableSpec.getDefault(), p);
            } else {
                projectLimitDao.insert(TableSpec.getDefault(), p);
            }

            ProjectQuotaReport report = buildFinance(p);
            if (reportDao.checkExists(report.getReportDate(), report.getProjectCode()) > 0) {
                reportDao.updateIncrement(report);
            }
            else {
                reportDao.insert(report);
            }
        });
    }

    public ProjectQuotaReport buildFinance(ProjectLimitEntity entity) {
        ProjectQuotaReport report = new ProjectQuotaReport();

        report.setProjectCode(entity.getProjectCode());
        report.setProjectName(entity.getProjectName());

        report.setReportDate(dayCutService.load());
        report.setProjectFinancingAmt(new BigDecimal(entity.getTotal()));
        report.setIssuedAmt(new BigDecimal(entity.getBid()));
        report.setRemainIssueAmt(new BigDecimal(entity.getUnbid()));
        report.setWithdrewAmt(new BigDecimal(entity.getWithdraw()));
        report.setRepaidPrn(new BigDecimal(entity.getPaidPrincipal()));
        report.setUnrepayPrn(new BigDecimal(entity.getUnpaidPrincipal()));
        report.setRepaidInterest(new BigDecimal(entity.getPaidInterest()));
        report.setUnrepayInterest(new BigDecimal(entity.getUnpaidInterest()));

        return report;
    }

}