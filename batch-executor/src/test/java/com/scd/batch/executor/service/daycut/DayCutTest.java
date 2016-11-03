package com.scd.batch.executor.service.daycut;

import com.alibaba.druid.filter.AutoLoad;
import com.scd.batch.common.daycut.dao.DayCutDao;
import com.scd.batch.common.daycut.entity.DayCutInfo;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.job.batch.control.dao.SwitchDao;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@PrepareForTest(SwitchServiceImpl.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-executor.xml")
public class DayCutTest {

    /**
     * 默认日切时间间隔大于 23 小时
     */
    private static final int MIN_CHANGE_INTERVAL = 23 * 60 * 60;

    @Mock
    private DayCutDao dayCutDao;
    @Mock
    private SwitchDao switchDao;

    @Resource
    private SwitchService switchService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cutAccountDateTest() {
        ShortDate accDate = ShortDate.valueOf("2016-11-01");
//        ShortDate accDate2 = ShortDate.valueOf("2016-03-03");
        DayCutInfo dayCutInfo = new DayCutInfo();

        DayCutInfo dayCutInfo2 = new DayCutInfo();
        dayCutInfo2.setAccountDate(new Date());

//        PowerMockito.when(dayCutDao.loadDate()).thenReturn(dayCutInfo);
//        PowerMockito.when(switchDao.addWithOld(accDate.toDate(), 1, MIN_CHANGE_INTERVAL)).thenReturn(0);
//        PowerMockito.when(dayCutDao.loadDate()).thenReturn(dayCutInfo2);
//        PowerMockito.when(switchDao.addHistory(dayCutInfo)).thenReturn(1);
        switchService.cutAccountDate(accDate);

//        PowerMockito.when(switchDao.addWithOld(accDate2.toDate(), 1, MIN_CHANGE_INTERVAL)).thenReturn(1);
//        switchService.cutAccountDate(accDate2);
    }

    @Test
    public void currentAccountDateTest() {
        DayCutInfo dayCutInfo = new DayCutInfo();
        dayCutInfo.setAccountDate(new Date());
        dayCutInfo.setDayCutCreated(new Date());
        dayCutInfo.setDayCutModified(new Date());
        dayCutInfo.setId(1);
        PowerMockito.when(dayCutDao.loadDate()).thenReturn(dayCutInfo);
    }

}
