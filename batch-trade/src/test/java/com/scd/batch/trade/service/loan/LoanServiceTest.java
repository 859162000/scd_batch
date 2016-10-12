/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service.loan;

import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

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
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "classpath:META-INF2/data/loan/loan_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE, value = { "classpath:META-INF2/data/loan/loan_service.xml" })
    public void getInstallmentInfoTest() {
        TableSpec tableSpec = new TableSpec("0", "0");
        ShortDate accDate = new ShortDate(20160520);
        Pagination pagination = new Pagination();
        pagination.setPageSize(500);
        pagination.setCurPage(1);
        // List<LoanInstallmentInfo>  infos = loanService.getInstallmentInfo(tableSpec, accDate, pagination);
        // System.out.println(infos.size());
        
    }

}
