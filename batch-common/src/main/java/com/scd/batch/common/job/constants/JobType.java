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
    BidLoanScheduleJob(1001, "BidLoanScheduleJob", "灵活调度-批量放款"),

    /**
     * 批量赎回任务
     */
//    RedeemScheduleJob(1002),

    /**
     * 批量回购
     */
    BidBuyBackScheduleJob(1002, "BidBuyBackScheduleJob", "灵活调度-批量回购"),

    /**
     * 批量认购预跑
     */
    PreAutoBuyScheduleJob(1003, "PreAutoBuyScheduleJob", "灵活调度-批量认购预跑"),

    /**
     * 批量认购
     */
    AutoBuyScheduleJob(1004, "AutoBuyScheduleJob", "灵活调度-批量认购"),


    /**
     * 对账相关
     *
     */

    /**
     * 放款流水抓取任务
     */
    LoanCrawlerJob(2001, "LoanCrawlerJob", "对账-放款流水抓取任务"),

    /**
     * 放款对账
     */
    LoanCalculatorJob(2002, "LoanCalculatorJob", "对账-放款对账"),

    /**
     * 还款流水抓取任务
     */
    PaymentCrawlerJob(2003, "PaymentCrawlerJob", "对账-还款流水抓取任务"),

    /**
     * 还款对账
     */
    PaymentCalculatorJob(2004, "PaymentCalculatorJob", "对账-还款对账"),


    /**
     * 提现流水抓取任务
     */
    CashCrawlerJob(2005, "CashCrawlerJob", "对账-提现流水抓取任务"),

    /**
     * 提现流水对账任务
     */
    CashCalculatorJob(2006, "CashCalculatorJob", "对账-提现流水对账任务"),

    /**
     * 充值流水抓取任务
     */
    SaveCrawlerJob(2007, "SaveCrawlerJob", "对账-充值流水抓取任务"),

    /**
     * 充值流水对账任务
     */
    SaveCalculatorJob(2008, "SaveCalculatorJob", "对账-充值流水对账任务"),

    // 余额抓取任务
    HuiFuUserBalanceCrawlerJob(2009, "HuiFuUserBalanceCrawlerJob", "对账-余额抓取任务"),

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
    ProjectLimitCalculateJob(3001, "ProjectLimitCalculateJob", "统计-项目额度"),

    /**
     * 借款人还款计划
     */
    BorrowerRepayPlanStatCalculateJob(3002, "BorrowerRepayPlanStatCalculateJob", "统计-借款人还款计划"),

    /**
     * 充值提现统计
     */
    FundStatCalculateJob(3003, "FundStatCalculateJob", "统计-充值提现统计"),

    /**
     * 定期计划到期
     */
    FixPlanDueStatCalculateJob(3004, "FixPlanDueStatCalculateJob", "统计-定期计划到期"),

    /**
     * 定期项目到期
     */
    FixProjectDueStatCalculateJob(3005, "FixProjectDueStatCalculateJob", "统计-定期项目到期"),

    /**
     * 平台支出
     */
    ExpenditureCalculateJob(3006, "ExpenditureCalculateJob", "统计-平台支出"),

    /**
     * 项目资产统计
     */
    AssetsStatProjectCalculateJob(3007, "AssetsStatProjectCalculateJob", "统计-项目资产统计"),

    /**
     * 余额资产统计
     */
    AssetsStatBalanceCalculateJob(3008, "AssetsStatBalanceCalculateJob", "统计-余额资产统计"),


    /**
     * 计息
     */

    /**
     * 昨日总资产
     */
    LastDayAssetsCalculateJob(4001, "LastDayAssetsCalculateJob", "计息-昨日总资产"),

    /**
     * 活期收益统计
     */
    UserCurrentProfitCalculateJob(4002, "UserCurrentProfitCalculateJob", "计息-活期收益统计"),

    /**
     * 昨日收益统计
     */
    UserDailyProfitCalculateJob(4003, "UserDailyProfitCalculateJob", "计息-昨日收益统计"),

    /**
     * 其他调度
     *
     */

    /**
     * 更新银行卡限额任务
     */
    UpdateBankCardQuotaScheduleJob(5001, "UpdateBankCardQuotaScheduleJob", "其他-更新银行卡限额任务"),

    /**
     * 更新注册人数到redis
     */
    UpdateUserRegisterCountToRedisJob(5002, "UpdateUserRegisterCountToRedisJob", "其他-更新注册人数到redis");


    public final int type;

    public final String name;

    public final String desc;

    JobType(int type, String name, String desc) {

        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Integer getType() {
        return this.type;
    }
}
