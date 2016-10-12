package com.scd.batch.trade.model.loan;

import com.google.common.base.Joiner;

public class AutoRepayInfo {
    /**
     * 期数
     */
    private int scheduleNo;

    /**
     * 客户ID
     */
    private long customerId;

    /**
     * 借据ID
     */
    private long loanId;

    /**
     * 应还金额(分)
     */
    private long amount;

    /**
     * 产品ID
     */
    private long productId;

    /**
     * 是否有逾期的
     */
    private boolean overdue;

    public int getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(int scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        return Joiner.on("\t,").join(new Object[] {scheduleNo, customerId, loanId, amount, productId});
    }

    public static void main(String[] args) {
        System.out.println(new AutoRepayInfo());
    }
}
