package com.scd.batch.interest.service;

import com.scd.batch.common.constant.bid.ProductType;
import com.scd.batch.common.constant.interest.InterestRateType;
import com.scd.batch.common.dao.acct.AcctUserAccumulateProfitDao;
import com.scd.batch.common.dao.acct.AcctUserDailyProfitDao;
import com.scd.batch.common.dao.acct.UserAccumulateProfitDao;
import com.scd.batch.common.dao.acct.UserDailyProfitDao;
import com.scd.batch.common.dao.bid.CreditRepayRealDao;
import com.scd.batch.common.dao.bid.CreditorRelationDao;
import com.scd.batch.common.entity.acct.UserAccumulativeProfitEntity;
import com.scd.batch.common.entity.acct.UserDailyProfitEntity;
import com.scd.batch.common.entity.bid.UserCreditroRelationEntity;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.interest.entity.UserProfitEntity;
import com.scd.batch.interest.strategy.InterestCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 秒钱宝，计算收益
 */
@Service
public class UserProfitCalculateService {

    @Autowired
    private CreditorRelationDao relationDao;

    @Autowired
    private UserDailyProfitDao profitDao;

    @Autowired
    private UserAccumulateProfitDao accumulateProfitDao;

    @Autowired
    private AcctUserDailyProfitDao acctUserDailyProfitDao;

    @Autowired
    private AcctUserAccumulateProfitDao acctUserAccumulateProfitDao;

    @Autowired
    private CreditRepayRealDao creditRepayRealDao;


    // 取数据库计算收益
    public List<UserProfitEntity> calculateProfit(TableSpec tableSpec, Date transDate, List<Long> batchIdList) {

        Date lastDate = ShortDate.valueOf(transDate).addDays(-1).toDate();
        List<UserCreditroRelationEntity> entityList = relationDao.getUserCreditorRelationList(tableSpec,
                batchIdList);

        List<UserProfitEntity> profitEntityList = new ArrayList<>();

        for (UserCreditroRelationEntity p : entityList) {

            // 每日活期计算收益
            if (p.getProductType() == ProductType.CURRENT.getCode()) {

                // 计息规则中的日期判断
                double intrest = InterestCalculator.dailyInterest(p.getRemainPrincipal(),
                        p.getInterestRate(),
                        InterestRateType.YEAR);

                // TODO 回购是否会付息，当日付息金额
                double repayInterest = 0.0;
                try {
                    repayInterest = creditRepayRealDao.repayInterestAmountByDay(tableSpec,
                            lastDate, p.getBuyerUid());
                } catch (Exception e) {

                }

                // 活期总收益 = 累计活期收益 + 当日活期收益 - 当日付息金额
                UserProfitEntity entity = new UserProfitEntity(
                        0,
                        p.getBuyerUid(),
                        lastDate,
                        new BigDecimal(0),
                        new BigDecimal(intrest),
                        new BigDecimal(0),
                        new BigDecimal(intrest - repayInterest)
                );
                profitEntityList.add(entity);
            }

        }

        return profitEntityList;
    }

    // 更新收益信息到数据库，不存在则先写入
    public void update2DB(List<UserProfitEntity> profitEntityList) {

        profitEntityList.forEach(p -> {

            UserDailyProfitEntity entity = new UserDailyProfitEntity(
                    0,
                    p.getUid(),
                    DateUtil.DateToString(p.getDate(), DateStyle.YYYYMMDD),
                    new BigDecimal(0),
                    p.getCurrentProfit()
            );

            // 累计收益
            UserAccumulativeProfitEntity accumulativeProfitEntity = new UserAccumulativeProfitEntity(
                    0,
                    p.getUid(),
                    new BigDecimal(0),
                    p.getCurrentInvestProfit()
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
