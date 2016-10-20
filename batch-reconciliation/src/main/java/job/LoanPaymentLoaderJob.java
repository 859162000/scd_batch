package job;

import com.google.common.collect.Lists;
import com.scd.batch.common.job.batch.DataFlowBatchJob;
import com.scd.batch.common.job.batch.DataFlowCalculator;
import com.scd.batch.common.job.batch.SourceDataProvider;
import com.scd.batch.common.job.batch.TargetDataHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.CommonUtil;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import org.apache.commons.collections.CollectionUtils;
import service.crawler.LoanPaymentCrawlerImpl;

import javax.annotation.Resource;
import java.util.List;

public class LoanPaymentLoaderJob extends DataFlowBatchJob {

    @Resource
    private LoanPaymentCrawlerImpl crawler;

    @Override
    protected JobType getJobType() {
        return JobType.LOAN;
    }

    @Override
    protected PhaseType getCurrentPhase() {
        return PhaseType.LOAD;
    }

    @Override
    protected PhaseType getNextPhase() {
        return PhaseType.CALCULATE;
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
    private List<String> batchQueryDB(ExecutorContext context) {

        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        ShortDate accountDate = context.getAttach(ShortDate.class);

        // 批量获取账号 ID
        List<Long> batchLoanId = getBatchIdList(context);

        // 数据已全部查出
        if (batchLoanId == null) {
            return null;
        }

        Pagination pagination = new Pagination();
        List<LoanPaymentTransferEntity> transferEntityList = crawler.crawler(TransferType.LOANS, pagination);

        if (CollectionUtils.isEmpty(transferEntityList)) {
            return Lists.newArrayList("");
        }

        return CommonUtil.transform2Lines(transferEntityList);
    }

    @Override
    protected List<Long> getAllIdList(ExecutorContext context) {
        TableSpec tableSpec = context.getAttach(TableSpec.class);
        ShortDate accountDate = context.getAttach(ShortDate.class);

        // TODO
        return null;
//        return loanService.getAllOverdueLoanId(tableSpec, accountDate);
    }
}
