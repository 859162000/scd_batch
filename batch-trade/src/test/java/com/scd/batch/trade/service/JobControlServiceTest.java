/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.utils.ShortDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * com.baidu.fbu.fcore.bat.service
 *
 * @author Created by hanxiao01 on 16/4/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
public class JobControlServiceTest {

    @Resource
    private JobControlService jobControlService;

    @Before
    public void setUp() {

    }

    @Test
    public void insertTest() {
        JobControl jobControl = new JobControl();
        jobControl.setAccountDate(ShortDate.today().toDate());
        jobControl.setCreated(new Date());
        jobControl.setModified(new Date());
        jobControl.setCheckPoint(1);
        jobControl.setEndTime(new Date());
//        jobControlService.insert(jobControl);
    }

    @Test
    public void insertListTest() {
        List<JobControl> list = new ArrayList<JobControl>() {
            {
                add(new JobControl());
                add(new JobControl());
            }
        };

//        jobControlService.insertList(list);
    }

    @Test
    public void insertEmptyListTest() {
        List<JobControl> list = new ArrayList<JobControl>();
//        jobControlService.insertList(list);
    }
    
    @Test
    public void getAvailableTest() {
        ShortDate accDate = ShortDate.today();
        // jobControlService.getAvailable(accDate, JobType.LOAN, PhaseType.CALCULATE);
    }

    @Test
    public void updatePhaseStatusTest1() {
        jobControlService.updatePhaseStatus("", PhaseType.CALCULATE, PhaseStatus.INIT);
    }
    
    @Test
    public void updatePhaseStatusTest2() {
        jobControlService.updatePhaseStatus("", PhaseType.CALCULATE, PhaseStatus.FAIL);
    }
    
    @Test
    public void updatePhaseStatusTest3() {
        jobControlService.updatePhaseStatus("", PhaseType.CALCULATE, PhaseStatus.PROCESSING);
    }
    
    @Test
    public void updatePhaseStatusTest4() {
        jobControlService.updatePhaseStatus("", PhaseType.CALCULATE, PhaseStatus.DONE);
    }
}
