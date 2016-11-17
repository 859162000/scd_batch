package com.scd.batch.common.dao.bid;

import com.scd.batch.common.entity.bid.LoanEntity;
import com.scd.batch.common.entity.bid.ProjectLoanEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanDao {

    @MultiDB(ds = DataSourceType.BID)
    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate);

    @MultiDB(ds = DataSourceType.BID)
    List<LoanEntity> getListByPage(@Param("ts") TableSpec ts,
                                   @Param("transDate") Date transDate,
                                   @Param("batchIds") List<Long> batchIds);

    @MultiDB(ds = DataSourceType.BID)
    List<ProjectLoanEntity> getLoanSumByProjectCodes(@Param("ts") TableSpec ts,
                                                     @Param("status") int status,
                                                     @Param("dataStatus") int dataStatus,
                                                     @Param("projectCodes") List<String> projectCodes);

}