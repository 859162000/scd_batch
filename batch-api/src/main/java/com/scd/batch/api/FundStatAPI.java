package com.scd.batch.api;

import com.scd.batch.api.entity.FundStatRequest;
import com.scd.batch.api.entity.FundStatResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 财主每日充值提现金额
 */

@Validated
public interface FundStatAPI {

    FundStatResponse query(
            @NotNull(message = "Null fundStatRequest!") @Valid FundStatRequest fundStatRequest);
}
