package com.scd.batch.statistics.dao;

import com.scd.batch.common.entity.statistics.BorrowerRepayPlanStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowerRepayPlanStatDao {

    // TODO mapper
    int batchInsert(List<BorrowerRepayPlanStatEntity> borrowerRepayPlanStatEntityList);

    // TODO mapper
    int insert(BorrowerRepayPlanStatEntity BorrowerRepayPlanStatEntity);

    List<BorrowerRepayPlanStatEntity> getList(@Param("start") long start, @Param("num") long num);
}
