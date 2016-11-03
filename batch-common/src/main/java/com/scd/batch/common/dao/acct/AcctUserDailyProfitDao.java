package com.scd.batch.common.dao.acct;

import com.scd.batch.common.entity.acct.UserDailyProfitEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 用户累计收益
 */
@Repository
public interface AcctUserDailyProfitDao {

    @MultiDB(ds = DataSourceType.USER)
    int insert(@Param("ts") TableSpec ts,
               @Param("entity") UserDailyProfitEntity entity);

    @MultiDB(ds = DataSourceType.USER)
    int checkExists(@Param("ts") TableSpec ts,
                    @Param("uid") String uid,
                    @Param("date") String date);

    @MultiDB(ds = DataSourceType.USER)
    int updateIncrement2DB(@Param("ts") TableSpec ts,
                           @Param("entity") UserDailyProfitEntity entity);
}
