package com.scd.batch.common.dao.statistics;

import com.scd.batch.common.entity.statistics.FixDueStatEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FixProjectDueStatDao {

    // 插入数据
    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("ts") TableSpec ts, @Param("entity") FixDueStatEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    int checkExists(@Param("ts") TableSpec ts, @Param("dueDate") Date dueDate);

    // 按照日期更新
    @MultiDB(ds = DataSourceType.BATCH)
    int updateIncrement2DB(@Param("ts") TableSpec ts, @Param("entity") FixDueStatEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    List<FixDueStatEntity> getStatList(@Param("ts") TableSpec ts,
                                       @Param("start") long start,
                                       @Param("num") long num);
}
