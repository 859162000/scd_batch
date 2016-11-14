package com.scd.batch.common.dao.trade;


import com.scd.batch.common.TestUtil;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
public class WithDrawLDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WithdrawLDao withdrawLDao;

    @Test
    public void testSelectRechargeSumByDate() {

        Double sum = withdrawLDao.selectWithdrawSumByDate(TestUtil.buildTableSpec(),
                TestUtil.buildStatusList(), TestUtil.getStartDate(),
                TestUtil.getEndDate(),
                null);

        Assert.assertEquals(sum, 4000.0);
    }

}
