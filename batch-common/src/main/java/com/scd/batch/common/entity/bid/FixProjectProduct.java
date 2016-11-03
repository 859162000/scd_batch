package com.scd.batch.common.entity.bid;

import java.io.Serializable;

/**
 *定期赚产品
 */
public class FixProjectProduct extends Product implements Serializable, Cloneable {
    private Long id;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     *  购买多少天后可以转让
     */
    private Integer minKeepTrans;
    /**
     * 是否可以装让 是：1 否：0
     */
    private Integer canTransfer;
    /**
     * 原始认购订单号
     */
    private String originSeqNo;
    /**
     * 前一个产品编号
     */
    private String previousCode;

    /**
     *上次交易订单单号
     */
    private String previousSeqNo;
//    /**
//     *到期时间
//     */
//    private Date expireDate;
    /**
     *项目编号
     */
    private String projectCode;
    /**
     *定向标投资者 uid
     */
    private String targetUid;

    /**
     *借款人 id 冗余
     */
    private String borrowerId;

    /**
     * 项目名称
     *
     */
    private String projectName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Integer getMinKeepTrans() {
        return minKeepTrans;
    }

    public void setMinKeepTrans(Integer minKeepTrans) {
        this.minKeepTrans = minKeepTrans;
    }

    public Integer getCanTransfer() {
        return canTransfer;
    }

    public void setCanTransfer(Integer canTransfer) {
        this.canTransfer = canTransfer;
    }


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(String targetUid) {
        this.targetUid = targetUid;
    }

    public String getPreviousCode() {
        return previousCode;
    }

    public void setPreviousCode(String previousCode) {
        this.previousCode = previousCode;
    }

    public String getPreviousSeqNo() {
        return previousSeqNo;
    }

    public void setPreviousSeqNo(String previousSeqNo) {
        this.previousSeqNo = previousSeqNo;
    }

    public String getOriginSeqNo() {
        return originSeqNo;
    }

    public void setOriginSeqNo(String originSeqNo) {
        this.originSeqNo = originSeqNo;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}