package com.scd.batch.common.entity.bid;

import java.io.Serializable;
import java.util.Date;

public class BorrowerRelation implements Serializable {
    private Integer id;//自增ID
    private String projectCode;//项目编码
    private String borrowerId;//借款人ID

    private String loanContId;//借款合同id
    private String oldContId;//原合同编号
    private String oldContName;//原合同名称
    private String creditor;//债权人简称
    private String creditorName;//债权人名称
    private String creditorAddress;//债权人地址
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer dataStatus;//数据状态:1-未删 0-已删

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getLoanContId() {
        return loanContId;
    }

    public void setLoanContId(String loanContId) {
        this.loanContId = loanContId == null ? null : loanContId.trim();
    }

    public String getOldContId() {
        return oldContId;
    }

    public void setOldContId(String oldContId) {
        this.oldContId = oldContId == null ? null : oldContId.trim();
    }

    public String getOldContName() {
        return oldContName;
    }

    public void setOldContName(String oldContName) {
        this.oldContName = oldContName == null ? null : oldContName.trim();
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor == null ? null : creditor.trim();
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName == null ? null : creditorName.trim();
    }

    public String getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(String creditorAddress) {
        this.creditorAddress = creditorAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    @Override
    public String toString() {
        return "BorrowerRelation{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", borrowerId='" + borrowerId + '\'' +
                ", loanContId='" + loanContId + '\'' +
                ", oldContId='" + oldContId + '\'' +
                ", oldContName='" + oldContName + '\'' +
                ", creditor='" + creditor + '\'' +
                ", creditorName='" + creditorName + '\'' +
                ", creditorAddress='" + creditorAddress + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", dataStatus=" + dataStatus +
                '}';
    }
}