package com.scd.batch.interest.service;

import com.scd.batch.common.dao.acct.AcctUserAccumulateProfitDao;
import com.scd.batch.common.dao.acct.AcctUserDailyProfitDao;
import com.scd.batch.common.dao.acct.UserAccumulateProfitDao;
import com.scd.batch.common.dao.acct.UserDailyProfitDao;
import com.scd.batch.common.dao.bid.CreditorRelationDao;
import com.scd.batch.common.dao.interest.UserAssetsDao;
import com.scd.batch.common.dao.trade.UserBalanceDao;
import com.scd.batch.common.entity.acct.UserAccumulativeProfitEntity;
import com.scd.batch.common.entity.acct.UserDailyProfitEntity;
import com.scd.batch.common.entity.interest.UserAssetsEntity;
import com.scd.batch.common.entity.statistics.trade.BalanceAssetsEntity;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.interest.entity.UserProfitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 昨日收益统计
 */
@Service
public class UserDailyProfitCalculateService {


    @Autowired
    private UserDailyProfitDao profitDao;

    @Autowired
    private UserAccumulateProfitDao accumulateProfitDao;

    @Autowired
    private AcctUserDailyProfitDao acctUserDailyProfitDao;

    @Autowired
    private AcctUserAccumulateProfitDao acctUserAccumulateProfitDao;

    @Autowired
    private UserBalanceDao balanceDao;

    @Autowired
    private UserAssetsDao assetsDao;

    // 取数据库计算昨日收益
    public List<UserProfitEntity> calculateProfit(TableSpec tableSpec, Date transDate, List<Long> batchIdList) {

        // 当前日期的前一天
        List<BalanceAssetsEntity> entityList = balanceDao.selectBalanceByBatchUid(tableSpec,
                ShortDate.valueOf(transDate).addDays(-1).toDate(),
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
            Date lastDate = ShortDate.valueOf(transDate).addDays(-1).toDate();
            UserAssetsEntity assets = assetsDao.selectAssets(p.getUid(), lastDate);
            double lastTotal = assets.getAssets();

            // 昨日收益 = 当日总资产 - 昨日总资产 + 提现金额 - 充值金额
            double profit = currentTotal - lastTotal + p.getWithdraw() - p.getRecharge();

            UserProfitEntity entity = new UserProfitEntity(
                    0,
                    p.getUid(),
                    transDate,
                    new BigDecimal(profit),
                    new BigDecimal(0),
                    new BigDecimal(profit),
                    new BigDecimal(0)
            );

            profitEntityList.add(entity);

            // 更新总资产
            assets.setTransDate(transDate);
            assets.setAssets(currentTotal);
            assetsDao.update(assets);

        }
        return profitEntityList;
    }

    // 更新收益信息到数据库，不存在则先写入
    public void update2DB(List<UserProfitEntity> profitEntityList) {

        profitEntityList.forEach(p -> {
            // 昨日收益
            UserDailyProfitEntity entity = new UserDailyProfitEntity(
                    0,
                    p.getUid(),
                    DateUtil.DateToString(p.getDate(), DateStyle.YYYYMMDD),
                    p.getProfit(),
                    new BigDecimal(0)
            );

            // 累计昨日收益
            UserAccumulativeProfitEntity accumulativeProfitEntity = new UserAccumulativeProfitEntity(
                    0,
                    p.getUid(),
                    p.getTotalProfit(),
                    new BigDecimal(0)
            );

            // TODO 分库分表
            // 按日统计收益，更新本地库
            if (profitDao.checkExists(TableSpec.getDefault(), entity.getUid(), entity.getDate()) > 0) {
                profitDao.updateIncrement2DB(TableSpec.getDefault(), entity);
            } else {
                profitDao.insert(TableSpec.getDefault(), entity);
            }

            if (accumulateProfitDao.checkExists(TableSpec.getDefault(), accumulativeProfitEntity.getUid()) > 0) {
                accumulateProfitDao.updateIncrement2DB(TableSpec.getDefault(), accumulativeProfitEntity);
            } else {
                accumulateProfitDao.insert(TableSpec.getDefault(), accumulativeProfitEntity);
            }

            // 更新acct


            if (acctUserDailyProfitDao.checkExists(TableSpec.getDefault(), entity.getUid(), entity.getDate()) > 0) {
                acctUserDailyProfitDao.updateIncrement2DB(TableSpec.getDefault(), entity);
            } else {
                acctUserDailyProfitDao.insert(TableSpec.getDefault(), entity);
            }

            if (acctUserAccumulateProfitDao.checkExists(TableSpec.getDefault(), accumulativeProfitEntity.getUid()) >
                    0) {
                acctUserAccumulateProfitDao.updateIncrement2DB(TableSpec.getDefault(), accumulativeProfitEntity);
            } else {
                acctUserAccumulateProfitDao.insert(TableSpec.getDefault(), accumulativeProfitEntity);
            }
        });
    }

}