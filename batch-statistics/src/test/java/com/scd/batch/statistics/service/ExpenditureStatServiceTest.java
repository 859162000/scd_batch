package com.scd.batch.statistics.service;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.dao.statistics.ExpenditureStatDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-statistics.xml")
public class ExpenditureStatServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ExpenditureStatService statService;

    @Test
    public void testBatchUpdate2DB() {
        statService.batchUpdate2DB(TestUtil.buildExpenditureList());
    }
}
