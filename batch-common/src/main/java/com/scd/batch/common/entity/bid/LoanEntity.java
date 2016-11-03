package com.scd.batch.common.entity.bid;

import com.scd.batch.common.entity.reconciliation.TransferValue;

import java.io.Serializable;
import java.util.Date;

/**
 * 放款流水实体
 */
public class LoanEntity implements Serializable, TransferValue {

    private String seqId;

    private Long id;

    /**
     * 放款单号,给汇付传的交易单号
     */
    private String loanFlowNo;

    /**
     * 认购产品的交易单号
     */
    private String orderSeqNo;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 产品类型
     */
    private Integer productType;

    /**
     * 和汇付的交易时的单号,调用汇付时的subOrderId
     */
    private String orderFlowNo;

    /**
     * 借款人Id
     */
    private String borrowerId;

    /**
     * 投资人Id
     */
    private String investUid;

    /**
     * 认购的金额,即放款金额
     */
    private Double tradeAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 认购时间
     */
    private Date orderTime;

    /**
     * 放款成功时间
     */
    private Date successTime;

    private Integer status;

    private Integer previousStatus;

    private Integer dataStatus;

    private String thirdResultCode;

    private String thirdResultMsg;


    // TODO 返回值
    public String getValue() {
        return "";
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public Long getId() {
        return id;
    }

    public String getOrderSeqNo() {
        return orderSeqNo;
    }

    public void setOrderSeqNo(String orderSeqNo) {
        this.orderSeqNo = orderSeqNo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getOrderFlowNo() {
        return orderFlowNo;
    }

    public void setOrderFlowNo(String orderFlowNo) {
        this.orderFlowNo = orderFlowNo;
    }

    public String getLoanFlowNo() {
        return loanFlowNo;
    }

    public void setLoanFlowNo(String loanFlowNo) {
        this.loanFlowNo = loanFlowNo;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getInvestUid() {
        return investUid;
    }

    public void setInvestUid(String investUid) {
        this.investUid = investUid;
    }

    public Double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(Integer previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getThirdResultCode() {
        return thirdResultCode;
    }

    public void setThirdResultCode(String thirdResultCode) {
        this.thirdResultCode = thirdResultCode;
    }

    public String getThirdResultMsg() {
        return thirdResultMsg;
    }

    public void setThirdResultMsg(String thirdResultMsg) {
        this.thirdResultMsg = thirdResultMsg;
    }
}
