/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service.loan;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.scd.batch.trade.model.loan.ExpediteInfo;
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
@DbUnitConfiguration(databaseConnection = {"h2DataSource"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class LoanExpediteServiceTest {

    @Resource
    private LoanExpediteService loanExpediteService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_expedite_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = {"classpath:META-INF2/data/loan/loan_expedite_service.xml"})
    public void getloanExpediteTest1() {
        TableSpec tableSpec = new TableSpec("0", "0");
        List<Long> ids = loanExpediteService.getAllLoanId(tableSpec);
        List<ExpediteInfo> expediteInfos;
        if (CollectionUtils.isEmpty(ids)) {
            return;
        } else {
            expediteInfos = loanExpediteService.getExpedite(tableSpec, new ShortDate(20160515), ids);
        }

        if (!CollectionUtils.isEmpty(expediteInfos)) {
            ExpediteInfo expediteInfo = expediteInfos.get(0);
            Assert.assertTrue(expediteInfo.getCustomerId() == 1);
            Assert.assertTrue(expediteInfo.getDueDate().equals(ShortDate.valueOf(20160515).toDate()));
            Assert.assertTrue(expediteInfo.getMoney() == 0);
            Assert.assertTrue(expediteInfo.getExpediteDay() == 0);
            Assert.assertTrue(expediteInfo.getProductId() == 1);
        }
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_expedite_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = {"classpath:META-INF2/data/loan/loan_expedite_service.xml"})
    public void getloanExpediteTest2() {
        TableSpec tableSpec = new TableSpec("0", "0");
        List<Long> ids = loanExpediteService.getAllLoanId(tableSpec);
        List<ExpediteInfo> expediteInfos;
        if (CollectionUtils.isEmpty(ids)) {
            return;
        } else {
            expediteInfos = loanExpediteService.getExpedite(tableSpec, new ShortDate(20160512), ids);
        }

        if (!CollectionUtils.isEmpty(expediteInfos)) {
            ExpediteInfo expediteInfo = expediteInfos.get(0);
            Assert.assertTrue(expediteInfo.getCustomerId() == 1);
            Assert.assertTrue(expediteInfo.getDueDate().equals(ShortDate.valueOf(20160515).toDate()));
            Assert.assertTrue(expediteInfo.getMoney() == 0);
            Assert.assertTrue(expediteInfo.getExpediteDay() == 3);
            Assert.assertTrue(expediteInfo.getProductId() == 1);
        }
    }


    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_expedite_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = {"classpath:META-INF2/data/loan/loan_expedite_service.xml"})
    public void getloanExpediteTest3() {
        TableSpec tableSpec = new TableSpec("0", "0");
        List<Long> ids = loanExpediteService.getAllLoanId(tableSpec);
        List<ExpediteInfo> expediteInfos;
        if (CollectionUtils.isEmpty(ids)) {
            return;
        } else {
            expediteInfos = loanExpediteService.getExpedite(tableSpec, new ShortDate(20160522), ids);
        }

        if (!CollectionUtils.isEmpty(expediteInfos)) {
            ExpediteInfo expediteInfo = expediteInfos.get(0);
            Assert.assertTrue(expediteInfo.getCustomerId() == 1);
            Assert.assertTrue(expediteInfo.getDueDate().equals(ShortDate.valueOf(20160515).toDate()));
            Assert.assertTrue(expediteInfo.getMoney() == 0);
            Assert.assertTrue(expediteInfo.getExpediteDay() == -7);
            Assert.assertTrue(expediteInfo.getProductId() == 1);
        }
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_expedite_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = {"classpath:META-INF2/data/loan/loan_expedite_service.xml"})
    public void getloanExpediteTest4() {
        TableSpec tableSpec = new TableSpec("0", "0");
        List<Long> ids = loanExpediteService.getAllLoanId(tableSpec);
        List<ExpediteInfo> expediteInfos;
        if (CollectionUtils.isEmpty(ids)) {
            return;
        } else {
            expediteInfos = loanExpediteService.getExpedite(tableSpec, new ShortDate(20160530), ids);
        }

        if (!CollectionUtils.isEmpty(expediteInfos)) {
            ExpediteInfo expediteInfo = expediteInfos.get(0);
            Assert.assertTrue(expediteInfo.getCustomerId() == 1);
            Assert.assertTrue(expediteInfo.getDueDate().equals(ShortDate.valueOf(20160515).toDate()));
            Assert.assertTrue(expediteInfo.getMoney() == 0);
            Assert.assertTrue(expediteInfo.getExpediteDay() == -15);
            Assert.assertTrue(expediteInfo.getProductId() == 1);
        }
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_expedite_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = {"classpath:META-INF2/data/loan/loan_expedite_service.xml"})
    public void getloanExpediteTest5() {
        TableSpec tableSpec = new TableSpec("0", "0");
        List<Long> ids = loanExpediteService.getAllLoanId(tableSpec);
        List<ExpediteInfo> expediteInfos;
        if (CollectionUtils.isEmpty(ids)) {
            return;
        } else {
            expediteInfos = loanExpediteService.getExpedite(tableSpec, new ShortDate(20160501), ids);
        }

        if (CollectionUtils.isEmpty(expediteInfos)) {
            Assert.assertTrue(expediteInfos.size() == 0);
        }
    }
}
