package com.scd.batch.statistics.manager;

import com.scd.batch.api.FundStatAPI;
import com.scd.batch.api.entity.FundStatResponse;
import com.scd.batch.api.entity.FundStatRequest;
import com.scd.batch.statistics.service.FundStatService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by liyankai on 2016/10/9.
 */
public class FundStatManager implements FundStatAPI {

    @Autowired
    private FundStatService fundStatService;

    @Override
    public FundStatResponse query(@NotNull(message = "Null fundStatRequest!") @Valid FundStatRequest
                                          fundStatRequest) {

        return new FundStatResponse(fundStatService.getStatList(fundStatRequest.pno * fundStatRequest.psize - 1,
                fundStatRequest.psize));
    }
}
