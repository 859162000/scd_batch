package com.scd.batch.api.entity;

import com.scd.batch.common.entity.AssetsStatEntity;
import com.scd.batch.common.entity.FundStatEntity;

import java.util.List;

/**
 * Created by liyankai on 2016/10/9.
 */
public class AssetStatResponse extends DefaultResponse {

    private static final long serialVersionUID = 4085598368282960245L;

    // 统计结果列表
    public List<AssetsStatEntity> assetsStatEntityList;

    public AssetStatResponse() {
    }

    public AssetStatResponse(List<AssetsStatEntity> assetsStatEntityList) {
        this.assetsStatEntityList = assetsStatEntityList;
    }

}
