package com.scd.batch.reconciliation.job;


import com.scd.batch.common.TestUtil;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-reconciliation.xml")
public class CashLoaderJobTest {

    @Autowired
    private CashLoaderJob loaderJob;

    @Test
    public void testBatchRead() {
        List<String> list = loaderJob.batchQueryDB(TestUtil.buildContext());
        Assert.assertEquals(list.size(), 10);
    }

    @Test
    public void testExecute(){
        loaderJob.start(TestUtil.buildContext());
    }

}
