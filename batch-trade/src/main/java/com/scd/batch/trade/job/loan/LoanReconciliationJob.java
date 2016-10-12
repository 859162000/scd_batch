package com.scd.batch.trade.job.loan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.scd.batch.common.job.batch.DataFlowBatchJob;
import com.scd.batch.common.job.batch.DataFlowCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
import com.scd.batch.common.job.batch.TargetFileWriteHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.scd.batch.trade.common.CommonUtil;
import com.scd.batch.trade.model.loan.VOA;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class LoanReconciliationJob extends DataFlowBatchJob {

    private String rootPath;

    @Override
    protected JobType getJobType() {
        return JobType.LOAN_RECONCILIATION;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.LOAD;
    }

    @Override
    protected PhaseType getNextPhase() {
        return null;
    }

    @Override
    protected SourceDataProvider getSourceDataProvider() {
        return () -> batchQueryDB(getExecutorContext());
    }

    // 此JOB的此方法需要特别特别注意
    // 文件夹目录默认按照T日生生，但是对账特殊，T日产出T-1日的对账文件
    // 所以需要重写而不是直接用父类的方法
    @Override
    protected TargetDataHandler getTargetDataHandler() {
        // Target save to local file
        // 目录日期设置为T-1
        File targetDir = new File(this.rootPath,
                ((ShortDate) this.getExecutorContext().getAttach(ShortDate.class).addDays(-1)).toString());
        logger.debug("targetDir = {}", targetDir.getAbsolutePath().toString());
        String targetFileName = (String) this.getExecutorContext().getAttach(ATTACH_KEY_TARGET_FILE_NAME);
        return new TargetFileWriteHandler(targetDir, targetFileName);
    }

    @Override
    protected DataFlowCalculator getDataFlowCalculator() {
        // No data calculate
        return getNoOpCalculator();
    }

    /**
     * Closure: batch query DB
     */
    private List<String> batchQueryDB(ExecutorContext context) {
        Pagination pagination = context.getAttach(Pagination.class);
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate shortDate = context.getAttach(ShortDate.class);
        List<String> reqIdList = getReqIdList(context);
        if (reqIdList == null) {
            return null;
        }
        List<VOA> voas = null;
        // 为空时为了继续查询
        if (CollectionUtils.isEmpty(voas)) {
            return Lists.newArrayList("");
        }
        return CommonUtil.joinerFormBean(voas);

    }

    private List<String> getReqIdList(ExecutorContext context) {
        if (context.getAttach("TEMP_PARAM") == null) {
            Object allId = this.getAllReqIdList(context);
            if (allId == null) {
                allId = new ArrayList();
            }

            context.addAttach("TEMP_PARAM", allId);
            this.logger.info("getBatchIdList size: {}", Integer.valueOf(((List) allId).size()));
        }

        List allId1 = (List) context.getAttach("TEMP_PARAM");
        int accountNumber = allId1.size();
        Pagination pagination = (Pagination) context.getAttach(Pagination.class);
        int startInclusive = pagination.getStartIndex();
        int endExclusive = Math.min(startInclusive + pagination.getPageSize(), accountNumber);
        return startInclusive >= accountNumber ? null : allId1.subList(startInclusive, endExclusive);
    }

    private List<String> getAllReqIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate shortDate = context.getAttach(ShortDate.class);
        return null;
    }

    public void setRootPath(String rootPath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(rootPath), "missed rootPath");
        this.rootPath = StringUtils.trim(rootPath);
    }
}
