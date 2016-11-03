package com.scd.batch.common.entity.bid;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    private Long id;//自增ID
    private String projectName;//项目名称
    private String projectCode;//项目编码
    private String workNo;
    private String bidType;//标的类型:01-信用 02-抵押 03-债权转让 99-其他
    private Double bidAmount;//标的金额
    private Double yearRate;//年化利率
    private Integer minInvestCount;//最低投标份数
    private Double unitAmount; //每份投标金额
    private Double minInvestAmount;//最低投标金额
    private Double maxInvestAmount;//最高投标金额
    private Double totalInterest;//应还总利息
    private Date bidStartTime;//投标开始时间
    private Date bidEndTime;//投标结束时间
    private Date repayTime;//还款时间
    private Date lastRepayTime;//最后还款时间
    private Integer loanPeriod;//贷款期限
    private String loanPeriodUnit;//借款单位（Y年，M月，D日）
    /**
     */
    private String repayType;//还款方式:01- 一次还本付息 02-等额本金 03-等额本息 04-按期付息到期还本 99-其他
    private String guaranteeType;//本息保障：01-保本保息 02-保本不保息 03-不保本不保息
    private String bidProductType;//标的产品类型:01-房贷类 02-车贷类 03-收益权转让类 04-信用贷款类 05-股票配资类 06-银行承兑汇票 07-商业承兑汇票 08-消费贷款类 09-供应链类 99-其他
    private Integer isPadFunded;//是否需要垫资:0-不需要 1-需要
    private String riskCtrlType;//风险控制类型：01-抵（质）押 02-共管账户 03-担保 04-信用无担保 99-其他
    private String borrowerId;//借款人ID
    private String thdAuditStatus;//审核状态: 01-通过 02-拒绝 03-待上传证照 04-待审核 05-待审核证照 06-状态异常
    private String thdAuditDesc;//审核状态描述
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private Integer dataStatus = 1;//数据状态：1-未删，0-已删
    /**
     * 已投资金额
     */
    private Double investedAmount;

    /**
     *冻结金额
     */
    private Double frozenAmount;

    /**
     * 占用金额
     */
    private Double occupyAmount  = 0.0D;

    /**
     * 借款用途
     */
    private String loanUse;

    private Integer status;
    /**
     * 已还款额度
     */
    private Double repayAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getBidType() {
        return bidType;
    }

    public void setBidType(String bidType) {
        this.bidType = bidType == null ? null : bidType.trim();
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Double getYearRate() {
        return yearRate;
    }

    public void setYearRate(Double yearRate) {
        this.yearRate = yearRate;
    }

    public Integer getMinInvestCount() {
        return minInvestCount;
    }

    public void setMinInvestCount(Integer minInvestCount) {
        this.minInvestCount = minInvestCount;
    }

    public Double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public Double getMinInvestAmount() {
        return minInvestAmount;
    }

    public void setMinInvestAmount(Double minInvestAmount) {
        this.minInvestAmount = minInvestAmount;
    }

    public Double getMaxInvestAmount() {
        return maxInvestAmount;
    }

    public void setMaxInvestAmount(Double maxInvestAmount) {
        this.maxInvestAmount = maxInvestAmount;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Date getBidStartTime() {
        return bidStartTime;
    }

    public void setBidStartTime(Date bidStartTime) {
        this.bidStartTime = bidStartTime;
    }

    public Date getBidEndTime() {
        return bidEndTime;
    }

    public void setBidEndTime(Date bidEndTime) {
        this.bidEndTime = bidEndTime;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Date getLastRepayTime() {
        return lastRepayTime;
    }

    public void setLastRepayTime(Date lastRepayTime) {
        this.lastRepayTime = lastRepayTime;
    }

    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public String getLoanPeriodUnit() {
        return loanPeriodUnit;
    }

    public void setLoanPeriodUnit(String loanPeriodUnit) {
        this.loanPeriodUnit = loanPeriodUnit == null ? null : loanPeriodUnit.trim();
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType == null ? null : repayType.trim();
    }


    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public String getBidProductType() {
        return bidProductType;
    }

    public void setBidProductType(String bidProductType) {
        this.bidProductType = bidProductType == null ? null : bidProductType.trim();
    }

    public Integer getIsPadFunded() {
        return isPadFunded;
    }

    public void setIsPadFunded(Integer isPadFunded) {
        this.isPadFunded = isPadFunded;
    }

    public String getRiskCtrlType() {
        return riskCtrlType;
    }

    public void setRiskCtrlType(String riskCtrlType) {
        this.riskCtrlType = riskCtrlType == null ? null : riskCtrlType.trim();
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getThdAuditStatus() {
        return thdAuditStatus;
    }

    public void setThdAuditStatus(String thdAuditStatus) {
        this.thdAuditStatus = thdAuditStatus;
    }

    public String getThdAuditDesc() {
        return thdAuditDesc;
    }

    public void setThdAuditDesc(String thdAuditDesc) {
        this.thdAuditDesc = thdAuditDesc;
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

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
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

    public Double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(Double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public Double getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Double frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public Double getOccupyAmount() {
        return occupyAmount;
    }

    public void setOccupyAmount(Double occupyAmount) {
        this.occupyAmount = occupyAmount;
    }

    public String getLoanUse() {
        return loanUse;
    }

    public void setLoanUse(String loanUse) {
        this.loanUse = loanUse;
    }


    public Double getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Double repayAmount) {
        this.repayAmount = repayAmount;
    }
}