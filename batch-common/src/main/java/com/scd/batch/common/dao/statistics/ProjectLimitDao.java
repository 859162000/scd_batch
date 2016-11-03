package com.scd.batch.common.dao.statistics;

import com.scd.batch.common.entity.statistics.ProjectLimitEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectLimitDao {

    @MultiDB(ds = DataSourceType.BATCH)
    List<ProjectLimitEntity> getList(@Param("ts") TableSpec ts,
                                     @Param("start") long start,
                                     @Param("num") long num);

    // 检查ProjectCode是否存在
    @MultiDB(ds = DataSourceType.BATCH)
    int checkExists(@Param("ts") TableSpec ts,
                    @Param("projectCode") String projectCode);

    // 更新
    @MultiDB(ds = DataSourceType.BATCH)
    int update2DB(@Param("ts") TableSpec ts,
                  @Param("entity") ProjectLimitEntity updateEntity);

    // 写入
    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("ts") TableSpec ts,
               @Param("entity") ProjectLimitEntity projectLimitEntity);
}
