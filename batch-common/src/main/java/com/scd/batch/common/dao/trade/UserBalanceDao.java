package com.scd.batch.common.dao.trade;

import com.scd.batch.common.entity.reconciliation.ScdUserBalanceEntity;
import com.scd.batch.common.entity.statistics.trade.BalanceAssetsEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserBalanceDao {


    @MultiDB(ds = DataSourceType.TRADE)
    List<Long> getAllIds(@Param("ts") TableSpec ts);

    // 查找当日有动账的账户
    @MultiDB(ds = DataSourceType.TRADE)
    List<ScdUserBalanceEntity> getActiveUIDs(@Param("ts") TableSpec ts,
                                             @Param("transDate") Date transDate);

    // 统计余额
    @MultiDB(ds = DataSourceType.TRADE)
    BalanceAssetsEntity selectBalanceAssets(@Param("ts") TableSpec ts);


    // 统计余额
    @MultiDB(ds = DataSourceType.TRADE)
    List<BalanceAssetsEntity> selectBalanceByBatchUid(@Param("ts") TableSpec ts,
                                                      @Param("transDate") Date transDate,
                                                      @Param("batchIds") List<Long> batchIds);


}
