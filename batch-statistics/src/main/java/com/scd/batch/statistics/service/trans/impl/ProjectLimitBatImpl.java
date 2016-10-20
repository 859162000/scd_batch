package com.scd.batch.statistics.service.trans.impl;

import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.statistics.service.trans.iface.ProjectLimitBatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyankai on 2016/10/10.
 */
@Service
public class ProjectLimitBatImpl implements ProjectLimitBatService {

    @Autowired
    private DayCutService dayCutService;

    @Override
    public boolean calculate() {

        // 1. 从bid_project获取字段：项目标名称,项目标编号,项目计划融资总额度(元),已发标额度,剩余可发标额度(元)

        // 2. 从放款相关表，获取字段：融资方已提现金额(元)

        // 3. 从bid_realrepay获取字段：已还本金(元)，未还本金(元)，已还利息(元)，未还利息(元)

        return true;
    }
}
