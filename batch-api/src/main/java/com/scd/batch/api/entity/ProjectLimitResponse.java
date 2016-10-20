package com.scd.batch.api.entity;

import com.scd.batch.common.entity.statistics.ProjectLimitEntity;

import java.util.List;

/**
 * Created by liyankai on 2016/10/9.
 */
public class ProjectLimitResponse extends DefaultResponse {

    private static final long serialVersionUID = 8586683508932702714L;

    // 统计结果列表
    public List<ProjectLimitEntity> projectLimitEntityList;

    public ProjectLimitResponse() {
    }

    public ProjectLimitResponse(List<ProjectLimitEntity> projectLimitEntityList) {
        this.projectLimitEntityList = projectLimitEntityList;
    }

}
