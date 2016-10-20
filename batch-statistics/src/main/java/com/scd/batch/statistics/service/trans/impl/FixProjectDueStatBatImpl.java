package com.scd.batch.statistics.service.trans.impl;

import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.statistics.service.trans.iface.FixProjectDueStatBatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyankai on 2016/10/10.
 */
@Service
public class FixProjectDueStatBatImpl implements FixProjectDueStatBatService {

    @Autowired
    private DayCutService dayCutService;

    @Override
    public boolean calculate() {

        // FixPlanProduct和FixProjectProduct表
        // 到期时间为产品结束时间endTime
        // 本金和利息，已出售额度investedAmount，总利息无法计算。

        return true;
    }
}
