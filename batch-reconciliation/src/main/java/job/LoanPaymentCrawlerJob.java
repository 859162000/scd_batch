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
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import org.apache.commons.collections.CollectionUtils;
import service.TransferBatchUpdateImpl;
import service.crawler.LoanPaymentCrawlerImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * 从汇付爬取放还款对账数据
 * <p>
 * 写入数据库，并生成对账文件
 */
public class LoanPaymentCrawlerJob extends DataFlowBatchJob {

    @Resource
    private LoanPaymentCrawlerImpl crawler;

    @Resource
    private TransferBatchUpdateImpl batchUpdate;

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
        // Source from HF
        return () -> batchRead(getExecutorContext());
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
     * Closure: batch crawl from HuiFu
     */
    private List<String> batchRead(ExecutorContext context) {

        Pagination pagination = context.getAttach(Pagination.class);
        List<LoanPaymentTransferEntity> transferEntityList = crawler.crawler(TransferType.LOANS, pagination);

        if (CollectionUtils.isEmpty(transferEntityList)) {
            return Lists.newArrayList("");
        }

        // batch insert DB
        batchUpdate.batchInsertLoanPaymentTransfer(transferEntityList);

        return CommonUtil.transform2Lines(transferEntityList);
    }

}
