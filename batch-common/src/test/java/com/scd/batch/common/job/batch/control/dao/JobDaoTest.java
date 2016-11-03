package com.scd.batch.common.job.batch.control.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.scd.batch.common.job.batch.control.JobControl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class})
public class JobDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobDaoTest.class);

    @Autowired
    private JobDao jobDao;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF/dev/job/job.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = {"classpath:META-INF/dev/job/job.xml"})
    public void testGetByUUID() {
        try {
            String uuid = "1";
            JobControl jobControl = jobDao.getByUUID(uuid);
            LOGGER.info("jobControl:" + jobControl.getUuid());
            Assert.assertEquals(uuid, jobControl.getUuid());
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
