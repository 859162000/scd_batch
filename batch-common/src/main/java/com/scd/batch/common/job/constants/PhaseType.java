package com.scd.batch.common.job.constants;


import com.scd.batch.common.utils.EnumType;

/**
 * Created on 19:37 04/25/2016.
 *
 * @author <a href="mailto:songguo@baidu.com">songguo</a>
 */
public enum PhaseType implements EnumType<PhaseType, Integer> {
    LOAD       (1),
    CALCULATE  (2),
    UPDATE     (3);

    public final int type;

    PhaseType(int type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return null;
    }
}
