package com.scd.batch.common.job.constants;


import com.scd.batch.common.utils.EnumType;

public enum SourceType implements EnumType<SourceType, String> {
    
    SCD("SCD"),
    HUIFU("HIUFU");

    public final String type;

    SourceType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
