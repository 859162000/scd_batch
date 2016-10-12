package com.scd.batch.trade.schedule;

import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.batch.control.JobControlService;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.job.loan.LoanLoadDataJob;
import com.scd.batch.trade.service.loan.LoanService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
public class LoanLoadDataJobTest {

    private static final String ROOTPATH = "/tmp";
    private static final String LOAN_FILE_BASE = "LOAN_INSTALLMENT";
    private static final String FEE_FILE_BASE = "LOAN_INSTALLMENT_FEE";

    @Mock
    private JobControlService jobControlService;

    @Mock
    private LoanService loanService;

    // @Mock
    // private CommonService commonService;

    @Mock
    private ExecutorContext context;

    @InjectMocks
    private LoanLoadDataJob loanLoadDataJob;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanLoadDataJob.setName("loanLoadDataJob");
        loanLoadDataJob.setRootPath(ROOTPATH);
        loanLoadDataJob.setBatchSize(5000);
        loanLoadDataJob.setTargetNamePrefix(LOAN_FILE_BASE);
    }

    @Test
    public void beforeExecuteTest() {

        loanLoadDataJob.setBatchSize(5000);

        ExecutorContext context = new ExecutorContext();

        JobControl jobControl = new JobControl();
        jobControl.setDatabaseId("0");
        jobControl.setTableId("0");

        context.addAttach(JobControl.class, jobControl);
        context.addAttach(ShortDate.class, ShortDate.today());

        // PowerMockito.when(commonService.grabJobControl(context, JobType.LOAN, PhaseType.LOAD)).thenReturn(jobControl);
//        when(jobControlService.updatePhaseStatus(any(), eq(PhaseType.CALCULATE), eq(PhaseStatus.INIT)))
//                .thenReturn(1);

        try {
            loanLoadDataJob.start(context);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(ExceptionUtils.getFullStackTrace(e));
        }
    }

}
