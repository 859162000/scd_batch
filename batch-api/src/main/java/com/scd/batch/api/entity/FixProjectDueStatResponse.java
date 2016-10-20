package com.scd.batch.api.entity;

import com.scd.batch.common.entity.statistics.FundStatEntity;

import java.util.List;

/**
 * Created by liyankai on 2016/10/9.
 */
public class FixProjectDueStatResponse extends DefaultResponse {

    private static final long serialVersionUID = 4085598368282960245L;

    // 统计结果列表
    public List<FundStatEntity> fundStatEntityList;

    public FixProjectDueStatResponse() {
    }

    public FixProjectDueStatResponse(List<FundStatEntity> fundStatEntityList) {
        this.fundStatEntityList = fundStatEntityList;
    }

}
