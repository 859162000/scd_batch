package com.scd.batch.common.job.batch.control.dao;

import com.scd.batch.common.job.batch.control.JobCondition;
import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JobDao {

    @MultiDB(ds = DataSourceType.BATCH)
    int insertOne(JobControl jobControl);

    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("controlList") List<JobControl> controlList);

    @MultiDB(ds = DataSourceType.BATCH)
    JobControl selectOneAvailableJob(@Param("condition") JobCondition condition);

    @MultiDB(ds = DataSourceType.BATCH)
    int updateOneAvailableJob(@Param("condition") JobCondition condition, @Param("uuid") String uuid);

    @MultiDB(ds = DataSourceType.BATCH)
    int updatePhaseStatusByUUID(@Param("uuid") String uuid,
                                @Param("phase") int phase,
                                @Param("phaseStatus") int phaseStatus);

    @MultiDB(ds = DataSourceType.BATCH)
    JobControl getByUUID(@Param("uuid") String uuid);

    @MultiDB(ds = DataSourceType.BATCH)
    List<JobControl> selectJobs(@Param("accountDate") Date accountDate, @Param("jobType") int jobType);
}
