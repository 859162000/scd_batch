/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.schedule;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.job.util.MD5;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.job.loan.LoanCalculateJob;
import com.scd.batch.trade.service.FailureRecordService;
import com.scd.batch.trade.service.loan.LoanService;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.mockito.Matchers.any;

/**
 * com.baidu.fbu.fcore.bat.schedule
 *
 * @author Created by hanxiao01 on 16/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
public class LoanCalculateJobTest {
    Logger logger = LoggerFactory.getLogger(LoanCalculateJobTest.class);
    private static final String rootPath = "/tmp";
    private static final String LOAN_FILE_BASE = "LOAN_INSTALLMENT";
    private static final String FEE_FILE_BASE = "LOAN_INSTALLMENT_FEE";

    @Mock
    private JobControlService jobControlServiceMocked;

    @Mock
    private FailureRecordService failureRecordServiceMocked;
    
    // @Mock
    // private CommonService commonServiceMocked;
    @Mock
    private LoanService loanService ;
    
    @InjectMocks
    private LoanCalculateJob loanCalculateJob = new LoanCalculateJob();

 
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanCalculateJob.setBatchSize(5000);
        loanCalculateJob.setRootPath(rootPath);
        loanCalculateJob.setTargetNamePrefix(FEE_FILE_BASE);
        loanCalculateJob.setSourceNamePrefix(LOAN_FILE_BASE);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void beforeExecuteTest() {
        // when(commonServiceMocked.grabJobControl(any(), eq(JobType.LOAN), eq(PhaseType.CALCULATE)))
        //        .thenReturn(null);
        ExecutorContext context = new ExecutorContext();
        ShortDate accDate = ShortDate.today();
        
        JobControl jobControl = new JobControl();
        jobControl.setDatabaseId("0");
        jobControl.setTableId("0");
        
        context.addAttach(ShortDate.class, accDate);
        context.addAttach(JobControl.class, jobControl);
        
        // loanCalculateJob.start(context);
        loanCalculateJob.beforeExecute(context);

        // when(commonServiceMocked.grabJobControl(any(), eq(JobType.LOAN), eq(PhaseType.CALCULATE)))
        //        .thenReturn(jobControl);
        context = new ExecutorContext();
        context.addAttach(ShortDate.class, accDate);
        context.addAttach(JobControl.class, jobControl);

        loanCalculateJob.start(context);
        boolean ret = loanCalculateJob.beforeExecute(context);
        Assert.assertEquals(ret, false);
    }

    @Test
    public void afterExecuteTest() {
        ExecutorContext context = new ExecutorContext();
        context.addAttach(ShortDate.class, ShortDate.today());
        JobControl jobControl = new JobControl();
        jobControl.setDatabaseId("0");
        jobControl.setTableId("0");
        
        context.addAttach(JobControl.class, jobControl);
        jobControl.setUuid("xxxx");
        
        // when(commonServiceMocked.grabJobControl(any(), eq(JobType.LOAN), eq(PhaseType.CALCULATE)))
        // .thenReturn(jobControl);
        
        loanCalculateJob.start(context);
        // loanCalculateJob.afterExecute(context);
    }

    @Test
    public void queryLinesTest() {
        // 准备运行环境
        ExecutorContext context = new ExecutorContext();
        ShortDate accDate = ShortDate.valueOf("2016-04-19");
        context.addAttach(ShortDate.class, accDate);
        JobControl control = new JobControl();
        control.setDatabaseId("0");
        control.setTableId("0");
        context.addAttach(JobControl.class, control);

        // 准备测试文件
        // prepareCalcFiles(context);
        
        JobControl jobControl = new JobControl();
        jobControl.setDatabaseId("0");
        jobControl.setTableId("0");
//         when(commonServiceMocked.grabJobControl(any(), eq(JobType.LOAN), eq(PhaseType.CALCULATE)))
//         .thenReturn(jobControl);
   
//        Mockito.doAnswer(new Answer<Object>() {
//            public Object answer(InvocationOnMock invocation) {
//                Object[] args = invocation.getArguments();
//                return "called with arguments: " + args;
//            }
//        }).when(failureRecordServiceMocked); // .addLoanFailureRecord(any(), any());

//        when(loanService.loanCalculate(any(), any())).thenReturn(new LoanInfo());
       
//        loanCalculateJob.start(context);
//        loanCalculateJob.beforeExecute(context);
//        loanCalculateJob.execute(context);
//        File md5File = new File("/tmp/2016-04-19/LOAN_INSTALLMENT_0_0.md5");
//        md5File.delete();
    }

    private void generateMD5(File f) throws Exception {

        if (! f.exists()) {
            logger.warn("file: {} does not exist", f);
            f.createNewFile();
            return;
        }
        String md5Value = MD5.generateMD5(f);

        File md5File = new File(f.getParent(), f.getName() + ".md5");

        FileUtils.write(md5File, md5Value, "UTF-8");
    }
}
