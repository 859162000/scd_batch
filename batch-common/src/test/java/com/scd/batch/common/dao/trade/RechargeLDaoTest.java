package com.scd.batch.common.dao.trade;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.dao.statistics.ProjectLimitDao;
import com.scd.batch.common.entity.statistics.ProjectLimitEntity;
import com.scd.batch.common.utils.ShortDate;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
public class RechargeLDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RechargeLDao rechargeLDao;

    @Test
    public void testSelectRechargeSumByDate() {

        Double sum = rechargeLDao.selectRechargeSumByDate(TestUtil.buildTableSpec(),
                TestUtil.buildStatusList(), TestUtil.getStartDate(), TestUtil.getEndDate());
        Assert.assertEquals(sum, 1000.0);
    }

}
