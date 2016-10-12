package com.scd.batch.trade.service.trans.impl;

import com.google.common.collect.Lists;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.FundStatEntity;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.statistics.dao.FundStatDao;
import com.scd.batch.trade.dao.trans.RechargeLDao;
import com.scd.batch.trade.dao.trans.WithdrawLDao;
import com.scd.batch.trade.service.trans.iface.FundStatBatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liyankai on 2016/10/10.
 */
@Service
public class FundStatBatImpl implements FundStatBatService {

    @Autowired
    private DayCutService dayCutService;

    @Autowired
    private FundStatDao fundStatDao;

    @Autowired
    private RechargeLDao rechargeLDao;

    @Autowired
    private WithdrawLDao withdrawLDao;

    @Override
    public boolean calculate() {

        // 读取前一天的汇总数据
        Date accountingDate = dayCutService.load();
        Date lastDate = DateUtil.addDay(accountingDate, -1);

        // TODO 状态枚举
        List<String> successStatsList = Lists.newArrayList("1");

        // 数据库读取体现汇总金额
        double rechargeSum = rechargeLDao.selectRechargeSumByDate(successStatsList.stream().map(p -> "'" + p + "'")
                .reduce("", String::concat), lastDate, accountingDate);

        double withdrawSum = withdrawLDao.selectWithdrawSumByDate(successStatsList.stream().map(p -> "'" + p + "'")
                .reduce("", String::concat), lastDate, accountingDate);

        // 写入到数据库
        FundStatEntity fundStartEntity = new FundStatEntity(accountingDate, rechargeSum, withdrawSum);
        fundStatDao.insert(fundStartEntity);

        return true;
    }
}
