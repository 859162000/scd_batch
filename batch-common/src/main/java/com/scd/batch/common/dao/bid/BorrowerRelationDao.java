package com.scd.batch.common.dao.bid;

import com.scd.batch.common.entity.statistics.bid.ProjectBorrowerEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowerRelationDao {

    // 获取项目信息
    @MultiDB(ds = DataSourceType.BID)
    List<ProjectBorrowerEntity> getRelationByProjectIdList(@Param("ts") TableSpec ts,
                                                           @Param("batchProjectCode")
                                                                   List<String> batchProjectCode);
}