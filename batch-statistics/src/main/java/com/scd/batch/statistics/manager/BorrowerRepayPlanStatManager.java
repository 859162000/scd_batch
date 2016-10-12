package com.scd.batch.statistics.manager;

import com.scd.batch.api.BorrowerRepayPlanStatAPI;
import com.scd.batch.api.entity.BorrowerRepayPlanStatRequest;
import com.scd.batch.api.entity.BorrowerRepayPlanStatResponse;
import com.scd.batch.statistics.service.BorrowerRepayPlanStatService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by liyankai on 2016/10/9.
 */
public class BorrowerRepayPlanStatManager implements BorrowerRepayPlanStatAPI {

    @Autowired
    private BorrowerRepayPlanStatService borrowerRepayPlanStatService;

    @Override
    public BorrowerRepayPlanStatResponse query(@NotNull(message = "Null borrowerRepayPlanStatRequest!") @Valid
                                               BorrowerRepayPlanStatRequest
                                                       borrowerRepayPlanStatRequest) {

        return new BorrowerRepayPlanStatResponse(borrowerRepayPlanStatService.getStatList
                (borrowerRepayPlanStatRequest.pno * borrowerRepayPlanStatRequest.psize - 1,
                        borrowerRepayPlanStatRequest.psize));
    }
}
