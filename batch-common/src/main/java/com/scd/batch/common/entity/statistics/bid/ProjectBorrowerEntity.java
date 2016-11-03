package com.scd.batch.common.entity.statistics.bid;

/**
 * 借款人相关统计项
 */
public class ProjectBorrowerEntity {

    private String projectCode;

    private Long borrowerId;

    private String borrowerName;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
}
