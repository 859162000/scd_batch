package com.scd.batch.common.dao.bid;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.constant.bid.ProjectStatus;
import com.scd.batch.common.entity.statistics.bid.SimpleProjectEntity;
import com.scd.batch.common.utils.TableSpec;
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
public class ProjectDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectDao projectDao;

    @Test
    public void testGetAllIdList() {

        TableSpec tableSpec = TestUtil.getTableSpec();

        List<Long> projectList = projectDao.getAllIdList(tableSpec, ProjectStatus.getValidStatusList());

        Assert.assertEquals(projectList.size(), 1);
    }


    @Test
    public void testGetProjectListById() {

        TableSpec tableSpec = TestUtil.getTableSpec();


        List<SimpleProjectEntity> list = projectDao.getProjectListById(tableSpec,
                TestUtil.buildProjectIdList(),
                ProjectStatus.getValidStatusList());

        Assert.assertEquals(list.size(), 1);
    }

}
