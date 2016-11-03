package com.scd.batch.statistics.manager;

import com.scd.batch.api.AssetStatAPI;
import com.scd.batch.api.entity.AssetStatRequest;
import com.scd.batch.api.entity.AssetStatResponse;
import com.scd.batch.statistics.service.AssetsStatService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by liyankai on 2016/10/9.
 */
public class AssetsStatManager implements AssetStatAPI {

    @Autowired
    private AssetsStatService assetsStatService;

    @Override
    public AssetStatResponse query(@NotNull(message = "Null assetStatRequest!") @Valid AssetStatRequest
                                           assetStatRequest) {

        // TODO
        return null;
    }
}
