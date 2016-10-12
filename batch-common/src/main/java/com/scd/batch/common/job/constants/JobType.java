package com.scd.batch.common.job.constants;


/**
 * @author Created by hanxiao01 on 16/4/8.
 */

import com.scd.batch.common.utils.EnumType;

/**
 * 任务类型.
 */
public enum JobType implements EnumType<JobType, Integer> {

    /**
     * 贷款Job类型.
     */
    LOAN(1),

    /**
     * 信用支付Job类型.
     */
    CREDIT(2),

    /**
     * 日切前/批跑
     */
    PRE_PROCESS(3),

    /**
     * 日切
     */
    SWITCH(4),

    /**
     * 预授权
     */
    PREAUTHORIZE(5),

    /**
     * 老核心对账文件
     */
    LOAN_OLD_RECONCILIATION(7),

    /**
     * 对账文件
     */
    LOAN_RECONCILIATION(8),

    /**
     * 催收
     */
    LOAN_EXPEDITE(9),

    /**
     * 代扣自动还款
     */
    LOAN_AUTO_REPAY(10),

    /**
     * 信用支付对账
     */
    CREDIT_RECONCILIATION(30),

    /**
     * 信用支付催收
     */
    CREDIT_EXPEDITE(31),
    
    /**
     * 账务任务：入账请求处理
     */
    ACCOUNTING(20),
    
    /**
     * 账务任务：交易-入账请求准备任务
     */
    ACCG_REQ_TRANSACTION(21),
    
    /**
     * 账务任务：批跑罚息-入账请求准备任务
     */
    ACCG_REQ_BATCH_PENALTY(22),
    
    /**
     * 账务任务：利息计提-入账请求准备任务
     */
    ACCG_REQ_PROVISION(23),

    /**
     * 计提
     */
    PROVISION(11),


    LOAN_ACCG_REQ(12);

    public final int type;

    JobType(int type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return this.type;
    }
}
