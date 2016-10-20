package com.scd.batch.common.job.constants;


import com.scd.batch.common.utils.EnumType;

/**
 * Created on 19:37 04/25/2016.
 *
 * @author <a href="mailto:songguo@baidu.com">songguo</a>
 */
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
