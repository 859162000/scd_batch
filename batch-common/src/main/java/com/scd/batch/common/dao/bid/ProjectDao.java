package com.scd.batch.common.dao.bid;

import com.scd.batch.common.entity.statistics.bid.SimpleProjectEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao {

    // 查询分库分表中的所有ID
    @MultiDB(ds = DataSourceType.BID)
    List<Long> getAllIdList(@Param("ts") TableSpec ts,
                            @Param("statusList") List<Integer> statusList);

    // 查询项目列表
    @MultiDB(ds = DataSourceType.BID)
    List<SimpleProjectEntity> getProjectListById(@Param("ts") TableSpec ts, List<Long> batchProjectId,
                                                 @Param("statusList") List<Integer> statusList);
}