package com.scd.batch.common.daycut.dao;

import com.scd.batch.common.daycut.entity.DayCutInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface DayCutDao {

    /**
     * 获取当前账务日期
     */
    DayCutInfo load();

}