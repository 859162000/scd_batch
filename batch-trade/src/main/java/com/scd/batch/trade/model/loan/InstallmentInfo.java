package com.scd.batch.trade.model.loan;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class InstallmentInfo {

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
     * 产品 ID
     */
    private long productId;

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
     * 到期日
     */
    private Date dueDate;

    /**
     * 宽限日
     */
    private Date graceDate;

    /**
     * 分期状态, 1-正常, 2-已转分期
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

    /**
     * 罚息增加额
     */
    private long penaltyDelta;

    /**
     * 滞纳金/逾期费增加额
     */
    private long overdueDelta;

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

    public long getService() {
        return service;
    }

    public void setService(long service) {
        this.service = service;
    }

    public long getManagementRepay() {
        return managementRepay;
    }

    public void setManagementRepay(long managementRepay) {
        this.managementRepay = managementRepay;
    }

    public long getServiceRepay() {
        return serviceRepay;
    }

    public void setServiceRepay(long serviceRepay) {
        this.serviceRepay = serviceRepay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
