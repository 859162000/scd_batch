package com.scd.batch.common.dao.reconciliation;

import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanPaymentTransferDao {

    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate,
                                 @Param("transferType") int transferType);

    int batchInsert(@Param("ts") TableSpec ts,
                    @Param("entityList") List<LoanPaymentTransferEntity> entityList);

    List<LoanPaymentTransferEntity> getListByPage(@Param("ts") TableSpec ts,
                                                  @Param("transDate") Date transDate,
                                                  @Param("batchIds") List<Long> batchIds,
                                                  @Param("transferType") int transferType);

    int deleteLoanPaymentTransfer(@Param("ts") TableSpec ts,
                                  @Param("transDate") Date transDate,
                                  @Param("transferType") int transferType);
}
