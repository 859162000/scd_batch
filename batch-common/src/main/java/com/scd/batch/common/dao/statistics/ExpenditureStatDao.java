package com.scd.batch.common.dao.statistics;

import com.scd.batch.common.entity.statistics.ExpenditureStatEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenditureStatDao {

    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("ts") TableSpec ts, @Param("entity") ExpenditureStatEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    int update2DB(@Param("ts") TableSpec ts, @Param("entity") ExpenditureStatEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    int checkExists(@Param("ts") TableSpec ts,
                    @Param("transDate") Date transDate,
                    @Param("type") int type);

    @MultiDB(ds = DataSourceType.BATCH)
    List<ExpenditureStatEntity> getStatList(@Param("ts") TableSpec ts, @Param("start") long start, @Param("num") long
            num);
}
