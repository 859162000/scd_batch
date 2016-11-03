package com.scd.batch.statistics.service;

import com.scd.batch.common.dao.financial.BorrowerRepaymentPlanDao;
import com.scd.batch.common.dao.statistics.BorrowerRepayPlanStatDao;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.financial.BorrowerRepaymentPlan;
import com.scd.batch.common.entity.statistics.BorrowerRepayPlanStatEntity;
import com.scd.batch.common.utils.TableSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BorrowerRepayPlanStatService {

    @Autowired
    private BorrowerRepayPlanStatDao statDao;

    @Autowired
    private DayCutService dayCutService;

    @Autowired
    private BorrowerRepaymentPlanDao planDao;

    public List<BorrowerRepayPlanStatEntity> getStatList(long start, long num) {
        return statDao.getStatList(TableSpec.getDefault(), start, num);
    }


    public boolean calculate() {

        // 1. Borrower，BorrowerRelation，Project，RepayPlan，RepayReal

        return true;
    }

    public void batchUpdate2DB(List<BorrowerRepayPlanStatEntity> entityList) {
        entityList.forEach(p -> {
            if (statDao.checkExists(TableSpec.getDefault(), p.getDueDate(),
                    p.getBorrowerId(),
                    p.getProjectCode()) == 1) {
                statDao.update2DB(TableSpec.getDefault(), p);
            } else {
                statDao.insert(TableSpec.getDefault(), p);
            }

            BorrowerRepaymentPlan plan = buildFinancial(p);
            if (planDao.checkExists(plan.getRepaymentDate(), plan.getProjectCode()) > 0) {
                planDao.updateIncrement(plan);
            } else {
                planDao.insert(plan);
            }
        });
    }

    public BorrowerRepaymentPlan buildFinancial(BorrowerRepayPlanStatEntity entity) {

        BorrowerRepaymentPlan plan = new BorrowerRepaymentPlan();
        plan.setRepaymentDate(entity.getDueDate());
        plan.setBorrower(entity.getBorrowerName());
        plan.setProjectCode(entity.getProjectCode());
        plan.setRepaymentType(String.valueOf(entity.getRepayType()));
        plan.setRepaymentPrn(new BigDecimal(entity.getDuePrincipal()));
        plan.setRepaymentInterest(new BigDecimal(entity.getDueInterest()));
        plan.setActualRepaymentPrn(new BigDecimal(entity.getRepayPrincipal()));
        plan.setActualRepaymentInterest(new BigDecimal(entity.getRepayInterest()));

        return plan;
    }

}