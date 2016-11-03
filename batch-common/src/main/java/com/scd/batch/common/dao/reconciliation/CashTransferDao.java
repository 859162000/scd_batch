package com.scd.batch.common.dao.reconciliation;

import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CashTransferDao {

    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate);

    int batchInsert(@Param("ts") TableSpec ts,
                    @Param("entityList") List<CashTransferEntity> entityList);

    List<CashTransferEntity> getListByPage(@Param("ts") TableSpec ts,
                                           @Param("transDate") Date transDate,
                                           @Param("batchIds") List<Long> batchIds);

    int deleteCashTransfer(@Param("ts") TableSpec ts,
                           @Param("transDate") Date transDate);
}
