package com.scd.batch.executor.service.daycut;

import com.scd.batch.common.daycut.dao.DayCutDao;
import com.scd.batch.common.daycut.entity.DayCutInfo;
import com.scd.batch.common.daycut.exception.DayCutException;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.job.batch.control.dao.SwitchDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class SwitchServiceImpl implements SwitchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchServiceImpl.class);

    /**
     * 默认日切时间间隔大于 23 小时
     */
    @Value("${switch.interval}")
    private int minChangeInterval = 23 * 60 * 60;


    @Resource
    private DayCutDao dayCutDao;

    @Resource
    private SwitchDao switchDao;

    /**
     * 日切: 将账务时间 +1 天
     *
     * @param oldAccountDate 给定原来的账务时间, 将账务时间 +1 天,
     *                       originalAccountDate 必须和库里的未切之前的账务时间相同, 否则返回当前日切时间
     */
    @Override
    @Transactional
    public void cutAccountDate(ShortDate oldAccountDate) {
        checkNotNull(oldAccountDate, "oldAccountDate");

        try {
            DayCutInfo oldInfo = dayCutDao.loadDate();

            // 日切
            switchDao.addWithOld(oldAccountDate.toDate(), 1, minChangeInterval);

            switchDao.addHistory(oldInfo);

            LOGGER.info("oldAccountDate.toDate():" + oldAccountDate.toDate() + ", minChangeInterval:" +
                    minChangeInterval);
        } catch (Exception e) {
            LOGGER.error("cutAccountDate error", e);
            throw new DayCutException("cutAccountDate error", e);
        }
    }

    /**
     * 获取当前账务日期
     */
    @Override
    @Transactional
    public ShortDate currentAccountDate() {
        DayCutInfo dayCutInfo = dayCutDao.loadDate();
        return ShortDate.valueOf(dayCutInfo.getAccountDate());
    }
}
