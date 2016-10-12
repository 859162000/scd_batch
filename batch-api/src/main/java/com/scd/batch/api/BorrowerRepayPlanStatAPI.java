package com.scd.batch.api;

import com.scd.batch.api.entity.BorrowerRepayPlanStatRequest;
import com.scd.batch.api.entity.BorrowerRepayPlanStatResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 财主每日充值提现金额
 */

@Validated
public interface BorrowerRepayPlanStatAPI {

    BorrowerRepayPlanStatResponse query(
            @NotNull(message = "Null borrowerRepayPlanStatRequest!") @Valid BorrowerRepayPlanStatRequest
                    borrowerRepayPlanStatRequest);
}
