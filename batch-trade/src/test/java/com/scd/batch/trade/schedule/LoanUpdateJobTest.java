/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.schedule;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.job.loan.LoanUpdateJob;
import com.scd.batch.trade.service.daycut.SwitchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * com.baidu.fbu.fcore.bat.schedule
 *
 * @author Created by hanxiao01 on 16/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
public class LoanUpdateJobTest {

    @Mock
    private SwitchService switchServiceMocked;

    // @Mock
    // private CommonService commonServiceMocked;

    @InjectMocks
    @Resource
    private LoanUpdateJob loanUpdateJob;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void beforeExecuteTest() {
        ExecutorContext context = new ExecutorContext();
        ShortDate accDate = ShortDate.valueOf("2016-04-19");
        JobControl control = new JobControl();
        control.setDatabaseId("0");
        control.setTableId("0");
        context.addAttach(ShortDate.class, accDate);
        context.addAttach(JobControl.class, control);
        
        // loanUpdateJob.start(context);

        // 测试可用job
        // when(commonServiceMocked.grabJobControl(any(), eq(JobType.LOAN), eq(PhaseType.UPDATE)))
        //         .thenReturn(control);
        // loanUpdateJob.beforeExecute(context);

        // 测试无可用job
        // when(commonServiceMocked.grabJobControl(any(), eq(JobType.LOAN), eq(PhaseType.UPDATE)))
        //        .thenReturn(null);
        // loanUpdateJob.beforeExecute(context);
    }

    @Test
    public void afterExecuteTest() {
        ExecutorContext context = new ExecutorContext();
        ShortDate accDate = ShortDate.valueOf("2016-04-19");
        JobControl control = new JobControl();
        control.setDatabaseId("0");
        control.setTableId("0");
        context.addAttach(ShortDate.class, accDate);
        context.addAttach(JobControl.class, control);
        
        // loanUpdateJob.start(context);
    }

    @Test
    public void executeTest() {

    }
}
