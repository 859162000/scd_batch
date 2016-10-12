package com.scd.batch.statistics.dao;

import com.scd.batch.common.entity.AssetsStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetsStatDao {

    // TODO mapper
    int batchInsert(List<AssetsStatEntity> assetsStatDaoList);

    // TODO mapper
    int insert(AssetsStatEntity assetsStatEntity);

    List<AssetsStatEntity> getList(@Param("start") long start, @Param("num") long num);
}
