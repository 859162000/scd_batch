package com.scd.batch.common.dao.statistics;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.statistics.ProjectLimitEntity;
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
public class ProjectLimitDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectLimitDao projectLimitDao;

    @Test
    public void testGetList() {

        List<ProjectLimitEntity> list = projectLimitDao.getList(TestUtil.buildTableSpec(), 0, 10);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testInsert() {

        int result = projectLimitDao.insert(TestUtil.getTableSpec(), TestUtil.buildProjectLimit());
        Assert.assertEquals(result, 100);
    }

}
