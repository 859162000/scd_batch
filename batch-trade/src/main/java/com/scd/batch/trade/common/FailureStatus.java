package com.scd.batch.trade.common;

import com.scd.batch.common.utils.EnumType;

public enum  FailureStatus implements EnumType<FailureStatus, Integer> {
    INIT       (1),
    RETRY_SUCC (2),
    RETRY_FAIL (3);

    public final int type;

    FailureStatus(int type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return type;
    }
}
