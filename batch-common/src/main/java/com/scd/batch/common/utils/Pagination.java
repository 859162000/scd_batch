package com.scd.batch.common.utils;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Pagination {

    /** Constants for default page */
    public static final int DEFAULT_PAGE_SIZE = 1000;
    public static final int DEFAULT_PAGE_NUMBER = 1;

    /** The total count of the pages */
    private int count = 0;

    /** Page size */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /** Current page number, index from 1 */
    private int curPage = DEFAULT_PAGE_NUMBER;

    /**
     * Getters & Setters
     */
    public int getPageCount() {
        return count % pageSize == 0 ? 0 : (count / pageSize) + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStartIndex() {
        return pageSize * (curPage - 1);
    }

    public int getEndIndex() {
        int endIndex = getStartIndex() + pageSize;
        return endIndex > count - 1 ? count : endIndex;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
