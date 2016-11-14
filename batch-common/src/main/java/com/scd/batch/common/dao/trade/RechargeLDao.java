package com.scd.batch.common.dao.trade;

import com.scd.batch.common.entity.trade.RechargeL;
import com.scd.batch.common.entity.trade.WithdrawL;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RechargeLDao {

    @MultiDB(ds = DataSourceType.TRADE)
    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate);

    @MultiDB(ds = DataSourceType.TRADE)
    List<RechargeL> getListByPage(@Param("ts") TableSpec ts,
                                  @Param("transDate") Date transDate,
                                  @Param("batchIds") List<Long> batchIds);

    @MultiDB(ds = DataSourceType.TRADE)
    Double selectRechargeSumByDate(@Param("ts") TableSpec ts,
                                   @Param("statusList") List<String> statusList,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   @Param("uid") String uid);

}
