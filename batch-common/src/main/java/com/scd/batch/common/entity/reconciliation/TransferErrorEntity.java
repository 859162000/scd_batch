package com.scd.batch.common.entity.reconciliation;

import com.scd.batch.common.entity.Entity;

/**
 * 流水不平实体
 */
public class TransferErrorEntity extends Entity {

    // 流水号或者订单号
    private String transId;

    // 流水类型
    private int transferType;

    // 不平类型
    private int errorType;

    private String aValue;

    private String bValue;

    private String aJson;

    private String bJson;

    private int errorStatus;

    public  TransferErrorEntity(){

    };

    public TransferErrorEntity(String transId, int transferType, int errorType, int errorStatus) {
        this.transId = transId;
        this.transferType = transferType;
        this.errorType = errorType;
        this.errorStatus = errorStatus;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getaValue() {
        return aValue;
    }

    public void setaValue(String aValue) {
        this.aValue = aValue;
    }

    public String getbValue() {
        return bValue;
    }

    public void setbValue(String bValue) {
        this.bValue = bValue;
    }

    public String getaJson() {
        return aJson;
    }

    public void setaJson(String aJson) {
        this.aJson = aJson;
    }

    public String getbJson() {
        return bJson;
    }

    public void setbJson(String bJson) {
        this.bJson = bJson;
    }

    public int getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(int errorStatus) {
        this.errorStatus = errorStatus;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }
}
