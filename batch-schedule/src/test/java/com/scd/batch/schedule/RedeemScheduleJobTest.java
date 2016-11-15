/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.schedule;

import com.scd.batch.common.utils.Settings;
import com.scd.batch.schedule.job.RedeemScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-schedule.xml")
public class RedeemScheduleJobTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedeemScheduleJob scheduleJob;


    @Test
    public void testExecute() {
        scheduleJob.start();
    }

    @Test
    public void testRedeem() {
        logger.error("bidRetry:" + Settings.getInstance().getBidLoanRetry());
    }
}
