/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.common.job.batch.control;

import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.utils.IdGenUtil;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
public class JobControlServiceTest {

    @Resource
    private JobControlService jobControlService;

    @Before
    public void setUp() {

    }

    @Test
    public void insertTest() {
        JobControl jobControl = buildJobControll();
        jobControlService.insert(jobControl);
    }

    @Test
    public void insertListTest() {
        List<JobControl> list = new ArrayList<JobControl>() {
            {
                add(buildJobControll());
                add(buildJobControll());
            }
        };

        jobControlService.insertList(list);
    }

    private JobControl buildJobControll() {
        JobControl control = new JobControl();

        control.setPhase(PhaseType.LOAD.type);
        control.setPhaseStatus(PhaseStatus.INIT.type);
        control.setJobType(JobType.CashCalculatorJob.type);

        control.setUuid(String.valueOf(IdGenUtil.get().nextId()));
        control.setRetryTime(0);
        control.setCheckPoint(0);
        control.setAccountDate(ShortDate.today().toDate());
        control.setTableId("1");
        control.setDatabaseId("1");

        return control;
    }

    @Test
    public void insertEmptyListTest() {
        List<JobControl> list = new ArrayList<JobControl>();
        jobControlService.insertList(list);
    }

    @Test
    public void getAvailableTest() {
        ShortDate accDate = ShortDate.today();
        jobControlService.getAvailable(accDate, JobType.CashCalculatorJob, PhaseType.CALCULATE);
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
