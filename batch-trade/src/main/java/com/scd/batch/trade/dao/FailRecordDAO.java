package com.scd.batch.trade.dao;

import java.util.Date;
import java.util.List;

import com.scd.batch.common.mybatis.version.OptimisticLock;
import com.scd.batch.trade.model.FailRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FailRecordDAO {

    int insertOne(FailRecordEntity entity);

    int insert(@Param("failList") List<FailRecordEntity> failList);

    List<Long> selectFailureId(@Param("accountDate") Date accountDate, @Param("failStatus") int failStatus);

    List<Long> selectAllFailedId(@Param("accountDate") Date accountDate);

    List<FailRecordEntity> selectById(@Param("idList") List<Long> idList);

    @OptimisticLock(expect = 1)
    int updateStatusById(@Param("id") long id, @Param("failStatus") int failStatus);

    int updateSuccessStatus(@Param("accountDate") Date accountDate, @Param("failStatus") int failStatus);
}
