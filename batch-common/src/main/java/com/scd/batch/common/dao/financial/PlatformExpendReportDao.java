package com.scd.batch.common.dao.financial;


import com.scd.batch.common.entity.financial.PlatformExpendReport;
import com.scd.batch.common.entity.financial.ProjectQuotaReport;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PlatformExpendReportDao {

    @MultiDB(ds = DataSourceType.STATISTICS)
    int insert(@Param("entity") PlatformExpendReport entity);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int checkExists(@Param("transDate") Date transDate,
                    @Param("expendDetail") String expendDetail);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int updateIncrement(@Param("entity") PlatformExpendReport entity);
}
