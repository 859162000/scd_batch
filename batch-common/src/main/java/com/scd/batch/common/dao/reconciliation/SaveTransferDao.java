package com.scd.batch.common.dao.reconciliation;

import com.scd.batch.common.entity.reconciliation.SaveTransferEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaveTransferDao {

    @MultiDB(ds = DataSourceType.BATCH)
    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate);

    @MultiDB(ds = DataSourceType.BATCH)
    int batchInsert(@Param("ts") TableSpec ts,
                    @Param("entityList") List<SaveTransferEntity> entityList);

    @MultiDB(ds = DataSourceType.BATCH)
    List<SaveTransferEntity> getListByPage(@Param("ts") TableSpec ts,
                                           @Param("transDate") Date transDate,
                                           @Param("batchIds") List<Long> batchIds);

    @MultiDB(ds = DataSourceType.BATCH)
    int deleteSaveTransfer(@Param("ts") TableSpec ts,
                           @Param("transDate") Date transDate);
}
