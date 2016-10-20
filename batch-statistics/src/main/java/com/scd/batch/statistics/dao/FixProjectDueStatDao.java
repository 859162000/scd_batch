package com.scd.batch.statistics.dao;

import com.scd.batch.common.entity.statistics.FixProductDueStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixProjectDueStatDao {

    // TODO mapper
    int batchInsert(List<FixProductDueStatEntity> fixProductDueStatEntityList);

    // TODO mapper
    int insert(FixProductDueStatEntity fixProductDueStatEntity);

    List<FixProductDueStatEntity> getList(@Param("start") long start, @Param("num") long num);
}
