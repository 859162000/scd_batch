package com.scd.batch.interest.service;

import com.scd.batch.common.constant.trans.UserType;
import com.scd.batch.common.constant.trans.WithDrawLStatus;
import com.scd.batch.common.dao.acct.AcctUserAccumulateProfitDao;
import com.scd.batch.common.dao.acct.AcctUserDailyProfitDao;
import com.scd.batch.common.dao.acct.UserAccumulateProfitDao;
import com.scd.batch.common.dao.acct.UserDailyProfitDao;
import com.scd.batch.common.dao.bid.CreditRepayRealDao;
import com.scd.batch.common.dao.interest.UserAssetsDao;
import com.scd.batch.common.dao.trade.RechargeLDao;
import com.scd.batch.common.dao.trade.UserBalanceDao;
import com.scd.batch.common.dao.trade.WithdrawLDao;
import com.scd.batch.common.entity.acct.UserAccumulativeProfitEntity;
import com.scd.batch.common.entity.acct.UserDailyProfitEntity;
import com.scd.batch.common.entity.interest.UserAssetsEntity;
import com.scd.batch.common.entity.statistics.trade.BalanceAssetsEntity;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.interest.entity.UserProfitEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 昨日收益统计，先决条件：需要先跑跑昨日收益任务
 */
@Service
public class UserDailyProfitCalculateService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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

    @Autowired
    private WithdrawLDao withdrawLDao;

    @Autowired
    private RechargeLDao rechargeLDao;

    // 取数据库计算昨日收益
    public List<UserProfitEntity> calculateProfit(TableSpec tableSpec, Date transDate, List<Long> batchIdList) {

        Date lastDate = ShortDate.valueOf(transDate).addDays(-1).toDate();
        Date preYestodayDate = ShortDate.valueOf(transDate).addDays(-2).toDate();

        // 有余额的用户列表
        List<BalanceAssetsEntity> entityList = balanceDao.selectBalanceByBatchUid(tableSpec,
                lastDate,
                UserType.INVEST.getType(),
                batchIdList);

        List<UserProfitEntity> profitEntityList = new ArrayList<>();

        // 昨日收益 = 每日收益，0

        for (BalanceAssetsEntity p : entityList) {

            UserAssetsEntity lastAssets = assetsDao.selectAssets(p.getUid(), lastDate);
            Date lastUpdateTime = lastDate;
            double lastTotal = 0.0;
            if (lastAssets == null) {
                // 昨日总资产不存在，则跳过这条余额不处理
                continue;
            } else {
                lastTotal = lastAssets.getAssets();
                lastUpdateTime = lastAssets.getModified();
                logger.debug("昨日总资产：" + lastTotal + ", UID:" + p.getUid() + ", lastUpdateTime:" + lastUpdateTime);
            }

            // 昨日的前一天的总资产
            UserAssetsEntity preYestodayassets = assetsDao.selectAssets(p.getUid(), preYestodayDate);
            double preYestodayTotal = 0.0;
            if (preYestodayassets != null) {
                // 前日总资产不存在，则跳过不处理
                continue;
            } else {
                preYestodayTotal = preYestodayassets.getAssets();
                logger.debug("前日总资产：" + preYestodayTotal + ", UID:" + p.getUid());
                lastUpdateTime = preYestodayassets.getModified();
            }

            Date untilTime = transDate;
            // 按天统计提现金额，开始时间为上次更新余额的时间
            double withdrawSumByDate = withdrawLDao.selectWithdrawSumByDate(tableSpec,
                    WithDrawLStatus.getSuccessStatusList(),
                    lastUpdateTime,
                    untilTime,
                    p.getUid());

            // 按天计算充值金额
            double rechargeSumByDate = rechargeLDao.selectRechargeSumByDate(tableSpec,
                    WithDrawLStatus.getSuccessStatusList(),
                    lastUpdateTime,
                    untilTime,
                    p.getUid());

            // 总的昨日收益 = 当日总资产 - 昨日总资产 + 提现金额 - 充值金额
            double profit = lastTotal - preYestodayTotal + withdrawSumByDate - rechargeSumByDate;

            logger.debug("昨日收益：" + profit + ", lastTotal:" +
                    lastTotal + ", preYestodayTotal:" + preYestodayTotal +
                    ", withdrawSumByDate:" + withdrawSumByDate +
                    ", rechargeSumByDate:" + rechargeSumByDate);

            // 累加到总收益里面去
            UserProfitEntity entity = new UserProfitEntity(
                    0,
                    p.getUid(),
                    lastDate,
                    new BigDecimal(profit),
                    new BigDecimal(0),
                    new BigDecimal(profit),
                    new BigDecimal(0)
            );

            profitEntityList.add(entity);

        }
        return profitEntityList;
    }

    // 更新收益信息到数据库，不存在则先写入
    public void update2DB(List<UserProfitEntity> profitEntityList) {

        for (UserProfitEntity p : profitEntityList) {
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
        }
    }

}
