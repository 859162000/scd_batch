package com.scd.batch.common.dao.trade;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.statistics.trade.BalanceAssetsEntity;
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
public class UserBalanceDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Test
    public void testSelectRechargeSumByDate() {

        BalanceAssetsEntity entity = userBalanceDao.selectBalanceAssets(TestUtil.buildTableSpec());
        Assert.assertEquals(entity.getUsableSa(), 1000.0);
    }

}
