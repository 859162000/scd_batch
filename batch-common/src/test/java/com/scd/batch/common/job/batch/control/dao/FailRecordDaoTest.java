package com.scd.batch.common.job.batch.control.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.scd.batch.common.constant.executor.FailureStatus;
import com.scd.batch.common.utils.ShortDate;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class FailRecordDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FailRecordDaoTest.class);


    @Autowired
    private FailRecordDao recordDao;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF/data/executor/failRecord.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = {"classpath:META-INF/data/executor/failRecord.xml"})
    public void testSelectFailureId() {
        try {
            List<Long> list = recordDao.selectFailureId(
                    ShortDate.today().toDate(),
                    FailureStatus.RETRY_FAIL.type);

            Assert.assertEquals(list.size(), 1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
