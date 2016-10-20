package com.scd.batch.statistics.service.trans.impl;

import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.statistics.service.trans.iface.ProjectLimitBatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyankai on 2016/10/10.
 */
@Service
public class AssetStatBatImpl implements ProjectLimitBatService {

    @Autowired
    private DayCutService dayCutService;

    @Override
    public boolean calculate() {

        // 1. 从bid_loan和Product表，统计流动性产品（元）,定期计划（元）,定期项目（元）

        // 2. 从UserBalance表，统计财主账户余额（元），冻结金额（元），合计

        return true;
    }
}
