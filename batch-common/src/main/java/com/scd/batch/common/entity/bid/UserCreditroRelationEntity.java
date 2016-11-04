package com.scd.batch.common.entity.bid;

import java.util.Date;

/**
 * 用户持有债权实体
 */
public class UserCreditroRelationEntity {

    private long id;

    // 购买者Id
    private String buyerUid;

    // 项目编号
    private String projectCode;

    // 产品编号
    private String productCode;

    // 剩余本金
    private double remainPrincipal;

    // 利率化 360天
    private double interestRate;

    // 首次计息时间
    private Date interestDate;

    // 到期时间
    private Date expireDate;

    // 产品类型
    private int productType;

    public UserCreditroRelationEntity() {
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public Date getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(String buyerUid) {
        this.buyerUid = buyerUid;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getRemainPrincipal() {
        return remainPrincipal;
    }

    public void setRemainPrincipal(double remainPrincipal) {
        this.remainPrincipal = remainPrincipal;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
