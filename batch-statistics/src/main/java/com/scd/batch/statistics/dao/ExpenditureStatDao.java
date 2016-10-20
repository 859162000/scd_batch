package com.scd.batch.statistics.dao;

import com.scd.batch.common.entity.statistics.ExpenditureStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenditureStatDao {

    // TODO mapper
    int batchInsert(List<ExpenditureStatEntity> expenditureStatEntityList);

    // TODO mapper
    int insert(ExpenditureStatEntity expenditureStatEntity);

    List<ExpenditureStatEntity> getList(@Param("start") long start, @Param("num") long num);
}
