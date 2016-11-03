package com.scd.batch.common.dao.bid;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.statistics.bid.ProjectBorrowerEntity;
import com.scd.batch.common.entity.statistics.bid.SimpleProjectEntity;
import com.scd.batch.common.utils.TableSpec;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
@ActiveProfiles({"h2"})
public class BorrowerRelationDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BorrowerRelationDao relationDao;

    @Test
    public void testGetRelationByProjectIdList() {

        TableSpec tableSpec = TestUtil.getTableSpec();

        List<ProjectBorrowerEntity> list = relationDao.getRelationByProjectIdList(tableSpec, TestUtil
                .buildProjectCodeList
                        ());

        Assert.assertEquals(list.size(), 1);
    }


}
