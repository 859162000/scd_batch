/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.DateUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.model.loan.LoanInstallmentInfo;
import com.scd.batch.trade.service.daycut.SwitchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * com.baidu.fbu.fcore.bat.service
 *
 * @author Created by hanxiao01 on 16/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
public class CommonServiceTest {
    @Mock
    private SwitchService switchService;

    @Mock
    private JobControlService jobControlService;

    // @InjectMocks
    // @Resource
    // private CommonService commonService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void grabJobControlTest() {
        ExecutorContext context = new ExecutorContext();
        ShortDate accDate = ShortDate.valueOf("2016-04-19");
        when(switchService.currentAccountDate()).thenReturn(accDate);
        when(jobControlService.getAvailable(any(), any(), any())).thenReturn(new JobControl());
        // commonService.grabJobControl(context, JobType.LOAN, PhaseType.LOAD);

        when(jobControlService.getAvailable(any(), any(), any())).thenReturn(null);
        // commonService.grabJobControl(context, JobType.LOAN, PhaseType.LOAD);
    }

    @Test
    public void loanCalculateTest() {
        Date dueDate = new Date();
        Date graceDate = DateUtils.getNextDateOfTheDay(dueDate, 1);

        List<LoanInstallmentInfo> installments = new ArrayList<LoanInstallmentInfo>() {
            {
                LoanInstallmentInfo installment = new LoanInstallmentInfo();
                installment.setProductId(1L);
                installment.setProductNo(1L);
                installment.setGraceDate(graceDate);
                installment.setDueDate(dueDate);
                installment.setmOverdue(10000);
                add(installment);
            }
        };

        ShortDate accDate = ShortDate.valueOf(DateUtils.getNextDateOfTheDay(dueDate, 2));


    }
}
