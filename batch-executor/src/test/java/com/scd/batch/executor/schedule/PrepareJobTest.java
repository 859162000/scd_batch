/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.executor.schedule;

import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.job.PrepareJob;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-executor.xml")
public class PrepareJobTest {
    @Mock
    private SwitchService switchServiceMocked;

    @Mock
    private JobControlService jobControlServiceMocked;

    @InjectMocks
    private PrepareJob prepareJob = new PrepareJob(1, 1, 1, 1);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeTest() {
        ShortDate accDate = ShortDate.today();
        when(switchServiceMocked.currentAccountDate()).thenReturn(accDate);
//        when(jobControlServiceMocked.insertList(any())).thenReturn(1);
        prepareJob.init();
        prepareJob.execute(new ExecutorContext());
    }
}
