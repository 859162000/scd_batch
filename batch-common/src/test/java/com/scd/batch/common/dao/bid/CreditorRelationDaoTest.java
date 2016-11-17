package com.scd.batch.common.dao.bid;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.bid.UserCreditroRelationEntity;
import com.scd.batch.common.entity.statistics.bid.FixPlanDuePlanEntity;
import com.scd.batch.common.entity.statistics.bid.FixProductDuePlanEntity;
import com.scd.batch.common.entity.statistics.bid.ProductAssetsEntity;
import com.scd.batch.common.utils.ShortDate;
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
public class CreditorRelationDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreditorRelationDao relationDao;

    @Test
    public void testGetAllIdList() {

        TableSpec tableSpec = TestUtil.getTableSpec();

        List<FixPlanDuePlanEntity> list = relationDao.selectFixPlanDueStatList(tableSpec);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testGetListByQuery() {

        TableSpec tableSpec = TestUtil.getTableSpec();

        List<ProductAssetsEntity> entityList = relationDao.getProductAssetsList(tableSpec, TestUtil.buildIds());

        Assert.assertEquals(entityList.size(), 2);
    }

    @Test
    public void testSelectFixPlanDueStatList() {
        List<FixPlanDuePlanEntity> list = relationDao.selectFixPlanDueStatList(TestUtil.getTableSpec());
        Assert.assertEquals(list.get(0).getFixPlanInterest(), -3222.0);
    }


    @Test
    public void testSelectFixProjectDueStatList() {
        List<FixProductDuePlanEntity> list = relationDao.selectFixProjectDueStatList(TestUtil.getTableSpec());
        Assert.assertEquals(list.get(0).getFixProjectInterest(), -3222.0);
    }

    @Test
    public void testGetUserCreditorRelationList() {
        List<UserCreditroRelationEntity> list = relationDao.getUserCreditorRelationList(
                TestUtil.getTableSpec(),
                ShortDate.today().toDate(),
                TestUtil.buildIds());

        Assert.assertEquals(list.size(), 2);
    }
}
