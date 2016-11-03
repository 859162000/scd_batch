package com.scd.batch.executor.schedule;

import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.job.SwitchJob;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-executor.xml")
public class SwitchJobTest {
    @Mock
    private SwitchService switchServiceMoked;

    @InjectMocks

    private SwitchJob switchJob;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSwitchJob() {
        ShortDate curDate = ShortDate.today();
        // ShortDate nextDate = curDate.addDays(1);
        when(switchServiceMoked.currentAccountDate()).thenReturn(curDate);
        // when(switchServiceMoked.cutAccountDate(any())).thenReturn(nextDate);
        ExecutorContext executorContext = new ExecutorContext();

        switchJob.initialize();
        switchJob.execute(executorContext);

        switchJob.shutdown();
    }
}
