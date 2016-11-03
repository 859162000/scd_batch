package com.scd.batch.interest.service;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.interest.TestUtilInterest;
import com.scd.batch.interest.entity.UserProfitEntity;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-interest.xml")
public class UserProfitCalculateServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserProfitCalculateService calculateService;

    @Test
    public void testCSalculateProfit() {

        List<UserProfitEntity> list = calculateService.calculateProfit(TableSpec.getDefault(),
                ShortDate.today().toDate(),
                TestUtil.buildIds());

        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void testUpdate2DB() {
        calculateService.update2DB(TestUtilInterest.buildUserProfitList());
    }

}
