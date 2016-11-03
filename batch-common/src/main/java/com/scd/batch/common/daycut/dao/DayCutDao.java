package com.scd.batch.common.daycut.dao;

import com.scd.batch.common.daycut.entity.DayCutInfo;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import org.springframework.stereotype.Repository;

@Repository
public interface DayCutDao {

    /**
     * 获取当前账务日期
     */
    @MultiDB(ds = DataSourceType.BATCH)
    DayCutInfo loadDate();

}