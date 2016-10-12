package com.scd.batch.api.entity;

import com.scd.batch.common.entity.ExpenditureStatEntity;

import java.util.List;

/**
 * Created by liyankai on 2016/10/9.
 */
public class ExpenditureStatResponse extends DefaultResponse {

    private static final long serialVersionUID = 4085598368282960245L;

    // 统计结果列表
    public List<ExpenditureStatEntity> expenditureStatEntityList;

    public ExpenditureStatResponse() {
    }

    public ExpenditureStatResponse(List<ExpenditureStatEntity> expenditureStatEntityList) {
        this.expenditureStatEntityList = expenditureStatEntityList;
    }

}
