package com.scd.batch.common.job.batch.control.dao;

import java.util.Date;
import java.util.List;

import com.scd.batch.common.entity.executor.FailRecordEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.mybatis.version.OptimisticLock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FailRecordDao {

    @MultiDB
    int insertOne(FailRecordEntity entity);

    @MultiDB
    int insert(@Param("failList") List<FailRecordEntity> failList);

    @MultiDB
    List<Long> selectFailureId(@Param("accountDate") Date accountDate,
                               @Param("failStatus") int failStatus);

    @MultiDB
    List<Long> selectAllFailedId(@Param("accountDate") Date accountDate);

    @MultiDB
    List<FailRecordEntity> selectById(@Param("idList") List<Long> idList);

    @OptimisticLock(expect = 1)
    @MultiDB
    int updateStatusById(@Param("id") long id, @Param("failStatus") int failStatus);

    @MultiDB
    int updateSuccessStatus(@Param("accountDate") Date accountDate, @Param("failStatus") int failStatus);
}
