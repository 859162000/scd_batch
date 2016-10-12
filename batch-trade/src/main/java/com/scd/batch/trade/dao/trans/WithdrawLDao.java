package com.scd.batch.trade.dao.trans;

import com.scd.batch.common.entity.FundStatEntity;
import com.scd.batch.trade.model.trans.WithdrawL;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository()
public interface WithdrawLDao {

    double selectWithdrawSumByDate(@Param("statusList") String statusList, @Param("startDate") Date
            startDate, @Param("endDate") Date endDate);

}