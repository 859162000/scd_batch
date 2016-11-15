package com.scd.batch.interest.strategy;

import com.scd.batch.common.constant.interest.InterestRateType;

/**
 * 利率计算策略
 */
public class InterestCalculator {

    public static int YEAR_DAYS = 365;
    public static int MONTH_DAYS = 30;

    // 根据利率计算利息策略
    public static double dailyInterest(double principal, double rate, InterestRateType rateType) {

        if (rateType == InterestRateType.YEAR) {

            return principal * rate / (100 * YEAR_DAYS);
        } else if (rateType == InterestRateType.MONTH) {

            return principal * rate / (100 * MONTH_DAYS);
        } else if (rateType == InterestRateType.DAY) {

            return principal * rate / 100;
        } else {

            throw new RuntimeException("利率类型非法!");
        }
    }
}
