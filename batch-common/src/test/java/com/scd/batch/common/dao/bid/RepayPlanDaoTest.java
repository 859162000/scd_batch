package com.scd.batch.common.dao.bid;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.statistics.bid.BorrowerRepayEntity;
import com.scd.batch.common.entity.statistics.bid.RepayPlanStatEntity;
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
public class RepayPlanDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RepayPlanDao repayPlanDao;

    @Test
    public void testGetProjectRepayPlanListByTerm() {

        List<BorrowerRepayEntity> repayEntityList = repayPlanDao.getProjectRepayPlanListByTerm(TestUtil.getTableSpec(),
                TestUtil.buildProjectCodeList());

        Assert.assertEquals(repayEntityList.size(), 1);
    }

    @Test
    public void testGetProjectLimitList() {
        List<RepayPlanStatEntity> list = repayPlanDao.getProjectLimitList(TestUtil.getTableSpec(), TestUtil
                .buildProjectCodeList());
        Assert.assertEquals(list.size(), 1);
    }

}
