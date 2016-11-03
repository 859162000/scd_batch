package com.scd.batch.common.dao.trade;

import com.scd.batch.common.entity.trade.LoansCb;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository()
public interface LoansCbDao {

    @MultiDB(ds = DataSourceType.TRADE)
    List<Long> getAllTransferIds(@Param("ts") TableSpec ts,
                                 @Param("transDate") Date transDate);

    @MultiDB(ds = DataSourceType.TRADE)
    List<LoansCb> getListByPage(@Param("ts") TableSpec ts,
                                @Param("transDate") Date transDate,
                                @Param("batchIds") List<Long> batchIds);

}