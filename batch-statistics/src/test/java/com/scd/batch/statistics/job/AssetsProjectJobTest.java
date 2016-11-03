package com.scd.batch.statistics.job;


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
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-statistics.xml")
public class AssetsProjectJobTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AssetsStatProjectCalculateJob calculateJob;

    @Test
    public void testBatchQueryDB() {
        String line = calculateJob.batchQueryDB(TestUtil.buildContext());
        Assert.assertNotNull(line);
    }

    @Test
    public void testUpdate2DB() {

        String line = calculateJob.batchQueryDB(TestUtil.buildContext());
        calculateJob.update2DB(line);
    }
}
