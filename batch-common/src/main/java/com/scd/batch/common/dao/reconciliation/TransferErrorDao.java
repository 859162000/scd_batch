package com.scd.batch.common.dao.reconciliation;

import com.scd.batch.common.entity.reconciliation.TransferErrorEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferErrorDao {

    @MultiDB(ds = DataSourceType.BATCH)
    int batchInsert(@Param("ts") TableSpec ts,
                    @Param("entityList") List<TransferErrorEntity> entityList);

    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("ts") TableSpec ts,
               @Param("entity") TransferErrorEntity entity);

}
