package com.scd.batch.statistics.manager;

import com.scd.batch.api.FixProjectDueStatAPI;
import com.scd.batch.api.entity.FixProjectDueStatRequest;
import com.scd.batch.api.entity.FixProjectDueStatResponse;
import com.scd.batch.statistics.service.FixProjectDueStatService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by liyankai on 2016/10/9.
 */
public class FixProjectDueStatManager implements FixProjectDueStatAPI {

    @Autowired
    private FixProjectDueStatService fundStatService;

    @Override
    public FixProjectDueStatResponse query(@NotNull(message = "Null fixProjectDueStatRequest!") @Valid
                                                   FixProjectDueStatRequest
                                                   fixProjectDueStatRequest) {

        return null;
    }
}
