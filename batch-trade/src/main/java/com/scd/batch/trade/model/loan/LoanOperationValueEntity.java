package com.scd.batch.trade.model.loan;

import com.scd.batch.common.entity.Entity;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoanOperationValueEntity extends Entity {

    /**
     * 借据ID或分期交易流水ID
     */
    private long transactionId;

    /**
     * 借据或分期
     */
    private int sourceType;

    /**
     * 客户ID
     */
    private long customerId;

    /**
     * 操作类型
     */
    private int operationType;

    /**
     * 变更前INT值
     */
    private long beforeValueInt;

    /**
     * 变更后INT值
     */
    private long afterValueInt;

    /**
     * 变更前字符串值
     */
    private String beforeValueString = "";

    /**
     * 变更后字符串值
     */
    private String afterValueString = "";

    /**
     * 分期序号
     */
    private int scheduleNo;

    /**
     * 借据被分期序号
     */
    private int installmentMadeNo;

    public LoanOperationValueEntity() {
    }

    public LoanOperationValueEntity(long transactionId, int sourceType, long customerId, int operationType,
                                    long beforeValueInt, long afterValueInt, int scheduleNo, int installmentMadeNo) {
        this.transactionId = transactionId;
        this.sourceType = sourceType;
        this.customerId = customerId;
        this.operationType = operationType;
        this.beforeValueInt = beforeValueInt;
        this.afterValueInt = afterValueInt;
        this.scheduleNo = scheduleNo;
        this.installmentMadeNo = installmentMadeNo;
        super.setPartitionId(customerId);
    }

    public LoanOperationValueEntity(long transactionId, int sourceType, long customerId, int operationType,
                                    String beforeValue, String afterValue, int scheduleNo, int installmentMadeNo) {
        this.transactionId = transactionId;
        this.sourceType = sourceType;
        this.customerId = customerId;
        this.operationType = operationType;
        this.beforeValueString = beforeValue;
        this.afterValueString = afterValue;
        this.scheduleNo = scheduleNo;
        this.installmentMadeNo = installmentMadeNo;
        super.setPartitionId(customerId);
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
        setPartitionId(customerId);
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public long getBeforeValueInt() {
        return beforeValueInt;
    }

    public void setBeforeValueInt(long beforeValueInt) {
        this.beforeValueInt = beforeValueInt;
    }

    public long getAfterValueInt() {
        return afterValueInt;
    }

    public void setAfterValueInt(long afterValueInt) {
        this.afterValueInt = afterValueInt;
    }

    public String getBeforeValueString() {
        return beforeValueString;
    }

    public void setBeforeValueString(String beforeValueString) {
        this.beforeValueString = beforeValueString;
    }

    public String getAfterValueString() {
        return afterValueString;
    }

    public void setAfterValueString(String afterValueString) {
        this.afterValueString = afterValueString;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}