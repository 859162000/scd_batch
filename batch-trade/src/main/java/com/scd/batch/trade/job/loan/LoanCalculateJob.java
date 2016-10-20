package com.scd.batch.trade.job.loan;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.scd.batch.common.job.batch.DataFlowBatchJob;
import com.scd.batch.common.job.batch.DataFlowCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.service.loan.LoanService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.google.common.collect.Lists;

public class LoanCalculateJob extends DataFlowBatchJob {
    @Resource
    protected LoanService loanService;

    @Override
    protected JobType getJobType() {
        return JobType.LOAN;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.CALCULATE;
    }

    @Override
    protected PhaseType getNextPhase() {
        return PhaseType.UPDATE;
    }

    @Override
    protected SourceDataProvider getSourceDataProvider() {
        // Read local source file
        return getSourceFileProvider();
    }

    @Override
    protected DataFlowCalculator getDataFlowCalculator() {
        // Calculate with source data lines
        return this::queryLines;
    }

    @Override
    protected TargetDataHandler getTargetDataHandler() {
        // Target save to local file
        return getTargetFileHandler();
    }

    private List<String> queryLines(List<String> sourceLines) {
        List<LoanInfo> loanInfoList = CommonUtil.transform2Bean(sourceLines, LoanInfo.class);

        ShortDate accountDate = getExecutorContext().getAttach(ShortDate.class);

        loanInfoList.parallelStream().forEach(loanInfo -> {
            try {
                loanService.loanCalculate(loanInfo, accountDate);
            } catch (Exception e) {
                loanInfo.setFlag((byte) 1);
                // 此时需要报警, 计算出错没必要记录差错表重试, 重试还会失败
                logger.error("error loanCalculate: loanId: {}, loanInfo: {}, e: {}",
                        loanInfo.getLoanId(), JsonUtils.toJson(loanInfo), ExceptionUtils.getStackTrace(e));
            }
        });

        List<LoanInfo> calculated = loanInfoList.stream()
                .filter(e -> e.getFlag() == 0).collect(Collectors.toList());

        if (!isEmpty(loanInfoList) && isEmpty(calculated)) {
            return Lists.newArrayList("");
        }

        return CommonUtil.transform2Lines(calculated);
    }
}
