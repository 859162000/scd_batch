package com.scd.batch.api;

import com.scd.batch.api.entity.FundStatRequest;
import com.scd.batch.api.entity.FundStatResponse;
import com.scd.batch.api.entity.ProjectLimitRequest;
import com.scd.batch.api.entity.ProjectLimitResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 财主每日充值提现金额
 */

@Validated
public interface ProjectLimitAPI {

    ProjectLimitResponse query(
            @NotNull(message = "Null projectLimitRequest!") @Valid ProjectLimitRequest projectLimitRequest);
}
