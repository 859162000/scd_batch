/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.schedule;

import com.scd.batch.schedule.job.UpdateBankCardQuotaScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-schedule.xml")
public class UpdateBankCardQuotaJobTest {

    @Autowired
    private UpdateBankCardQuotaScheduleJob scheduleJob;


    @Test
    public void testExecute() {
        scheduleJob.start();
    }
}
