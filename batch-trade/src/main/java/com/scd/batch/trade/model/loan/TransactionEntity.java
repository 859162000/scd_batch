package com.scd.batch.trade.model.loan;

import com.scd.batch.common.entity.Entity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class TransactionEntity extends Entity {

    /**
     * 前置请求流水号, 由业务后台发起交易时生成并传入
     */
    private String requestId = "";

    /**
     * 子前置请求流水号, 用于区分合并还款时的多条子流水
     */
    private long subRequestId;

    /**
     * 核心流水ID, H_TRANSACTION -> TRANSACTION_ID
     */
    private long transactionId;

    /**
     * 关联流水ID, 关联交易的流水ID
     */
    private long relatedTransactionId;

    /**
     * 支付渠道, 客户支付的渠道，如百度钱包、银联等
     */
    private long paymentChannel;

    /**
     * 资金机构, 放款资金机构，如上海小贷、重庆小贷等
     */
    private long financingSource;

    /**
     * 交易金额
     */
    private long transactionAmount;

    /**
     * 交易发起日期
     */
    private Date transactionDate;

    /**
     * 交易码
     */
    private String transactionCode;

    /**
     * 借据号
     */
    private long loanId;

    /**
     * 余额交易ID
     */
    private long debitId;

    /**
     * 客户ID, 由用户中心产生，并由业务后台传入，全局唯一，对应于自然人
     */
    private long customerId;

    /**
     * 账号ID, T_CUSTOMER_SIGN_CONTRACT -> ACCOUNT_ID
     */
    private long accountId;

    /**
     * 产品ID, M_PRODUCT -> PRODUCT_ID，产品配置时生成，对应于度学金、糯米等级别的产品
     */
    private long productId;

    public String getRequestId() {

        return requestId;
    }

    public void setRequestId(String requestId) {

        this.requestId = requestId;
    }

    public long getSubRequestId() {

        return subRequestId;
    }

    public void setSubRequestId(long subRequestId) {

        this.subRequestId = subRequestId;
    }

    public long getTransactionId() {

        return transactionId;
    }

    public void setTransactionId(long transactionId) {

        this.transactionId = transactionId;
    }

    public long getRelatedTransactionId() {

        return relatedTransactionId;
    }

    public void setRelatedTransactionId(long relatedTransactionId) {

        this.relatedTransactionId = relatedTransactionId;
    }

    public long getPaymentChannel() {

        return paymentChannel;
    }

    public void setPaymentChannel(long paymentChannel) {

        this.paymentChannel = paymentChannel;
    }

    public long getFinancingSource() {

        return financingSource;
    }

    public void setFinancingSource(long financingSource) {

        this.financingSource = financingSource;
    }

    public long getTransactionAmount() {

        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {

        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {

        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {

        this.transactionDate = transactionDate;
    }

    public String getTransactionCode() {

        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {

        this.transactionCode = transactionCode;
    }

    public long getLoanId() {

        return loanId;
    }

    public void setLoanId(long loanId) {

        this.loanId = loanId;
    }

    public long getDebitId() {

        return debitId;
    }

    public void setDebitId(long debitId) {

        this.debitId = debitId;
    }

    public long getCustomerId() {

        return customerId;
    }

    public void setCustomerId(long customerId) {

        this.customerId = customerId;
        setPartitionId(customerId);
    }

    public long getAccountId() {

        return accountId;
    }

    public void setAccountId(long accountId) {

        this.accountId = accountId;
    }

    public long getProductId() {

        return productId;
    }

    public void setProductId(long productId) {

        this.productId = productId;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }
}