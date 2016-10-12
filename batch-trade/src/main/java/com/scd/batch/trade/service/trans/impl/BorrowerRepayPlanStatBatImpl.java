package com.scd.batch.trade.service.trans.impl;

import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.trade.service.trans.iface.BorrowerRepayPlanStatBatService;
import com.scd.batch.trade.service.trans.iface.ProjectLimitBatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyankai on 2016/10/10.
 */
@Service
public class BorrowerRepayPlanStatBatImpl implements BorrowerRepayPlanStatBatService {

    @Autowired
    private DayCutService dayCutService;

    @Override
    public boolean calculate() {

        // 1. Borrower，BorrowerRelation，Project，RepayPlan，RepayReal

        return true;
    }
}
