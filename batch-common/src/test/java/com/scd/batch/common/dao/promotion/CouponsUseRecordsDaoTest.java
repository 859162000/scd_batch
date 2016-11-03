package com.scd.batch.common.dao.promotion;


import com.scd.batch.common.entity.statistics.ExpenditureStatEntity;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
public class CouponsUseRecordsDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CouponsUseRecordsDao couponsUseRecordsDao;

    /**
     * 测试卡支出情况
     */
    @Test
    public void testSelectStatList() {

        TableSpec ts = new TableSpec("0", "1");

        Date useTimeStart = ShortDate.today().addDays(-1).toDate();

        Date useTimeEnd = ShortDate.today().toDate();

        List<ExpenditureStatEntity> entityList = couponsUseRecordsDao.selectStatList(ts, useTimeStart, useTimeEnd);

        logger.info("entityList.size():" + entityList.size());

        Assert.assertEquals(entityList.size(), 1);
        Assert.assertEquals(entityList.get(0).getAmount(), 66.0);
        Assert.assertEquals(entityList.get(0).getType(), 0);
    }


}
