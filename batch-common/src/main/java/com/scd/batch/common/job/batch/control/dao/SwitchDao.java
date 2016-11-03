package com.scd.batch.common.job.batch.control.dao;

import java.util.Date;

import com.scd.batch.common.daycut.entity.DayCutInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchDao {
    /**
     * base on day cut date in db,add some time by the params.
     *
     * @param oldAccountDate with it to check DayCutTime.
     * @param days +day
     * @param minChangeInterval min interval from last edit
     */
//    @OptimisticLock(expect = 1)
    int addWithOld(@Param("oldAccountDate") Date oldAccountDate, @Param("days") int days,
                   @Param("minChangeInterval") int minChangeInterval);

    /**
     * save day cut object history to db
     *
     * @param dayCutInfo old entity
     */
    int addHistory(@Param("dayCutInfo") DayCutInfo dayCutInfo);
}
