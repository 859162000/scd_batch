package com.scd.batch.common.job.constants;


import com.scd.batch.common.utils.EnumType;

/**
 * 任务类型.
 */
public enum JobType implements EnumType<JobType, Integer> {


    /**
     * 灵活调度批跑
     */

    /**
     * 批量放款
     */
    BidLoanScheduleJob(1001),

    /**
     * 批量赎回任务
     */
    RedeemScheduleJob(1002),

    /**
     * 批量回购
     */
    BidBuyBackScheduleJob(1003),

    /**
     * 批量认购预跑
     */
    PreAutoBuyScheduleJob(1004),

    /**
     * 批量认购
     */
    AutoBuyScheduleJob(1005),

    /**
     * 更新银行卡限额任务
     */
    UpdateBankCardQuotaScheduleJob(1006),

    /**
     * 更新注册人数到redis
     */
    UpdateUserRegisterCountToRedisJob(1007),


    /**
     * 对账相关
     *
     */

    /**
     * 放款流水抓取任务
     */
    LoanCrawlerJob(2001),

    /**
     * 放款对账
     */
    LoanCalculatorJob(2002),

    /**
     * 还款流水抓取任务
     */
    PaymentCrawlerJob(2003),

    /**
     * 还款对账
     */
    PaymentCalculatorJob(2004),


    /**
     * 提现流水抓取任务
     */
    CashCrawlerJob(2005),

    /**
     * 提现流水对账任务
     */
    CashCalculatorJob(2006),

    /**
     * 充值流水抓取任务
     */
    SaveCrawlerJob(2007),

    /**
     * 充值流水对账任务
     */
    SaveCalculatorJob(2008),

    // 余额抓取任务
    HuiFuUserBalanceCrawlerJob(2009),

    /**
     * 商户扣款流水抓取任务
     */
    //TrfCrawlerJob(1009),

    /**
     * 商户扣款流水对账任务
     */
    //TrfCalculatorJob(1010),


    /**
     * 统计分析
     *
     */

    /**
     * 项目额度
     */
    ProjectLimitCalculateJob(3001),

    /**
     * 借款人还款计划
     */
    BorrowerRepayPlanStatCalculateJob(3002),

    /**
     * 充值提现统计
     */
    FundStatCalculateJob(3003),

    /**
     * 定期计划
     */
    FixPlanDueStatCalculateJob(3004),

    /**
     * 定期项目
     */
    FixProjectDueStatCalculateJob(3005),

    /**
     * 平台支出
     */
    ExpenditureCalculateJob(3006),

    /**
     * 项目资产统计
     */
    AssetsStatProjectCalculateJob(3007),

    /**
     * 余额资产统计
     */
    AssetsStatBalanceCalculateJob(3008),


    /**
     * 计息
     */

    /**
     * 昨日总资产
     */
    LastDayAssetsCalculateJob(4001),
    /**
     * 活期收益统计
     */
    UserCurrentProfitCalculateJob(4002),
    /**
     * 昨日收益统计
     */
    UserDailyProfitCalculateJob(4003);


    public final int type;

    JobType(int type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return this.type;
    }
}
