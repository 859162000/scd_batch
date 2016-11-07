package com.scd.batch.common.mybatis.multidb;

import com.scd.batch.common.utils.EnumType;

public enum DataSourceType implements EnumType<DataSourceType, Integer> {

    BATCH(1, "BATCH"),
    BID(2, "BID"),
    TRADE(3, "TRADE"),
    USER(4, "NOT"),
    PROMOTION(5, "PROMOTION"),
    STATISTICS(6, "STATISTICS");

    public int type;

    public String name;

    DataSourceType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getType() {
        return this.type;
    }

}
