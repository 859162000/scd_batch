package com.scd.batch.reconciliation.crawler;


import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-reconciliation.xml")
public class LoanPaymentCrawlerTest {

    @Autowired
    private LoanPaymentCrawler crawlerJob;


    @Test
    public void testBatchReadLoans() {
        ShortDate transDate = ShortDate.valueOf("2016-11-02");
        Pagination pagination = new Pagination();
        pagination.setPageSize(1000);
        pagination.setCurPage(1);
        List<LoanPaymentTransferEntity> list = crawlerJob.crawler(transDate, TransferType.LOANS, pagination);
        Assert.assertEquals(10, list.size());
    }


    @Test
    public void testBatchReadPayment() {
        ShortDate transDate = ShortDate.valueOf("2016-11-02");
        Pagination pagination = new Pagination();
        pagination.setPageSize(1000);
        pagination.setCurPage(1);
        List<LoanPaymentTransferEntity> list = crawlerJob.crawler(transDate, TransferType.REPAYMENT, pagination);
        Assert.assertEquals(10, list.size());
    }

}
