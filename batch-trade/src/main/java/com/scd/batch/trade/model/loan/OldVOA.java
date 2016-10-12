package com.scd.batch.trade.model.loan;

import com.scd.batch.common.utils.ShortDate;

import java.util.Date;

public class OldVOA {

    /**
     * 为了查询不输出至文件
     */
    private long transactionId;

    /**
     * 账号ID
     */
    private long accountId;

    /**
     * 前置请求流水号
     */
    private String requestId;

    /**
     * 交易发起日期
     */
    private Date transactionDate;

    /**
     * 交易金额
     */
    private long transactionAmount;

    /**
     * 交易码
     */
    private String transactionCode;

    /**
     * 产品ID
     */
    private long productId;

    /**
     * 产品名称（默认为空）
     */
    private String productName;

    /**
     * 项目ID（默认为空）
     */
    private String projectId;

    /**
     * 项目名称（默认为空）
     */
    private String  projectName;

    /**
     * 冲销本金
     */
    private long principal;

    /**
     * 冲销费用
     */
    private long charges;

    /**
     * 冲销罚息
     */
    private long penalty;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getPrincipal() {
        return principal;
    }

    public void setPrincipal(long principal) {
        this.principal = principal;
    }

    public long getCharges() {
        return charges;
    }

    public void setCharges(long charges) {
        this.charges = charges;
    }

    public long getPenalty() {
        return penalty;
    }

    public void setPenalty(long penalty) {
        this.penalty = penalty;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        String spl = ",";
        String tab = "\t";
        StringBuilder builder = new StringBuilder();
        builder.append(accountId).append(tab).append(spl)
                .append(requestId).append(tab).append(spl)
                .append(ShortDate.valueOf(transactionDate).getIntValue()).append(tab).append(spl)
                .append(transactionAmount).append(tab).append(spl)
                .append(transactionCode).append(tab).append(spl)
                .append(productId).append(tab).append(spl)
                .append(productName).append(tab).append(spl)
                .append(projectId).append(tab).append(spl)
                .append(projectName).append(tab).append(spl)
                .append(principal).append(tab).append(spl)
                .append(charges).append(tab).append(spl)
                .append(penalty);

        return builder.toString();
    }
}
