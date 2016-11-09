package com.scd.batch.reconciliation.util;


import com.miaoqian.api.dto.ReconciliationDto;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransferUtilTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TransferUtil util;

    @Test
    public void testBuildLoanRepaymentTransfer() {
        List<LoanPaymentTransferEntity> list = util.buildLoanPaymentTransfer(new Date(),
                TransferType.LOANS,
                buildReconciliationDtoList());
        Assert.assertEquals(list.size(), 4);

        LoanPaymentTransferEntity transfer = list.get(0);
        Assert.assertEquals(transfer.getBorrCustId(), "borrCustId");

        logger.info("borrCustId");
    }


    public static List<ReconciliationDto> buildReconciliationDtoList() {
        List<ReconciliationDto> list = new ArrayList<>();
        list.add(buildReconciliationDto());
        list.add(buildReconciliationDto());
        list.add(buildReconciliationDto());
        list.add(buildReconciliationDto());

        return list;
    }

    public static ReconciliationDto buildReconciliationDto() {
        ReconciliationDto dto = new ReconciliationDto();
        dto.setBorrCustId("borrCustId");
        dto.setInterestAmt("100");
        dto.setOrdDate("2016-01-01 00:00:00");
        dto.setMerCustId("mercust");

        return dto;
    }

}
