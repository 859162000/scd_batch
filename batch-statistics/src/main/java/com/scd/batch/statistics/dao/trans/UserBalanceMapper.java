package com.scd.batch.statistics.dao.trans;

import com.scd.batch.common.entity.trade.UserBalance;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserBalanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBalance record);

    int insertSelective(UserBalance record);

    UserBalance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBalance record);

    int updateByPrimaryKey(UserBalance record);

    //for recharge callBack
    int updateRechargeInfo(Map condition);

    int updateWithdrawFailInfo(Map condition);

    UserBalance selectByUserIDOperateType(Map condition);

    int updateWithdrawFreeze(Map condition);

    int updateWithdrawCallbackSucess(Map condition);

    int updateWithdrawCallbackFail(Map condition);
}
