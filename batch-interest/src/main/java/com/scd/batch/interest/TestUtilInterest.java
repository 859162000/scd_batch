package com.scd.batch.interest;


import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.interest.entity.UserProfitEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwei on 2016-10-25.
 */
public class TestUtilInterest {

    public static UserProfitEntity buildUserProfitEntity() {
        UserProfitEntity entity = new UserProfitEntity();
        entity.setUid("uid");
        entity.setDate(ShortDate.today().toDate());
        entity.setProfit(new BigDecimal(1132));
        entity.setCurrentProfit(new BigDecimal(2111));
        entity.setTotalProfit(new BigDecimal(222));
        entity.setCurrentInvestProfit(new BigDecimal(111));

        return entity;
    }

    public static List<UserProfitEntity> buildUserProfitList() {
        List<UserProfitEntity> list = new ArrayList<>();
        list.add(buildUserProfitEntity());
        list.add(buildUserProfitEntity());
        list.add(buildUserProfitEntity());
        list.add(buildUserProfitEntity());
        list.add(buildUserProfitEntity());
        list.add(buildUserProfitEntity());
        list.add(buildUserProfitEntity());

        return list;
    }

}
