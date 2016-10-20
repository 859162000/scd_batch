package com.scd.batch.api.entity;

import com.scd.batch.common.entity.statistics.FundStatEntity;

import java.util.List;

/**
 * Created by liyankai on 2016/10/9.
 */
public class FundStatResponse extends DefaultResponse {

    private static final long serialVersionUID = 8586683508932702714L;

    // 统计结果列表
    public List<FundStatEntity> fundStatEntityList;

    public FundStatResponse() {
    }

    public FundStatResponse(List<FundStatEntity> fixProjectDueStatEntityList) {
        this.fundStatEntityList = fixProjectDueStatEntityList;
    }

}
