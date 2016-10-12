package com.scd.batch.statistics.manager;

import com.scd.batch.api.ProjectLimitAPI;
import com.scd.batch.api.entity.ProjectLimitRequest;
import com.scd.batch.api.entity.ProjectLimitResponse;
import com.scd.batch.statistics.service.ProjectLimitService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by liyankai on 2016/10/9.
 */
public class ProjectLimitManager implements ProjectLimitAPI {

    @Autowired
    private ProjectLimitService projectLimitService;

    @Override
    public ProjectLimitResponse query(@NotNull(message = "Null projectLimitRequest!") @Valid ProjectLimitRequest
                                              projectLimitRequest) {

        return new ProjectLimitResponse(projectLimitService.getList(projectLimitRequest.pno *
                projectLimitRequest.psize - 1, projectLimitRequest.psize));
    }
}
