package com.scd.batch.common.dao.bid;

import com.scd.batch.common.entity.bid.CreditRepayReal;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditRepayRealDao {


    @MultiDB(ds = DataSourceType.BID)
    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate);

    @MultiDB(ds = DataSourceType.BID)
    List<CreditRepayReal> getListByPage(@Param("ts") TableSpec ts,
                                        @Param("transDate") Date transDate,
                                        @Param("batchIds") List<Long> batchIds);

    @MultiDB(ds = DataSourceType.BID)
    double repayInterestAmountByDay(@Param("ts") TableSpec ts,
                                    @Param("repayDate") Date batchrepayDateIds,
                                    @Param("uid") String uid);

}