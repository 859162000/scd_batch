package com.scd.batch.trade.service.daycut;


import com.scd.batch.common.utils.ShortDate;

public interface SwitchService {

    /**
     * 日切: 将账务时间 +1 天
     * 
     * @param oldAccountDate 给定原来的账务时间, 将账务时间 +1 天,
     *                        oldAccountDate 必须和库里的未切之前的账务时间相同, 否则返回当前日切时间
     */
    void cutAccountDate(ShortDate oldAccountDate);

    /**
     * 获取当前账务日期
     * @return 当前账务日期
     */
    ShortDate currentAccountDate();

}
