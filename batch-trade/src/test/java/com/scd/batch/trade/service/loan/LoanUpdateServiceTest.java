/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service.loan;

import com.scd.batch.common.utils.ShortDate;
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
import java.util.Date;
import java.util.List;

/**
 * com.baidu.fbu.fcore.bat.service
 *
 * @author Created by hanxiao01 on 16/4/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
@DbUnitConfiguration(databaseConnection = { "h2DataSource"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class LoanUpdateServiceTest {

    @Resource
    private LoanUpdateService loanUpdateService;

    @Test
    public void updateLoanInfoTest() {
        List<LoanInfo> installments = new ArrayList<LoanInfo>() {
            {
                add (new LoanInfo());
                add (new LoanInfo());
            }
        };

        ShortDate accDate = ShortDate.today();

        loanUpdateService.updateLoanInfo(installments, accDate);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT,
            value = "classpath:META-INF2/data/loan/loan_update_service.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE,
            value = { "classpath:META-INF2/data/loan/loan_update_service.xml" })
    public void updateLoanInfoTest2() {
        List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
        ShortDate accDate = ShortDate.today();
        LoanInfo loanInfo = new LoanInfo();
        loanInfo.setLoanId(1);
        loanInfo.setCustomerId(1);
        loanInfo.setAccountId(2);
        loanInfo.setVersion(0);

        List<InstallmentInfo> installmentInfos = new ArrayList<InstallmentInfo>();
        InstallmentInfo installmentInfo = new InstallmentInfo();
        installmentInfo.setStatus(1);
        installmentInfo.setCustomerId(1);
        installmentInfo.setAccountId(2);
        installmentInfo.setCharges(1);
        installmentInfo.setChargesRepay(1);
        installmentInfo.setDueDate(new Date());
        installmentInfo.setGraceDate(new Date());
        installmentInfo.setInstallmentMadeNo(1);
        installmentInfo.setInterest(1);
        installmentInfo.setInterestRepay(1);
        installmentInfo.setLoanId(1);
        installmentInfo.setManagement(1);
        installmentInfo.setManagementRepay(1);
        installmentInfo.setOverdue(0);
        installmentInfo.setOverdueDelta(0);
        installmentInfo.setOverdueRepay(0);
        installmentInfo.setPenalty(0);
        installmentInfo.setPenaltyDelta(0);
        installmentInfo.setPenaltyRepay(0);
        installmentInfo.setPrincipal(0);
        installmentInfo.setPenaltyDelta(0);
        installmentInfo.setPrincipalRepay(0);
        installmentInfo.setScheduleNo(1);
        installmentInfo.setStatus(1);
        installmentInfo.setService(0);
        installmentInfo.setServiceRepay(0);
        installmentInfo.setViolate(0);
        installmentInfo.setViolateRepay(0);
        installmentInfos.add(installmentInfo);
        loanInfo.setInstallments(installmentInfos);
        loanInfos.add(loanInfo);


        loanUpdateService.updateLoanInfo(loanInfos, accDate);
    }
}
