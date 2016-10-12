package com.scd.batch.trade.model.loan;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"flag"})
public class LoanInfo {

    /**
     * 内部使用
     */
    private byte flag;

    /**
     * 借据 ID
     */
    private long loanId;

    /**
     * 客户 ID
     */
    private long customerId;

    /**
     * 账号ID
     */
    private long accountId;

    /**
     * 产品ID
     */
    private long productId;

    /**
     * 产品序号
     */
    private long productNo;

    /**
     * 定价因子：罚息, 值为实际因子系数的10000倍，比如实际的因子为0.75，则这里的值为7500，默认为10000
     */
    private int mPenalty;

    /**
     * 定价因子：逾期费, 值为实际因子系数的10000倍，比如实际的因子为0.75，则这里的值为7500，默认为10000
     */
    private int mOverdue;

    /**
     * 借据账龄
     */
    private int loanAge;

    /**
     * 借据状态
     */
    private int loanStatus;

    /**
     * 借据罚息总额
     */
    private long loanPenalty;

    /**
     * 借据滞纳金总额
     */
    private long loanOverdue;

    /**
     * 资金机构
     */
    private int financialSource;

    /**
     * 放款渠道
     */
    private int loanChannel;

    /**
     * 证券化状态
     */
    private int securityStatus;

    /**
     * version, 更新借据时使用
     */
    private long version;


    /**
     * 借据下分期
     */
    private List<InstallmentInfo> installments;


    /* 分期汇总之后的值 *******************/

    /**
     * 账龄
     */
    private int age;

    /**
     * 罚息增加额
     */
    private long penaltyDelta;

    /**
     * 滞纳金/逾期费增加额
     */
    private long overdueDelta;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPenaltyDelta() {
        return penaltyDelta;
    }

    public void setPenaltyDelta(long penaltyDelta) {
        this.penaltyDelta = penaltyDelta;
    }

    public long getOverdueDelta() {
        return overdueDelta;
    }

    public void setOverdueDelta(long overdueDelta) {
        this.overdueDelta = overdueDelta;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public int getmPenalty() {
        return mPenalty;
    }

    public void setmPenalty(int mPenalty) {
        this.mPenalty = mPenalty;
    }

    public int getmOverdue() {
        return mOverdue;
    }

    public void setmOverdue(int mOverdue) {
        this.mOverdue = mOverdue;
    }

    public int getLoanAge() {
        return loanAge;
    }

    public void setLoanAge(int loanAge) {
        this.loanAge = loanAge;
    }

    public int getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(int loanStatus) {
        this.loanStatus = loanStatus;
    }

    public long getLoanPenalty() {
        return loanPenalty;
    }

    public void setLoanPenalty(long loanPenalty) {
        this.loanPenalty = loanPenalty;
    }

    public long getLoanOverdue() {
        return loanOverdue;
    }

    public void setLoanOverdue(long loanOverdue) {
        this.loanOverdue = loanOverdue;
    }

    public int getFinancialSource() {
        return financialSource;
    }

    public void setFinancialSource(int financialSource) {
        this.financialSource = financialSource;
    }

    public int getLoanChannel() {
        return loanChannel;
    }

    public void setLoanChannel(int loanChannel) {
        this.loanChannel = loanChannel;
    }

    public int getSecurityStatus() {
        return securityStatus;
    }

    public void setSecurityStatus(int securityStatus) {
        this.securityStatus = securityStatus;
    }

    public List<InstallmentInfo> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentInfo> installments) {
        this.installments = installments;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductNo() {
        return productNo;
    }

    public void setProductNo(long productNo) {
        this.productNo = productNo;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
