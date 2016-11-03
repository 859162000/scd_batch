package com.scd.batch.reconciliation.job;


import com.scd.batch.common.TestUtil;
import com.scd.batch.common.entity.reconciliation.TransferErrorBase;
import com.scd.batch.common.job.constants.SourceType;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-reconciliation.xml")
public class PaymentCalculatorJobTest {

    @Autowired
    private PaymentCalculatorJob calculatorJob;

    @Autowired
    private PaymentLoaderJob loaderJob;

    @Test
    public void testRconciliaiton() {
        List<String> huifuList = loaderJob.batchQueryDB(TestUtil.buildContext());

        ConcurrentHashMap<String, TransferErrorBase> transferRepo = new ConcurrentHashMap<>();
        calculatorJob.reconciliaiton(SourceType.HUIFU, huifuList, transferRepo);

        Assert.assertEquals(transferRepo.entrySet().size(), 10);
    }

}
