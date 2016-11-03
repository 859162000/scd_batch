package com.scd.batch.common.dao.financial;


import com.scd.batch.common.entity.financial.UserRwDailyReport;
import com.scd.batch.common.entity.statistics.FundStatEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserRwDailyReportDao {

    @MultiDB(ds = DataSourceType.STATISTICS)
    int insert(@Param("entity") UserRwDailyReport entity);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int checkExists(@Param("transDate") Date transDate);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int updateIncrement(@Param("entity") UserRwDailyReport entity);


}
