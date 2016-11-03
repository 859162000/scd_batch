package com.scd.batch.common.dao.acct;


import com.scd.batch.common.TestUtil;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
@ActiveProfiles("h2")
public class UserDailyProfitDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDailyProfitDao profitDao;

    @Test
    public void testInsert() {

        int result = profitDao.insert(TestUtil.getTableSpec(),
                TestUtil.buildUserDailyEntity());

        Assert.assertEquals(result, 1);
    }

}
