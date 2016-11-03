package com.scd.batch.common.entity.bid;


import java.io.Serializable;
import java.util.Date;

/**
 *产品公共类
 */
public class Product implements Serializable {
    /**
     * 自增id
     */
    private Long id;
    /**
     *产品编码 CP开头 25位字符串
     */
    private String productCode;
    /**
     *产品名称
     */
    private String productName;
    /**
     *发布者 用户id
     */
    private String pubUserId;
    /**
     *发布用户类型 内部用户，投资者，想
     */
    private Integer pubUserType;
    /**
     *产品开始时间
     */
    private Date startTime;
    /**
     *产品结束时间
     */
    private Date endTime;
    /**
     *产品类型
     */
    private Integer productType;

    /**
     *产品描述
     */
    private String description;

    /**
     *产品状态
     *
     */
    private Integer status;
    /**
     *产品渠道
     */
    private String channelCode;
    /**
     *满标时间
     */
    private Date fullTime;
    /**
     *计息模式
     */
    private String interestAccrualMode;
    /**
     *标的类型
     */
    private String bidType;
    /**
     *最大投资额度
     */
    private Double maxInvestAmount;
    /**
     *最小投资额度
     */
    private Double minInvestAmount;
    /**
     *续投额度限制
     */
    private Double nextInvestAmount;
    /**
     *最大利率限制
     */
    private Double maxRate;
    /**
     *购入价格
     */
    private Double bidPrice=0.0D;
    /**
     *结清时间
     */
    private Date overTime;
    /**
     *债权总额度
     */
    private Double bidAmount;
    /**
     *债权已出售额度
     */
    private Double investedAmount;
    /**
     *债权剩余额度
     */
    private Double remainAmount;
    /**
     * 实际展示的年利率,转让产品实际年利率和productRate不相等
     */
    private Double yearRate;

    /**
     * 产品的年利率,不在页面展示
     */
    private Double productRate;
    /**
     *数据状态
     */
    private Integer dataStatus;
    /**
     *产品创建时间
     */
    private Date createTime;
    /**
     *计息开始时间
     */
    private Integer interestStartDay;
    /**
     *加息
     */
    private Double addInterestRate;
    /**
     *赠送积分
     */
    private String presentIntegral;
    /**
     *债权冻结额度
     */
    private Double frozenAmount;
    /**
     *发布流程编号
     */
    private String pubStepCode;
    /**
     *债权单元额度
     */
    private Double unitAmount;
    /**
     * 回购债权金额
     */
    private Double buyBackAmount;
    /**
     * 产品过期时间
     */
    private Date expireDate;
    /**
     * 产品还款类型，定期赚产品才有
     */
    private String repayType;
    /**
     * 产品投资人次
     */
    private Integer investedCount=0;
    /**
     * 产品投资类型
     */
    private Integer investType;

    /**
     * 是否是定向发布到某个用户的产品
     * 定向产品不在普通用户产品列表展示
     */
    private Integer isTarget;

    /**
     * 还款状态
     */
    private Integer repayStatus;

    /**
     * 产品操作服部流程的备注
     * 不加入product的表，
     * 加入到productOperateLog表
     */
    private String operateRemark;

    public Double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Double remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Double unitAmount) {
        this.unitAmount = unitAmount;
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getPubUserId() {
        return pubUserId;
    }

    public void setPubUserId(String pubUserId) {
        this.pubUserId = pubUserId == null ? null : pubUserId.trim();
    }

    public Integer getPubUserType() {
        return pubUserType;
    }

    public void setPubUserType(Integer pubUserType) {
        this.pubUserType = pubUserType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public Date getFullTime() {
        return fullTime;
    }

    public void setFullTime(Date fullTime) {
        this.fullTime = fullTime;
    }

    public String getInterestAccrualMode() {
        return interestAccrualMode;
    }

    public void setInterestAccrualMode(String interestAccrualMode) {
        this.interestAccrualMode = interestAccrualMode == null ? null : interestAccrualMode.trim();
    }

    public String getBidType() {
        return bidType;
    }

    public void setBidType(String bidType) {
        this.bidType = bidType == null ? null : bidType.trim();
    }

    public Double getMaxInvestAmount() {
        return maxInvestAmount;
    }

    public void setMaxInvestAmount(Double maxInvestAmount) {
        this.maxInvestAmount = maxInvestAmount;
    }

    public Double getMinInvestAmount() {
        return minInvestAmount;
    }

    public void setMinInvestAmount(Double minInvestAmount) {
        this.minInvestAmount = minInvestAmount;
    }

    public Double getNextInvestAmount() {
        return nextInvestAmount;
    }

    public void setNextInvestAmount(Double nextInvestAmount) {
        this.nextInvestAmount = nextInvestAmount;
    }

    public Double getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Double maxRate) {
        this.maxRate = maxRate;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(Double investedAmount) {
        this.investedAmount = investedAmount == null ? 0.0 : investedAmount;
    }

    public Double getYearRate() {
        return yearRate;
    }

    public void setYearRate(Double yearRate) {
        this.yearRate = yearRate;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getInterestStartDay() {
        return interestStartDay;
    }

    public void setInterestStartDay(Integer interestStartDay) {
        this.interestStartDay = interestStartDay;
    }


    public Double getAddInterestRate() {
        return addInterestRate;
    }

    public void setAddInterestRate(Double addInterestRate) {
        this.addInterestRate = addInterestRate;
    }

    public String getPresentIntegral() {
        return presentIntegral;
    }

    public void setPresentIntegral(String presentIntegral) {
        this.presentIntegral = presentIntegral == null ? null : presentIntegral.trim();
    }

    public Double getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Double frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getPubStepCode() {
        return pubStepCode;
    }

    public void setPubStepCode(String pubStepCode) {
        this.pubStepCode = pubStepCode == null ? null : pubStepCode.trim();
    }

    public Integer getInvestType() {
        return investType;
    }

    public void setInvestType(Integer investType) {
        this.investType = investType;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getBuyBackAmount() {
        return buyBackAmount;
    }

    public void setBuyBackAmount(Double buyBackAmount) {
        this.buyBackAmount = buyBackAmount;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public Integer getInvestedCount() {
        return investedCount;
    }

    public void setInvestedCount(Integer investedCount) {
        this.investedCount = investedCount;
    }

    public Integer getIsTarget() {
        return isTarget;
    }

    public void setIsTarget(Integer isTarget) {
        this.isTarget = isTarget;
    }

    public Double getProductRate() {
        return productRate;
    }

    public void setProductRate(Double productRate) {
        this.productRate = productRate;
    }

    public Integer getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(Integer repayStatus) {
        this.repayStatus = repayStatus;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }
}