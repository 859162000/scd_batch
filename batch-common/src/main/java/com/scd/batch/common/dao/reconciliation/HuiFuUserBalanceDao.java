package com.scd.batch.common.dao.reconciliation;

import com.scd.batch.common.entity.reconciliation.HuiFuUserBalanceEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuiFuUserBalanceDao {

    @MultiDB(ds = DataSourceType.BATCH)
    List<Long> getAllIds(@Param("ts") TableSpec ts);

    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("ts") TableSpec ts,
               @Param("entity") HuiFuUserBalanceEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    int update(@Param("ts") TableSpec ts,
               @Param("entity") HuiFuUserBalanceEntity entity);


    @MultiDB(ds = DataSourceType.BATCH)
    int checkExists(@Param("ts") TableSpec ts,
                    @Param("custId") String custId);
}
