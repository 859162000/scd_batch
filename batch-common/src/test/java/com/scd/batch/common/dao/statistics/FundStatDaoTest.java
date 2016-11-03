package com.scd.batch.common.dao.statistics;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.statistics.FundStatEntity;
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
public class FundStatDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FundStatDao statDao;

    @Test
    public void testGetList() {

        List<FundStatEntity> list = statDao.getStatList(TestUtil.buildTableSpec(), 0, 10);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testInsert() {

        int result = statDao.insert(TestUtil.getTableSpec(), TestUtil.buildFundStat());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void testFindByOption() {
        int result = statDao.checkExists(TestUtil.getTableSpec(), ShortDate.today().toDate());

        Assert.assertEquals(result , 1);
    }

    @Test
    public void testUpdateIncrement() {
        int result = statDao.updateIncrement(TestUtil.getTableSpec(), TestUtil.buildFundStat());
        Assert.assertEquals(result, 1);
    }

}
