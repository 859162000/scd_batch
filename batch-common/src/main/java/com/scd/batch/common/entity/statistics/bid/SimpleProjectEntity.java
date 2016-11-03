package com.scd.batch.common.entity.statistics.bid;

/**
 * Created by liwei on 2016-10-21.
 */
public class SimpleProjectEntity {

    private long id;

    private String projectCode;

    private String projectName;

    private int repayType;

    private double bidAmount;

    private double occupyAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getRepayType() {
        return repayType;
    }

    public void setRepayType(int repayType) {
        this.repayType = repayType;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getOccupyAmount() {
        return occupyAmount;
    }

    public void setOccupyAmount(double occupyAmount) {
        this.occupyAmount = occupyAmount;
    }
}
