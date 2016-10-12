package com.scd.batch.common.job.constants;


import com.scd.batch.common.utils.EnumType;

/**
 * 跑批控制表中处理阶段状态
 *
 * @author Created by hanxiao01 on 16/4/1.
 */
public enum PhaseStatus implements EnumType<PhaseStatus, Integer> {
    INIT        (1),
    PROCESSING  (2),
    FAIL        (3),
    DONE        (4);

    public final int type;

    PhaseStatus(int type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return type;
    }
}
