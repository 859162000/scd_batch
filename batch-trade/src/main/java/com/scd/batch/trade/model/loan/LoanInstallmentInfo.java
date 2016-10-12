package com.scd.batch.trade.model.loan;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoanInstallmentInfo {

    /* 贷款分期相关属性 ***************/

    private long id;

    /**
     * 借据号
     */
    private long loanId;

    /**
     * 客户ID
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
     * 借据期序号
     */
    private int scheduleNo;

    /**
     * 分期还款计划属于某借据被分期的序号
     * 如, 一借据第一次分期，序号为 1, 第二次展期, 则分期还款计划的序号变为 2
     */
    private int installmentMadeNo;

    /**
     * 账务日期
     */
    private Date accountDate;

    /**
     * 到期日
     */

    private Date dueDate;
    /**
     * 宽限日
     */
    private Date graceDate;

    /**
     * 分期状态
     */
    private int status;

    /**
     * 本金总额
     * 计算息费依据
     */
    private long principal;

    /**
     * 已还本金
     */
    private long principalRepay;

    /**
     * 利息总额
     */
    private long interest;

    /**
     * 利息已还金额
     */
    private long interestRepay;

    /**
     * 费用总额
     */
    private long charges;

    /**
     * 费用已还金额
     */
    private long chargesRepay;


    /**
     * 违约金总额
     */
    private long violate;

    /**
     * 违约金已还
     */
    private long violateRepay;

    /**
     * 罚息总额
     * 由批跑生成
     */
    private long penalty;

    /**
     * 罚息已还
     */
    private long penaltyRepay;

    /**
     * 滞纳金总额
     * 由批跑生成
     */
    private long overdue;

    /**
     * 滞纳金已还
     */
    private long overdueRepay;

    /**
     * 管理费
     */
    private long management;

    /**
     * 管理费已还
     */
    private long managementRepay;

    /**
     * 服务费
     */
    private long service;

    /**
     * 服务费已还
     */
    private long serviceRepay;

    /* 贷款分期所属借据相关属性 ***************/

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


    /* 借据对应签约表相关属性 ***************/

    /**
     * 定价因子：罚息, 值为实际因子系数的10000倍，比如实际的因子为0.75，则这里的值为7500，默认为10000
     */
    private int mPenalty;

    /**
     * 定价因子：逾期费, 值为实际因子系数的10000倍，比如实际的因子为0.75，则这里的值为7500，默认为10000
     */
    private int mOverdue;


    /* 计算结果 ***************/

    /**
     * 罚息增加额
     */
    private long penaltyDelta;

    /**
     * 滞纳金/逾期费增加额
     */
    private long overdueDelta;

    /**
     * 借据表 VERSION
     */
    private long version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

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

    public int getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(int scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public int getInstallmentMadeNo() {
        return installmentMadeNo;
    }

    public void setInstallmentMadeNo(int installmentMadeNo) {
        this.installmentMadeNo = installmentMadeNo;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getGraceDate() {
        return graceDate;
    }

    public void setGraceDate(Date graceDate) {
        this.graceDate = graceDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getPrincipal() {
        return principal;
    }

    public void setPrincipal(long principal) {
        this.principal = principal;
    }

    public long getPrincipalRepay() {
        return principalRepay;
    }

    public void setPrincipalRepay(long principalRepay) {
        this.principalRepay = principalRepay;
    }

    public long getInterest() {
        return interest;
    }

    public void setInterest(long interest) {
        this.interest = interest;
    }

    public long getInterestRepay() {
        return interestRepay;
    }

    public void setInterestRepay(long interestRepay) {
        this.interestRepay = interestRepay;
    }

    public long getCharges() {
        return charges;
    }

    public void setCharges(long charges) {
        this.charges = charges;
    }

    public long getChargesRepay() {
        return chargesRepay;
    }

    public void setChargesRepay(long chargesRepay) {
        this.chargesRepay = chargesRepay;
    }

    public long getViolate() {
        return violate;
    }

    public void setViolate(long violate) {
        this.violate = violate;
    }

    public long getViolateRepay() {
        return violateRepay;
    }

    public void setViolateRepay(long violateRepay) {
        this.violateRepay = violateRepay;
    }

    public long getPenalty() {
        return penalty;
    }

    public void setPenalty(long penalty) {
        this.penalty = penalty;
    }

    public long getPenaltyRepay() {
        return penaltyRepay;
    }

    public void setPenaltyRepay(long penaltyRepay) {
        this.penaltyRepay = penaltyRepay;
    }

    public long getOverdue() {
        return overdue;
    }

    public void setOverdue(long overdue) {
        this.overdue = overdue;
    }

    public long getOverdueRepay() {
        return overdueRepay;
    }

    public void setOverdueRepay(long overdueRepay) {
        this.overdueRepay = overdueRepay;
    }

    public int getLoanAge() {
        return loanAge;
    }

    public void setLoanAge(int loanAge) {
        this.loanAge = loanAge;
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

    public int getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(int loanStatus) {
        this.loanStatus = loanStatus;
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

    public long getManagement() {
        return management;
    }

    public void setManagement(long management) {
        this.management = management;
    }

    public long getManagementRepay() {
        return managementRepay;
    }

    public void setManagementRepay(long managementRepay) {
        this.managementRepay = managementRepay;
    }

    public long getService() {
        return service;
    }

    public void setService(long service) {
        this.service = service;
    }

    public long getServiceRepay() {
        return serviceRepay;
    }

    public void setServiceRepay(long serviceRepay) {
        this.serviceRepay = serviceRepay;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public LoanInfo transform2LoanInfo() {
        LoanInfo loanInfo = new LoanInfo();

        loanInfo.setLoanId(this.getLoanId());
        loanInfo.setCustomerId(this.getCustomerId());
        loanInfo.setAccountId(this.getAccountId());
        loanInfo.setLoanAge(this.getLoanAge());
        loanInfo.setLoanPenalty(this.getLoanPenalty());
        loanInfo.setLoanOverdue(this.getLoanOverdue());
        loanInfo.setLoanStatus(this.getLoanStatus());
        loanInfo.setmOverdue(this.getmOverdue());
        loanInfo.setmPenalty(this.getmPenalty());

        loanInfo.setProductId(this.getProductId());
        loanInfo.setProductNo(this.getProductNo());

        loanInfo.setVersion(this.getVersion());

        loanInfo.setFinancialSource(this.getFinancialSource());
        loanInfo.setLoanChannel(this.getLoanChannel());
        loanInfo.setSecurityStatus(this.getSecurityStatus());

        return loanInfo;
    }

    public InstallmentInfo transform2InstallmentInfo() {
        InstallmentInfo installmentInfo = new InstallmentInfo();

        installmentInfo.setLoanId(this.getLoanId());
        installmentInfo.setCustomerId(this.getCustomerId());
        installmentInfo.setAccountId(this.getAccountId());

        installmentInfo.setScheduleNo(this.getScheduleNo());
        installmentInfo.setInstallmentMadeNo(this.getInstallmentMadeNo());

        installmentInfo.setDueDate(this.getDueDate());
        installmentInfo.setGraceDate(this.getGraceDate());

        installmentInfo.setStatus(this.getStatus());

        installmentInfo.setPrincipal(this.getPrincipal());
        installmentInfo.setPrincipalRepay(this.getPrincipalRepay());

        installmentInfo.setInterest(this.getInterest());
        installmentInfo.setInterestRepay(this.getInterestRepay());

        installmentInfo.setCharges(this.getCharges());
        installmentInfo.setChargesRepay(this.getChargesRepay());

        installmentInfo.setViolate(this.getViolate());
        installmentInfo.setViolateRepay(this.getViolateRepay());

        installmentInfo.setPenalty(this.getPenalty());
        installmentInfo.setPenaltyRepay(this.getPenaltyRepay());

        installmentInfo.setOverdue(this.getOverdue());
        installmentInfo.setOverdueRepay(this.getOverdueRepay());

        installmentInfo.setManagement(this.getManagement());
        installmentInfo.setManagementRepay(this.getManagementRepay());

        installmentInfo.setService(this.getService());
        installmentInfo.setServiceRepay(this.getServiceRepay());

        return installmentInfo;

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
