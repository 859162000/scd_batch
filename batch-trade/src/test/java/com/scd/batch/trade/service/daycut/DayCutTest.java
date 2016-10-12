package com.scd.batch.trade.service.daycut;

import com.scd.batch.common.daycut.dao.DayCutDao;
import com.scd.batch.common.daycut.entity.DayCutInfo;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.dao.SwitchDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SwitchServiceImpl.class)
public class DayCutTest {
    
    /**
     * 默认日切时间间隔大于 23 小时
     */
    private static final int MIN_CHANGE_INTERVAL = 23 * 60 * 60;
    
    @Mock
    private DayCutDao dayCutDao;
    @Mock
    private SwitchDAO switchDAO;
    
    @InjectMocks
    private SwitchServiceImpl switchService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void cutAccountDateTest () {
        ShortDate accDate = ShortDate.valueOf("2016-03-02");
        ShortDate accDate2 = ShortDate.valueOf("2016-03-03");
        DayCutInfo dayCutInfo = new DayCutInfo();
        
        DayCutInfo dayCutInfo2 = new DayCutInfo();
        dayCutInfo2.setAccountDate(new Date());
        
        PowerMockito.when(dayCutDao.load()).thenReturn(dayCutInfo);
        PowerMockito.when(switchDAO.addWithOld(accDate.toDate(), 1, MIN_CHANGE_INTERVAL)).thenReturn(0);
        PowerMockito.when(dayCutDao.load()).thenReturn(dayCutInfo2);
        PowerMockito.when(switchDAO.addHistory(dayCutInfo)).thenReturn(1);
        switchService.cutAccountDate(accDate);
        
        PowerMockito.when(switchDAO.addWithOld(accDate2.toDate(), 1, MIN_CHANGE_INTERVAL)).thenReturn(1);
        switchService.cutAccountDate(accDate2);
    }
    
    @Test
    public void currentAccountDateTest () {
        DayCutInfo dayCutInfo  = new DayCutInfo();
        dayCutInfo.setAccountDate(new Date());
        dayCutInfo.setDayCutCreated(new Date());
        dayCutInfo.setDayCutModified(new Date());
        dayCutInfo.setId(1);
        PowerMockito.when(dayCutDao.load()).thenReturn(dayCutInfo);
    }
    
}
