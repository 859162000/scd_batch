package com.scd.batch.api;

import com.scd.batch.api.entity.FixProjectDueStatRequest;
import com.scd.batch.api.entity.FixProjectDueStatResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 财主每日充值提现金额
 */

@Validated
public interface FixProjectDueStatAPI {

    FixProjectDueStatResponse query(
            @NotNull(message = "Null fixProjectDueStatRequest!") @Valid FixProjectDueStatRequest fixProjectDueStatRequest);
}
