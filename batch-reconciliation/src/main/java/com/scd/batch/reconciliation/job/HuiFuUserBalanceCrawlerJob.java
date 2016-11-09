package com.scd.batch.reconciliation.job;

import com.scd.batch.common.dao.acct.UserRegisterDao;
import com.scd.batch.common.dao.reconciliation.HuiFuUserBalanceDao;
import com.scd.batch.common.dao.trade.UserBalanceDao;
import com.scd.batch.common.entity.reconciliation.HuiFuUserBalanceEntity;
import com.scd.batch.common.entity.reconciliation.ScdUserBalanceEntity;
import com.scd.batch.common.job.batch.CrawlerDataFlowBatchJob;
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
import com.scd.batch.reconciliation.crawler.UserBalanceCrawler;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 余额对账任务
 */
public class HuiFuUserBalanceCrawlerJob extends CrawlerDataFlowBatchJob {

    @Autowired
    private UserBalanceCrawler crawler;

    @Autowired
    private HuiFuUserBalanceDao balanceDao;

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Autowired
    private UserRegisterDao registerDao;

    @Override
    protected JobType getJobType() {
        return JobType.HuiFuUserBalanceCrawlerJob;
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

        List<ScdUserBalanceEntity> userList = userBalanceDao.getActiveUIDs(TableSpec.getDefault(), transDate.toDate());

        // 提取uid
        List<String> userIdList = userList.stream().map(p -> p.getUid()).collect(Collectors.toList());

        // uid to acctId
        List<String> acctList = registerDao.getAcctListByUIdList(userIdList);

        List<HuiFuUserBalanceEntity> entityList = crawler.crawler(transDate, acctList);

        logger.info("entityList.size:" + entityList.size());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }

        // 对余额进行对账
        reconciliation(entityList, userList);

        // batch insert DB
        update2DB(TableSpec.getDefault(), entityList);

        return CommonUtil.transform2Lines(entityList);
    }

    // 写入数据库，存在则更新
    private void update2DB(TableSpec ts, List<HuiFuUserBalanceEntity> list) {

        for (HuiFuUserBalanceEntity balance : list) {
            if (balanceDao.checkExists(ts, balance.getUserCustId()) > 0) {
                balanceDao.update(ts, balance);
            } else {
                balanceDao.insert(ts, balance);
            }
        }
    }

    // TODO 对账
    private void reconciliation(List<HuiFuUserBalanceEntity> entityList, List<ScdUserBalanceEntity> userList) {
        ;
    }

}
