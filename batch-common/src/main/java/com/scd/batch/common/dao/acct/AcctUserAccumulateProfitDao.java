package com.scd.batch.common.dao.acct;

import com.scd.batch.common.entity.acct.UserAccumulativeProfitEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户累计收益
 */
@Repository
public interface AcctUserAccumulateProfitDao {

    @MultiDB(ds = DataSourceType.USER)
    int insert(@Param("ts") TableSpec ts,
               @Param("entity") UserAccumulativeProfitEntity entity);

    @MultiDB(ds = DataSourceType.USER)
    int checkExists(@Param("ts") TableSpec ts,
                    @Param("uid") String uid);

    @MultiDB(ds = DataSourceType.USER)
    int updateIncrement2DB(@Param("ts") TableSpec ts,
                           @Param("entity") UserAccumulativeProfitEntity entity);


}
