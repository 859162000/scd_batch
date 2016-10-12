package com.scd.batch.api;

import com.scd.batch.api.entity.AssetStatRequest;
import com.scd.batch.api.entity.AssetStatResponse;
import com.scd.batch.api.entity.FixProjectDueStatRequest;
import com.scd.batch.api.entity.FixProjectDueStatResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 财主每日充值提现金额
 */

@Validated
public interface AssetStatAPI {

    AssetStatResponse query(
            @NotNull(message = "Null assetStatRequest!") @Valid AssetStatRequest assetStatRequest);
}
