package com.scd.batch.trade.dao;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.dao.JobDAO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
@DbUnitConfiguration(databaseConnection = {"dbUnitDatabaseConnection"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class JobDaoTest {

    @Autowired
    private JobDAO jobDAO;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/job/job.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE, value = {"classpath:META-INF2/data/job/job.xml"})
    public void testGetByUUID() {
        try {
            String uuid = "1";
            JobControl jobControl = jobDAO.getByUUID(uuid);
            Assert.assertTrue(jobControl.getUuid().equals(uuid));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
