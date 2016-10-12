/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service;

import com.scd.batch.common.utils.ShortDate;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.scd.batch.trade.common.FailureStatus;
import com.scd.batch.trade.model.FailRecordEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * com.baidu.fbu.fcore.bat.service
 *
 * @author Created by hanxiao01 on 16/4/28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
@DbUnitConfiguration(databaseConnection = { "h2DataSource"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class FailureRecordServiceTest {

    @Resource
    private FailureRecordService failureRecordService;

    @Before
    public void setUp() {
        return;
    }
    
    @Test
    public void addFailRecordListTest () {
        List<FailRecordEntity> list = new ArrayList<FailRecordEntity>();
        FailRecordEntity entity = new FailRecordEntity();
        entity.setCustomerId(1);
        entity.setAccountId(1);
        entity.setStatus(FailureStatus.INIT.type);
        entity.setAccountDate(new Date());
        entity.setCreated(new Date());
        entity.setFailureId(1);
        entity.setId(1);
        entity.setModified(new Date());
        entity.setPartitionId(1);
        list.add(entity);
        failureRecordService.getAllFailedId(new ShortDate());
    }
}
