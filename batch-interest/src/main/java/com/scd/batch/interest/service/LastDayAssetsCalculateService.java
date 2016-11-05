package com.scd.batch.interest.service;

import com.scd.batch.common.dao.interest.UserAssetsDao;
import com.scd.batch.common.dao.trade.UserBalanceDao;
import com.scd.batch.common.entity.interest.UserAssetsEntity;
import com.scd.batch.common.entity.statistics.trade.BalanceAssetsEntity;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.interest.entity.UserProfitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 昨日总资产
 */
@Service
public class LastDayAssetsCalculateService {

    @Autowired
    private UserBalanceDao balanceDao;

    @Autowired
    private UserAssetsDao assetsDao;

    // 取数据库计算昨日收益
    public List<UserProfitEntity> calculateProfit(TableSpec tableSpec, Date transDate, List<Long> batchIdList) {

        Date lastDate = ShortDate.valueOf(transDate).addDays(-1).toDate();
        // 当前日期的前一天
        List<BalanceAssetsEntity> entityList = balanceDao.selectBalanceByBatchUid(tableSpec,
                lastDate,
                batchIdList);

        List<UserProfitEntity> profitEntityList = new ArrayList<>();

        for (BalanceAssetsEntity p : entityList) {

            /** 总资产 = 可用余额 + 体现冻结金额 + 投资冻结金额 + 还款冻结金额 + 活期赎回冻结金额
             + 活期本金  + 定期赚待收本金 + 定期计划待收本金
             */
            double currentTotal = p.getUsableSa() + p.getWithdrawFreezeSa() + p.getInvestFreezeSa() +
                    p.getRepayFreezeSa() + p.getCapitalFreezeSa()
                    + p.getCurrentCapital() + p.getFixendCapital() + p.getFixperiodCapital();

            // 昨日总资产
            UserAssetsEntity assets = assetsDao.selectAssets(p.getUid(), lastDate);

            if (assets == null) {
                assets = new UserAssetsEntity();
                assets.setUid(p.getUid());
                assets.setTransDate(lastDate);
                assets.setAssets(currentTotal);
                assetsDao.insert(assets);
            } else {
                // 更新总资产字段
                assets.setTransDate(lastDate);
                assets.setAssets(currentTotal);
                assetsDao.update(assets);
            }

        }
        return profitEntityList;
    }
}
