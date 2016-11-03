package com.scd.batch.reconciliation.job;

import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.dao.reconciliation.LoanPaymentTransferDao;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.job.batch.*;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.*;
import com.scd.batch.reconciliation.crawler.LoanPaymentCrawler;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 从汇付爬取放款对账数据
 */
public class LoanCrawlerJob extends CrawlerDataFlowBatchJob {

    @Resource
    private LoanPaymentCrawler crawler;

    @Resource
    private LoanPaymentTransferDao transferDao;

    @Override
    protected JobType getJobType() {
        return JobType.LoanCrawlerJob;
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
    public List<String> batchRead(ExecutorContext context) {

        Pagination pagination = context.getAttach(Pagination.class);
        // 业务日期
        ShortDate transDate = context.getAttach(ShortDate.class);

        List<LoanPaymentTransferEntity> transferEntityList = crawler.crawler(transDate,
                TransferType.LOANS,
                pagination);

        logger.info("transferEntityList.size():" + transferEntityList.size());

        if (transferEntityList == null || CollectionUtils.isEmpty(transferEntityList)) {
            logger.info("transferEntityList is empty!");
            return null;
        }

        if (isDeleteFlag()) {
            // 清理当天的旧数据
            transferDao.deleteLoanPaymentTransfer(TableSpec.getDefault(),
                    transDate.toDate(),
                    TransferType.LOANS.getType());
            setDeleteFlag(false);
        }

        // batch insert DB
        transferDao.batchInsert(TableSpec.getDefault(), transferEntityList);

        logger.info("transferEntityList:" + JsonUtils.toJson(transferEntityList));

        return CommonUtil.transform2Lines(transferEntityList);
    }

}
