package com.scd.batch.reconciliation.crawler;


import com.scd.batch.common.TestReconciliationUtil;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.reconciliation.service.TransferBatchUpdateService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-reconciliation.xml")
public class TransferBatchUpdateServiceTest {

    @Autowired
    private TransferBatchUpdateService updateService;

    @Test
    public void testBatchInsertLoanPaymentTransfer() {
        int result = updateService.batchInsertLoanPaymentTransfer(TestReconciliationUtil.buildLoanPaymentTransferList
                ());

        Assert.assertEquals(result, 1);

    }

    @Test
    public void testBatchInsertCashTransfer() {
        int result = updateService.batchInsertCashTransfer(TestReconciliationUtil.buildCashTransferList());

        Assert.assertEquals(result, 1);

    }

    @Test
    public void testDeleteLoanPaymentTransfer() {
        int[] result = updateService.deleteLoanPaymentTransfer(ShortDate.today().toDate(),
                TransferType.LOANS.getType());

        Assert.assertEquals(result[0], 1);
    }
}
