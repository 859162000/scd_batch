package com.scd.batch.api;

import com.scd.batch.api.entity.AssetStatRequest;
import com.scd.batch.api.entity.AssetStatResponse;
import com.scd.batch.api.entity.ExpenditureStatRequest;
import com.scd.batch.api.entity.ExpenditureStatResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 财主每日充值提现金额
 */

@Validated
public interface ExpenditureStatAPI {

    ExpenditureStatResponse query(
            @NotNull(message = "Null expenditureStatRequest!") @Valid ExpenditureStatRequest expenditureStatRequest);
}
