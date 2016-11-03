package com.scd.batch.common.uid.worker.dao;


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
public class WorkerNodeDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WorkerNodeDAO nodeDAO;

    @Test
    public void testGetRelationByProjectIdList() {

        // TODO
    }

    @Test
    public void testGetWorkerNodeByHostPort() {
        // TODO

    }

}
