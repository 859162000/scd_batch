package com.scd.batch.statistics.dao;

import com.scd.batch.common.entity.statistics.ProjectLimitEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectLimitDao {

    // TODO mapper
    int batchInsert(List<ProjectLimitEntity> projectLimitEntityList);

    // TODO mapper
    int insert(ProjectLimitEntity projectLimitEntity);

    List<ProjectLimitEntity> getList(@Param("start") long start, @Param("num") long num);
}
