package com.scd.batch.trade.model.loan;

import com.scd.batch.common.utils.ShortDate;

import java.util.Date;

public class ExpediteInfo {
    
    /**
     * 客户ID
     */
    private long customerId;
    
    /**
     * 产品ID
     */
    private long productId;

    /**
     * 应还金额
     */
    private long money;
   
    /**
     * 还款日期
     */
    private Date dueDate;
    
    /**
     * 催收T-N日
     */
    private int expediteDay;

    /**
     * 借据号
     * 2016-07-28需求添加
     */
    private long loanId;

    /**
     * 资金机构, 放款资金机构，如上海小贷、重庆小贷等
     * 2016-07-28需求添加
     * 为了满足自动还款
     */
    private long financingSource;
    
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getExpediteDay() {
        return expediteDay;
    }

    public void setExpediteDay(int expediteDay) {
        this.expediteDay = expediteDay;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public long getFinancingSource() {
        return financingSource;
    }

    public void setFinancingSource(long financingSource) {
        this.financingSource = financingSource;
    }

    @Override
    public String toString() {
        String spl = ",";
        StringBuilder builder = new StringBuilder();
        builder.append(customerId).append(spl)
                .append(spl)
                .append(spl)
                .append(spl)
                .append(money).append(spl)
                .append(spl)
                .append(productId).append(spl)
                .append(spl)
                .append(ShortDate.valueOf(dueDate)).append(spl)
                .append(spl)
                .append(expediteDay).append(spl)
                .append(loanId).append(spl)
                .append(financingSource);
        return builder.toString();
    }
    
    
}
