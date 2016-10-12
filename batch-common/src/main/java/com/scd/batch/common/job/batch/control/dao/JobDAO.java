package com.scd.batch.common.job.batch.control.dao;

import com.scd.batch.common.job.batch.control.JobCondition;
import com.scd.batch.common.job.batch.control.JobControl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JobDAO {

    int insertOne(JobControl jobControl);

    int insert(@Param("controlList") List<JobControl> controlList);

    JobControl selectOneAvailableJob(@Param("condition") JobCondition condition);

    int updateOneAvailableJob(@Param("condition") JobCondition condition, @Param("uuid") String uuid);

    int updatePhaseStatusByUUID(@Param("uuid") String uuid,
                                @Param("phase") int phase,
                                @Param("phaseStatus") int phaseStatus);

    JobControl getByUUID(@Param("uuid") String uuid);

    List<JobControl> selectJobs(@Param("accountDate") Date accountDate, @Param("jobType") int jobType);
}
