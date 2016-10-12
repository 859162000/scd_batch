package com.scd.batch.common.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TableSpec {
    private String dbId;
    private String tableId;

    public TableSpec(String dbId, String tableId) {
        this.dbId = dbId;
        this.tableId = tableId;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
