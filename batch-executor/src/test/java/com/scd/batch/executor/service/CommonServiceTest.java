/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.executor.service;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-executor.xml")
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
}
