/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.scd.batch.trade.common.FailureStatus;
import com.scd.batch.trade.model.FailRecordEntity;
import com.scd.batch.trade.model.loan.LoanInstallmentInfo;
import com.scd.batch.trade.service.loan.LoanService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.baidu.fbu.fcore.bat.service
 *
 * @author Created by hanxiao01 on 16/4/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
@DbUnitConfiguration(databaseConnection = { "h2DataSource"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class LoanServiceTest {

    @Resource
    private LoanService loanService;


    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = { "classpath:META-INF2/data/loan/loan_service.xml" })
    public void getAllOverdueLoanIdTest() {
        TableSpec tableSpec = new TableSpec("0", "0");
        ShortDate accDate = ShortDate.today();
        List<Long> ids = null;
        ids = loanService.getAllOverdueLoanId(tableSpec, accDate);

        accDate = ShortDate.valueOf(20160618);
        ids =  loanService.getAllOverdueLoanId(tableSpec, accDate);
        if (!CollectionUtils.isEmpty(ids)) {
            Assert.assertTrue(ids.size() == 1);
        }
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = { "classpath:META-INF2/data/loan/loan_service.xml" })
    public void getInstallmentInfoTest() {
        TableSpec tableSpec = new TableSpec("0", "0");
        ShortDate accDate = ShortDate.today();
        List<Long> ids = null;
        ids = loanService.getAllOverdueLoanId(tableSpec, accDate);

        accDate = ShortDate.valueOf(20160618);
        ids =  loanService.getAllOverdueLoanId(tableSpec, accDate);
        if (!CollectionUtils.isEmpty(ids)) {
            Assert.assertTrue(ids.size() == 1);
        }
        List<LoanInstallmentInfo>  loanInstallmentInfos = loanService.getInstallmentInfo(tableSpec, ids, accDate);
        if (!CollectionUtils.isEmpty(loanInstallmentInfos)) {
            Assert.assertTrue(loanInstallmentInfos.size() == 1);
        }
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = { "classpath:META-INF2/data/loan/loan_service.xml" })
    public void retryLoanFailRecordTest() {
        FailRecordEntity failRecord = new FailRecordEntity();
        failRecord.setStatus(FailureStatus.RETRY_SUCC.type);

        failRecord.setStatus(FailureStatus.RETRY_FAIL.type);
        failRecord.setAccountId(2);
        failRecord.setCustomerId(1);
        failRecord.setAccountDate(ShortDate.valueOf(20160407).toDate());
        failRecord.setFailureId(1);

        // loanService.retryLoanFailRecord(failRecord, ShortDate.valueOf(20160601));



    }
}
