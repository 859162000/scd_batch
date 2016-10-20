package com.scd.batch.api.entity;

import com.scd.batch.common.entity.statistics.BorrowerRepayPlanStatEntity;

import java.util.List;

/**
 * Created by liyankai on 2016/10/9.
 */
public class BorrowerRepayPlanStatResponse extends DefaultResponse {

    private static final long serialVersionUID = 4085598368282960245L;

    // 统计结果列表
    public List<BorrowerRepayPlanStatEntity> borrowerRepayPlanStatEntityList;

    public BorrowerRepayPlanStatResponse() {
    }

    public BorrowerRepayPlanStatResponse(List<BorrowerRepayPlanStatEntity> borrowerRepayPlanStatEntityList) {
        this.borrowerRepayPlanStatEntityList = borrowerRepayPlanStatEntityList;
    }

}
