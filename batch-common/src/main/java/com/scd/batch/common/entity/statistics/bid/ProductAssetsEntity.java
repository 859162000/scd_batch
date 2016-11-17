package com.scd.batch.common.entity.statistics.bid;

/**
 * 按照产品计算的资产规模统计项
 */
public class ProductAssetsEntity {

    // 产品类型
    private int productType;

    /**
     * 剩余金额
     */
    private Double remainAmount;

    /**
     * 交易金额
     */
    private Double tradeAmount;

    /**
     * 已收本金
     */
    private Double receiptedAmount;

    /**
     * 已转出本金
     */
    private Double transferredAmount;

    /**
     * 已收利息
     */
    private Double receiptedInterest;

    /**
     * 总利息
     */
    private Double totalInterest;

    public ProductAssetsEntity() {
    }

    public ProductAssetsEntity(int productType, Double remainAmount, Double tradeAmount, Double receiptedAmount,
                               Double transferredAmount, Double receiptedInterest, Double totalInterest) {
        this.productType = productType;
        this.remainAmount = remainAmount;
        this.tradeAmount = tradeAmount;
        this.receiptedAmount = receiptedAmount;
        this.transferredAmount = transferredAmount;
        this.receiptedInterest = receiptedInterest;
        this.totalInterest = totalInterest;
    }

    public double getAssets() {
        return tradeAmount - transferredAmount + receiptedInterest;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public Double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Double remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Double getReceiptedAmount() {
        return receiptedAmount;
    }

    public void setReceiptedAmount(Double receiptedAmount) {
        this.receiptedAmount = receiptedAmount;
    }

    public Double getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(Double transferredAmount) {
        this.transferredAmount = transferredAmount;
    }

    public Double getReceiptedInterest() {
        return receiptedInterest;
    }

    public void setReceiptedInterest(Double receiptedInterest) {
        this.receiptedInterest = receiptedInterest;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }
}
