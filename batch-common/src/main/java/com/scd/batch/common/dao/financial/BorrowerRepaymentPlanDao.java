package com.scd.batch.common.dao.financial;


import com.scd.batch.common.entity.financial.BorrowerRepaymentPlan;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BorrowerRepaymentPlanDao {

    @MultiDB(ds = DataSourceType.STATISTICS)
    int insert(@Param("entity") BorrowerRepaymentPlan entity);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int checkExists(@Param("transDate") Date transDate,
                    @Param("projectCode") String projectCode);

    @MultiDB(ds = DataSourceType.STATISTICS)
    int updateIncrement(@Param("entity") BorrowerRepaymentPlan entity);


}
