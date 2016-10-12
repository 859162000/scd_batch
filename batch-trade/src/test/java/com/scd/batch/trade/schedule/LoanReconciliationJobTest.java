/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.schedule;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.scd.batch.trade.job.PrepareJob;
import com.scd.batch.trade.job.loan.LoanReconciliationJob;
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
public class LoanReconciliationJobTest {

    @Resource
    private PrepareJob prepareJob;

    @Resource
    private LoanReconciliationJob loanReconciliationJob;

    @Test
    public void getInstallmentInfoTest() {

        // 需要设置JOB UUID 才能打开测试
        // prepareJob.start();

        // loanReconciliationJob.start();
    }

}
