/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.scd.batch.trade.service;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.trade.model.loan.LoanInstallmentInfo;
import com.scd.batch.trade.service.loan.LoanUpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * com.baidu.fbu.fcore.bat.service
 *
 * @author Created by hanxiao01 on 16/4/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF2/spring.xml")
public class LoanUpdateServiceTest {

    @Resource
    private LoanUpdateService loanUpdateService;

    @Test
    public void updateLoanInstallmentsTest() {
        List<LoanInstallmentInfo> installments = new ArrayList<LoanInstallmentInfo>() {
            {
                add (new LoanInstallmentInfo());
                add (new LoanInstallmentInfo());
            }
        };

        TableSpec tableSpec = new TableSpec("0", "0");
        ShortDate accDate = ShortDate.today();

        // loanUpdateService.updateLoanInstallments(installments, accDate, tableSpec);
    }

}
