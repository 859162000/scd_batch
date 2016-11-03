package com.scd.batch.common.dao.statistics;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.statistics.FixDueStatEntity;
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
public class FixProjectDueStatDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FixProjectDueStatDao statDao;

    @Test
    public void testGetList() {

        List<FixDueStatEntity> list = statDao.getStatList(TestUtil.buildTableSpec(), 0, 10);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testCheckExists() {
        int result = statDao.checkExists(TestUtil.buildTableSpec(), ShortDate.today().toDate());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void testInsert() {

        int result = statDao.insert(TestUtil.getTableSpec(), TestUtil.buildFixDueStatEntity());
        Assert.assertEquals(result, 1);
    }

    @Test
    public void testUpdate2DB() {
        int result = statDao.update2DB(TestUtil.getTableSpec(), TestUtil.buildFixDueStatEntity());

        Assert.assertEquals(result, 1);
    }

}
