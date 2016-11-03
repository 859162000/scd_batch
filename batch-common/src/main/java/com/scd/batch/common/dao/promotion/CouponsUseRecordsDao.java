package com.scd.batch.common.dao.promotion;

import com.scd.batch.common.entity.statistics.ExpenditureStatEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("couponsUseRecords")
public interface CouponsUseRecordsDao {

    // 按天统计支出汇总
    @MultiDB(ds = DataSourceType.PROMOTION)
    List<ExpenditureStatEntity> selectStatList(@Param("ts") TableSpec ts, @Param("useTimeStart") Date useTimeStart
            , @Param("useTimeEnd") Date useTimeEnd);

}