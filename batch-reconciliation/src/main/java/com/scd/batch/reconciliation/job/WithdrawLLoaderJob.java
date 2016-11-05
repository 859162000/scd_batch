package com.scd.batch.reconciliation.job;

import com.google.common.collect.Lists;
import com.scd.batch.common.dao.reconciliation.CashTransferDao;
import com.scd.batch.common.dao.trade.WithdrawLDao;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.trade.WithdrawL;
import com.scd.batch.common.job.batch.DataFlowBatchJob;
import com.scd.batch.common.job.batch.DataFlowCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 取现加载任务
 */
public class WithdrawLLoaderJob extends DataFlowBatchJob {

    @Resource
    private WithdrawLDao withdrawLDao;

    @Override
    protected JobType getJobType() {
        return JobType.CashCalculatorJob;
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
        // Source from DB
        return () -> batchQueryDB(getExecutorContext());
    }

    @Override
    protected DataFlowCalculator getDataFlowCalculator() {
        // No data calculate
        return getNoOpCalculator();
    }

    @Override
    protected TargetDataHandler getTargetDataHandler() {
        // Target save to local file
        return getTargetFileHandler();
    }

    /**
     * Closure: batch query DB
     */
    public List<String> batchQueryDB(ExecutorContext context) {

        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        ShortDate accountDate = context.getAttach(ShortDate.class);

        // 批量获取 ID
        List<Long> batchIds = getBatchIdList(context);

        // 数据已全部查出
        if (batchIds == null) {
            logger.info("batchIds is null!");
            return null;
        }

        Date lastDate = accountDate.addDays(-1).toDate();
        List<WithdrawL> scdList = withdrawLDao.getListByPage(tableSpec,
                lastDate,
                batchIds);

        if (CollectionUtils.isEmpty(scdList)) {
            logger.info("scdList is null!");
            return Lists.newArrayList("");
        }

        return CommonUtil.transform2Lines(scdList);
    }

    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate accountDate = context.getAttach(ShortDate.class);

        return withdrawLDao.getAllTransferIds(tableSpec, accountDate.toDate());
    }
}
