package com.scd.batch.trade.service.loan;


import java.util.List;

import javax.annotation.Resource;

import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.trade.common.LoanUtil;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.service.FailureRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Stopwatch;

@Service
public class LoanUpdateControlService {
    private static final Logger logger = LoggerFactory.getLogger(LoanUpdateControlService.class);

    @Resource
    private LoanUpdateService loanUpdateService;
    @Resource
    private FailureRecordService failureRecordService;

    @Value("${batch.update.size}")
    private int batchUpdateSize = 500;

    public void update(final List<LoanInfo> loanInfoList, final ShortDate accountDate) {
        if (CollectionUtils.isEmpty(loanInfoList)) {
            return;
        }

        final int totalSize = loanInfoList.size();

        final int count = CommonUtil.ceilDiv(totalSize, batchUpdateSize);

        for (int i = 0; i < count; i++) {
            int startIndex = i * batchUpdateSize;
            int endIndex = Math.min(startIndex + batchUpdateSize, totalSize);

            List<LoanInfo> perBatch = loanInfoList.subList(startIndex, endIndex);

            try {
                logger.info("loan update batch by batch, totalSize: {}, count: {}, batchUpdateSize: {}, "
                        + "i: {}, startIndex: {}, endIndex: {}",
                        totalSize, count, batchUpdateSize, i, startIndex, endIndex);

                Stopwatch stopwatch = Stopwatch.createStarted();

                loanUpdateService.updateLoanInfo(perBatch, accountDate);

            } catch (Exception e) {
                logger.error("loan update batch by batch error: {}, e: {}",
                        JsonUtils.toJson(perBatch), ExceptionUtils.getStackTrace(e));

                failureRecordService.addFailRecord(LoanUtil.trans2FailRecord(perBatch, accountDate));
            }
        }

    }
}
