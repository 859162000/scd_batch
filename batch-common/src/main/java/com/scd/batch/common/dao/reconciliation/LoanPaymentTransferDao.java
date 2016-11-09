package com.scd.batch.common.dao.reconciliation;

import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanPaymentTransferDao {

    @MultiDB(ds = DataSourceType.BATCH)
    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate,
                                 @Param("transferType") int transferType);

    @MultiDB(ds = DataSourceType.BATCH)
    int batchInsert(@Param("ts") TableSpec ts,
                    @Param("entityList") List<LoanPaymentTransferEntity> entityList);

    @MultiDB(ds = DataSourceType.BATCH)
    List<LoanPaymentTransferEntity> getListByPage(@Param("ts") TableSpec ts,
                                                  @Param("transDate") Date transDate,
                                                  @Param("batchIds") List<Long> batchIds,
                                                  @Param("transferType") int transferType);

    int deleteLoanPaymentTransfer(@Param("ts") TableSpec ts,
                                  @Param("transDate") Date transDate,
                                  @Param("transferType") int transferType);
}
