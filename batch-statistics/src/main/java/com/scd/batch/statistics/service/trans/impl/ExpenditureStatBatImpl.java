package com.scd.batch.statistics.service.trans.impl;

import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.statistics.service.trans.iface.ProjectLimitBatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyankai on 2016/10/10.
 */
@Service
public class ExpenditureStatBatImpl implements ProjectLimitBatService {

    @Autowired
    private DayCutService dayCutService;

    @Override
    public boolean calculate() {

        // 1. 从促销相关表获取支出类型和金额

        return true;
    }
}
