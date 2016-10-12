package com.scd.batch.statistics.dao;

import com.scd.batch.common.entity.FundStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundStatDao {

    // TODO mapper
    int batchInsert(List<FundStatEntity> fundStatEntityList);

    // TODO mapper
    int insert(FundStatEntity fundStatEntity);

    List<FundStatEntity> getList(@Param("start") long start, @Param("num") long num);
}
