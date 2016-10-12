package com.scd.batch.statistics.manager;

import com.scd.batch.api.ExpenditureStatAPI;
import com.scd.batch.api.entity.ExpenditureStatRequest;
import com.scd.batch.api.entity.ExpenditureStatResponse;
import com.scd.batch.statistics.service.ExpenditureStatService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by liyankai on 2016/10/9.
 */
public class ExpenditureStatManager implements ExpenditureStatAPI {

    @Autowired
    private ExpenditureStatService expenditureStatService;

    @Override
    public ExpenditureStatResponse query(@NotNull(message = "Null expenditureStatRequest!") @Valid
                                         ExpenditureStatRequest
                                                 expenditureStatRequest) {

        return new ExpenditureStatResponse(expenditureStatService.getStatList(expenditureStatRequest.pno *
                expenditureStatRequest.psize - 1, expenditureStatRequest.psize));
    }
}
