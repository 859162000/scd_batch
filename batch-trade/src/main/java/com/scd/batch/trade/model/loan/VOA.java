package com.scd.batch.trade.model.loan;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.common.LoanUtil;

import java.util.Date;

public class VOA {

    /**
     * 交易日期 业务发生日期
     * yyyyMMdd
     */
    private Date transactionDate;

    /**
     * 交易时间
     * hh:mm:ss
     */
    private Date transactionTime;

    /**
     * 产品id
     */
    private long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 交易类型
     *
     */
    private String transactionType;

    /**
     * 前置请求流水号, 由业务后台发起交易时生成并传入
     */
    private String requestId;

    /**
     * 子前置请求流水号, 用于区分合并还款时的多条子流水
     * 单条流水时使用默认值0
     */
    private long subRequestId;

    /**
     * 核心流水ID, H_TRANSACTION -> TRANSACTION_ID
     */
    private long transactionId;

    /**
     * 借据号
     */
    private long loanId;

    /**
     * 余额交易ID
     */
    private long debitId;

    /**
     * 交易金额
     */
    private long transactionAmount;

    /**
     * 资金机构, 放款资金机构，如上海小贷、重庆小贷等
     * 贷款实体
     */
    private long financingSource;

    /**
     * 交易状态
     * T_ACCG_REQ_LIST 入账状态
     * 初始状态(0),入账处理成功(1),入账处理失败(2)
     */
    private int state;

    /**
     * 明细序号
     * 借据分期序号
     */
    private int loanScheduleNo;

    /**
     * 信用消费分期序号
     */
    private int consumeNo;

    /**
     * 信用账单分期序号
     */
    private int billNo;

    /**
     * 本次交易本金金额
     */
    private long principal;

    /**
     * 本次交易本金减免
     */
    private long principalReduce;

    /**
     * 本次交易本金调整
     */
    private long principalAdjust;

    /**
     * 本次交易利息金额
     */
    private long interest;

    /**
     * 本次交易利息减免
     */
    private long interestReduce;

    /**
     * 本次交易利息调整
     */
    private long interestAdjust;

    /**
     * 本次交易费用金额
     */
    private long charges;

    /**
     * 本次交易费用减免
     */
    private long chargesReduce;

    /**
     * 本次交易费用调整
     */
    private long chargesAdjust;

    /**
     * 本次交易罚息金额
     */
    private long penalty;

    /**
     * 本次交易罚息减免
     */
    private long penaltyReduce;

    /**
     * 本次交易罚息调整
     */
    private long penaltyAdjust;

    /**
     * 本次交易逾期费
     */
    private long overdue;

    /**
     * 本次交易逾期费减免
     */
    private long overdueReduce;

    /**
     * 本次交易逾期费调整
     */
    private long overdueAdjust;

    /**
     * 本次交易违约金
     */
    private long violate;

    /**
     * 本次交易违约金减免
     */
    private long violateReduce;

    /**
     * 本次交易违约金调整
     */
    private long violateAdjust;

    /**
     * 本次交易贴息金额
     */
    private long discount;

    /**
     * 本次交易贴息减免
     */
    private long discountReduce;

    /**
     * 本次交易贴息调整
     */
    private long discountAdjust;

    /**
     * 账号管理费
     */
    private long management;

    private long managementReduce;

    private long managementAdjust;

    /**
     * 服务费
     */
    private long service;

    private long serviceReduce;

    private long serviceAdjust;

    /**
     * 预留费用
     */
    private long fee;

    private long feeReduce;

    private long feeAdjust;

    /**
     * 会费预留
     */
    private long dues;

    private long duesReduce;

    private long duesAdjust;

    /**
     * 运输费用预留
     */
    private long expense;

    private long expenseReduce;

    private long expenseAdjust;

    /**
     * 成本费用预留
     */
    private long cost;

    private long costReduce;

    private long costAdjust;

    /**
     * 本次交易溢交款金额
     */
    private long overFlow;

    /**
     * H_LOAN_OPERATION_VALUE
     */

    /**
     * 退贴息金额
     */
    private long discountRefund;

    /**
     * 应还本金
     */
    private long totalPrincipal;

    /**
     * 已还本金
     */
    private long repaidPrincipal;

    /**
     * 应还利息
     */
    private long totalInterest;

    /**
     * 已还利息
     */
    private long repaidInterest;

    /**
     * 应还费用
     */
    private long totalCharges;

    /**
     * 已还费用
     */
    private long repaidCharges;

    /**
     * 应还罚息
     */
    private long totalPenalty;

    /**
     * 已还罚息
     */
    private long repaidPenalty;

