/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service.loan;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.model.loan.LoanInfo;
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
import java.util.List;

/**
 * com.baidu.fbu.fcore.bat.service
 *
 * @author Created by hanxiao01 on 16/4/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
@DbUnitConfiguration(databaseConnection = { "h2DataSource"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class LoanUpdateControlServiceTest {

    @Resource
    private LoanUpdateControlService loanUpdateControlService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_update_control_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = { "classpath:META-INF2/data/loan/loan_update_control_service.xml" })
    public void  updateLoanTest() {
        TableSpec tableSpec = new TableSpec("0", "0");
        ShortDate accDate = new ShortDate(20160520);

        List<LoanInfo> loanInfoList = null;
        loanUpdateControlService.update(loanInfoList, accDate);
        loanInfoList = new ArrayList<LoanInfo>();
        loanUpdateControlService.update(loanInfoList, accDate);

        LoanInfo loanInfo = new LoanInfo();
        loanInfo.setLoanId(1);
        loanInfo.setCustomerId(1);
        loanInfo.setAccountId(2);
        loanInfo.setVersion(0);
        loanInfo.setInstallments(new ArrayList<InstallmentInfo>());
        loanInfoList.add(loanInfo);
        loanUpdateControlService.update(loanInfoList, accDate);
        
    }

}
