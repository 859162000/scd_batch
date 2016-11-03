package com.scd.batch.common.dao.financial;


import com.scd.batch.common.entity.financial.ProjectQuotaReport;
import com.scd.batch.common.entity.financial.UserRwDailyReport;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProjectQuotaReportDao {

    @MultiDB(ds = DataSourceType.STATISTICS)
    int insert(@Param("entity") ProjectQuotaReport entity);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int checkExists(@Param("transDate") Date transDate,
                    @Param("projectCode") String projectCode);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int updateIncrement(@Param("entity") ProjectQuotaReport entity);

}