    /**
     * 应还滞纳金
     */
    private long totalOverdue;

    /**
     * 已还滞纳金
     */
    private long repaidOverdue;

    /**
     * 应还违约金
     */
    private long totalViolate;

    /**
     * 已还违约金
     */
    private long repaidViolate;

    /**
     * 应还账户管理费
     */
    private long totalManagement;

    /**
     * 已还账户管理费
     */
    private long repaidManagement;

    /**
     * 应还服务费
     */
    private long totalService;

    /**
     * 已还服务费
     */
    private long repaidService;

    public Date getTransactionDate() {

        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {

        this.transactionDate = transactionDate;
    }

    public Date getTransactionTime() {

        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {

        this.transactionTime = transactionTime;
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

    public String getTransactionType() {

        return transactionType;
    }

    public void setTransactionType(String transactionType) {

        this.transactionType = transactionType;
    }

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

    public long getTransactionAmount() {

        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {

        this.transactionAmount = transactionAmount;
    }

    public long getFinancingSource() {

        return financingSource;
    }

    public void setFinancingSource(long financingSource) {

        this.financingSource = financingSource;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getLoanScheduleNo() {

        return loanScheduleNo;
    }

    public void setLoanScheduleNo(int loanScheduleNo) {

        this.loanScheduleNo = loanScheduleNo;
    }

    public int getConsumeNo() {

        return consumeNo;
    }

    public void setConsumeNo(int consumeNo) {

        this.consumeNo = consumeNo;
    }

    public int getBillNo() {

        return billNo;
    }

    public void setBillNo(int billNo) {

        this.billNo = billNo;
    }

    public long getPrincipal() {

        return principal;
    }

    public void setPrincipal(long principal) {

        this.principal = principal;
    }

    public long getPrincipalReduce() {

        return principalReduce;
    }

    public void setPrincipalReduce(long principalReduce) {

        this.principalReduce = principalReduce;
    }

    public long getPrincipalAdjust() {

        return principalAdjust;
    }

    public void setPrincipalAdjust(long principalAdjust) {

        this.principalAdjust = principalAdjust;
    }

    public long getInterest() {

        return interest;
    }

    public void setInterest(long interest) {

        this.interest = interest;
    }

    public long getInterestReduce() {

        return interestReduce;
    }

    public void setInterestReduce(long interestReduce) {

        this.interestReduce = interestReduce;
    }

    public long getInterestAdjust() {

        return interestAdjust;
    }

    public void setInterestAdjust(long interestAdjust) {

        this.interestAdjust = interestAdjust;
    }

    public long getCharges() {

        return charges;
    }

    public void setCharges(long charges) {

        this.charges = charges;
    }

    public long getChargesReduce() {

        return chargesReduce;
    }

    public void setChargesReduce(long chargesReduce) {

        this.chargesReduce = chargesReduce;
    }

    public long getChargesAdjust() {

        return chargesAdjust;
    }

    public void setChargesAdjust(long chargesAdjust) {

        this.chargesAdjust = chargesAdjust;
    }

    public long getPenalty() {

        return penalty;
    }

    public void setPenalty(long penalty) {

        this.penalty = penalty;
    }

    public long getPenaltyReduce() {

        return penaltyReduce;
    }

    public void setPenaltyReduce(long penaltyReduce) {

        this.penaltyReduce = penaltyReduce;
    }

    public long getPenaltyAdjust() {

        return penaltyAdjust;
    }

    public void setPenaltyAdjust(long penaltyAdjust) {

        this.penaltyAdjust = penaltyAdjust;
    }

    public long getOverdue() {

        return overdue;
    }

    public void setOverdue(long overdue) {

        this.overdue = overdue;
    }

    public long getOverdueReduce() {

        return overdueReduce;
    }

    public void setOverdueReduce(long overdueReduce) {

        this.overdueReduce = overdueReduce;
    }

    public long getOverdueAdjust() {

        return overdueAdjust;
    }

    public void setOverdueAdjust(long overdueAdjust) {

        this.overdueAdjust = overdueAdjust;
    }

    public long getViolate() {

        return violate;
    }

    public void setViolate(long violate) {

        this.violate = violate;
    }

    public long getViolateReduce() {

        return violateReduce;
    }

    public void setViolateReduce(long violateReduce) {

        this.violateReduce = violateReduce;
    }

    public long getViolateAdjust() {

        return violateAdjust;
    }

    public void setViolateAdjust(long violateAdjust) {

        this.violateAdjust = violateAdjust;
    }

    public long getDiscount() {

        return discount;
    }

    public void setDiscount(long discount) {

        this.discount = discount;
    }

    public long getDiscountReduce() {

        return discountReduce;
    }

    public void setDiscountReduce(long discountReduce) {

        this.discountReduce = discountReduce;
    }

    public long getDiscountAdjust() {

        return discountAdjust;
    }

    public void setDiscountAdjust(long discountAdjust) {

        this.discountAdjust = discountAdjust;
    }

    public long getManagement() {

        return management;
    }

    public void setManagement(long management) {

        this.management = management;
    }

    public long getManagementReduce() {

        return managementReduce;
    }

    public void setManagementReduce(long managementReduce) {

        this.managementReduce = managementReduce;
    }

    public long getManagementAdjust() {

        return managementAdjust;
    }

    public void setManagementAdjust(long managementAdjust) {

        this.managementAdjust = managementAdjust;
    }

    public long getService() {

        return service;
    }

    public void setService(long service) {

        this.service = service;
    }

    public long getServiceReduce() {

        return serviceReduce;
    }

    public void setServiceReduce(long serviceReduce) {

        this.serviceReduce = serviceReduce;
    }

    public long getServiceAdjust() {

        return serviceAdjust;
    }

    public void setServiceAdjust(long serviceAdjust) {

        this.serviceAdjust = serviceAdjust;
    }

    public long getFee() {

        return fee;
    }

    public void setFee(long fee) {

        this.fee = fee;
    }

    public long getFeeReduce() {

        return feeReduce;
    }

    public void setFeeReduce(long feeReduce) {

        this.feeReduce = feeReduce;
    }

    public long getFeeAdjust() {

        return feeAdjust;
    }

    public void setFeeAdjust(long feeAdjust) {

        this.feeAdjust = feeAdjust;
    }

    public long getDues() {

        return dues;
    }

    public void setDues(long dues) {

        this.dues = dues;
    }

    public long getDuesReduce() {

        return duesReduce;
    }

    public void setDuesReduce(long duesReduce) {

        this.duesReduce = duesReduce;
    }

    public long getDuesAdjust() {

        return duesAdjust;
    }

    public void setDuesAdjust(long duesAdjust) {

        this.duesAdjust = duesAdjust;
    }

    public long getExpense() {

        return expense;
    }

    public void setExpense(long expense) {

        this.expense = expense;
    }

    public long getExpenseReduce() {

        return expenseReduce;
    }

    public void setExpenseReduce(long expenseReduce) {

        this.expenseReduce = expenseReduce;
    }

    public long getExpenseAdjust() {

        return expenseAdjust;
    }

    public void setExpenseAdjust(long expenseAdjust) {

        this.expenseAdjust = expenseAdjust;
    }

    public long getCost() {

        return cost;
    }

    public void setCost(long cost) {

        this.cost = cost;
    }

    public long getCostReduce() {

        return costReduce;
    }

    public void setCostReduce(long costReduce) {

        this.costReduce = costReduce;
    }

    public long getCostAdjust() {

        return costAdjust;
    }

    public void setCostAdjust(long costAdjust) {

        this.costAdjust = costAdjust;
    }

    public long getOverFlow() {

        return overFlow;
    }

    public void setOverFlow(long overFlow) {

        this.overFlow = overFlow;
    }

    public long getDiscountRefund() {

        return discountRefund;
    }

    public void setDiscountRefund(long discountRefund) {

        this.discountRefund = discountRefund;
    }

    public long getTotalPrincipal() {

        return totalPrincipal;
    }

    public void setTotalPrincipal(long totalPrincipal) {

        this.totalPrincipal = totalPrincipal;
    }

    public long getRepaidPrincipal() {

        return repaidPrincipal;
    }

    public void setRepaidPrincipal(long repaidPrincipal) {

        this.repaidPrincipal = repaidPrincipal;
    }

    public long getTotalInterest() {

        return totalInterest;
    }

    public void setTotalInterest(long totalInterest) {

        this.totalInterest = totalInterest;
    }

    public long getRepaidInterest() {

        return repaidInterest;
    }

    public void setRepaidInterest(long repaidInterest) {

        this.repaidInterest = repaidInterest;
    }

    public long getTotalCharges() {

        return totalCharges;
    }

    public void setTotalCharges(long totalCharges) {

        this.totalCharges = totalCharges;
    }

    public long getRepaidCharges() {

        return repaidCharges;
    }

    public void setRepaidCharges(long repaidCharges) {

        this.repaidCharges = repaidCharges;
    }

    public long getTotalPenalty() {

        return totalPenalty;
    }

    public void setTotalPenalty(long totalPenalty) {

        this.totalPenalty = totalPenalty;
    }

    public long getRepaidPenalty() {

        return repaidPenalty;
    }

    public void setRepaidPenalty(long repaidPenalty) {

        this.repaidPenalty = repaidPenalty;
    }

    public long getTotalOverdue() {

        return totalOverdue;
    }

    public void setTotalOverdue(long totalOverdue) {

        this.totalOverdue = totalOverdue;
    }

    public long getRepaidOverdue() {

        return repaidOverdue;
    }

    public void setRepaidOverdue(long repaidOverdue) {

        this.repaidOverdue = repaidOverdue;
    }

    public long getTotalViolate() {

        return totalViolate;
    }

    public void setTotalViolate(long totalViolate) {

        this.totalViolate = totalViolate;
    }

    public long getRepaidViolate() {

        return repaidViolate;
    }

    public void setRepaidViolate(long repaidViolate) {

        this.repaidViolate = repaidViolate;
    }

    public long getTotalManagement() {

        return totalManagement;
    }

    public void setTotalManagement(long totalManagement) {

        this.totalManagement = totalManagement;
    }

    public long getRepaidManagement() {

        return repaidManagement;
    }

    public void setRepaidManagement(long repaidManagement) {

        this.repaidManagement = repaidManagement;
    }

    public long getTotalService() {

        return totalService;
    }

    public void setTotalService(long totalService) {

        this.totalService = totalService;
    }

    public long getRepaidService() {

        return repaidService;
    }

    public void setRepaidService(long repaidService) {

        this.repaidService = repaidService;
    }

    @Override
    public String toString() {

        String spl = ",";
        StringBuilder builder = new StringBuilder();
        builder.append(ShortDate.valueOf(transactionDate).getIntValue()).append(spl)
                .append(LoanUtil.formatByDateTimePattern(transactionTime)).append(spl)
                .append(productId).append(spl)
                .append(productName).append(spl)
                .append(transactionType).append(spl)
                .append(state).append(spl)
                .append(requestId).append(spl)
                .append(subRequestId).append(spl)
                .append(transactionId).append(spl)
                .append(loanId).append(spl)
                .append(loanScheduleNo).append(spl)
                .append(consumeNo).append(spl)
                .append(billNo).append(spl)
                .append(debitId).append(spl)
                .append(transactionAmount).append(spl)
                .append(financingSource).append(spl)
                .append(principal).append(spl)
                .append(principalReduce).append(spl)
                .append(principalAdjust).append(spl)
                .append(interest).append(spl)
                .append(interestReduce).append(spl)
                .append(interestAdjust).append(spl)
                .append(charges).append(spl)
                .append(chargesReduce).append(spl)
                .append(chargesAdjust).append(spl)
                .append(penalty).append(spl)
                .append(penaltyReduce).append(spl)
                .append(penaltyAdjust).append(spl)
                .append(overdue).append(spl)
                .append(overdueReduce).append(spl)
                .append(overdueAdjust).append(spl)
                .append(violate).append(spl)
                .append(violateReduce).append(spl)
                .append(violateAdjust).append(spl)
                .append(discount).append(spl)
                .append(discountReduce).append(spl)
                .append(discountAdjust).append(spl)
                .append(management).append(spl)
                .append(managementReduce).append(spl)
                .append(managementAdjust).append(spl)
                .append(service).append(spl)
                .append(serviceReduce).append(spl)
                .append(serviceAdjust).append(spl)
                .append(fee).append(spl)
                .append(feeReduce).append(spl)
                .append(feeAdjust).append(spl)
                .append(dues).append(spl)
                .append(duesReduce).append(spl)
                .append(duesAdjust).append(spl)
                .append(expense).append(spl)
                .append(expenseReduce).append(spl)
                .append(expenseAdjust).append(spl)
                .append(cost).append(spl)
                .append(costReduce).append(spl)
                .append(costAdjust).append(spl)
                .append(overFlow).append(spl)

                .append(discountRefund).append(spl)
                .append(totalPrincipal).append(spl)
                .append(repaidPrincipal).append(spl)
                .append(totalInterest).append(spl)
                .append(repaidInterest).append(spl)
                .append(totalCharges).append(spl)
                .append(repaidCharges).append(spl)
                .append(totalPenalty).append(spl)
                .append(repaidPenalty).append(spl)
                .append(totalOverdue).append(spl)
                .append(repaidOverdue).append(spl)
                .append(totalViolate).append(spl)
                .append(repaidViolate).append(spl)
                .append(totalManagement).append(spl)
                .append(repaidManagement).append(spl)
                .append(totalService).append(spl)
                .append(repaidService);
        return builder.toString();

    }

}
