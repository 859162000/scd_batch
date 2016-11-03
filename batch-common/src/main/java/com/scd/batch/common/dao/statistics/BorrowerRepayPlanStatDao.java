package com.scd.batch.common.dao.statistics;

import com.scd.batch.common.entity.statistics.BorrowerRepayPlanStatEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowerRepayPlanStatDao {

    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("ts") TableSpec ts,
               @Param("entity") BorrowerRepayPlanStatEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    int update2DB(@Param("ts") TableSpec ts,
                  @Param("entity") BorrowerRepayPlanStatEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    int checkExists(@Param("ts") TableSpec ts,
                    @Param("dueDate") Date dueDate,
                    @Param("borrowerId") long borrowerId,
                    @Param("projectCode") String projectCode);

    @MultiDB(ds = DataSourceType.BATCH)
    List<BorrowerRepayPlanStatEntity> getStatList(@Param("ts") TableSpec ts,
                                                  @Param("start") long start,
                                                  @Param("num") long num);
}
