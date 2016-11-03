package com.scd.batch.common.dao.trade;

import com.scd.batch.common.entity.statistics.trade.BalanceAssetsEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceDao {

    // 统计余额
    @MultiDB(ds = DataSourceType.TRADE)
    BalanceAssetsEntity selectBalanceAssets(@Param("ts") TableSpec ts);
}
