package com.scd.batch.trade.dao.trans;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository()
public interface RechargeLDao {

    double selectRechargeSumByDate(@Param("statusList") String statusList, @Param("startDate") Date
            startDate, @Param("endDate") Date endDate);

}
