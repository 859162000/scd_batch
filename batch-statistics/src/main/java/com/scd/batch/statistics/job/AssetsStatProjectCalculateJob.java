package com.scd.batch.statistics.job;

import com.scd.batch.common.constant.bid.ProductType;
import com.scd.batch.common.dao.bid.CreditorRelationDao;
import com.scd.batch.common.entity.statistics.AssetsStatEntity;
import com.scd.batch.common.entity.statistics.bid.ProductAssetsEntity;
import com.scd.batch.common.job.batch.StatisticsCalculateJob;
import com.scd.batch.common.job.batch.StatisticsCalculator;
import com.scd.batch.common.job.batch.TargetStatisticsHandler;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.statistics.service.AssetsStatService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 按照产品类型统计资金规模任务
 */
public class AssetsStatProjectCalculateJob extends StatisticsCalculateJob {

    @Resource
    private AssetsStatService statService;

    @Resource
    private CreditorRelationDao creditorRelationDao;

    @Override
    protected JobType getJobType() {
        return JobType.AssetsStatProjectCalculateJob;
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
    protected TargetStatisticsHandler getTargetStatisticsHandler() {
        return this::update2DB;
    }

    @Override
    protected StatisticsCalculator getStatisticsCalculator() {
        return () -> batchQueryDB(getExecutorContext());
    }

    /**
     * Closure: batch query DB
     */
    public String batchQueryDB(ExecutorContext context) {
        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        ShortDate accountDate = context.getAttach(ShortDate.class);

        // 批量获取 ID
        List<Long> batchId = getBatchIdList(context);

        List<ProductAssetsEntity> productAssetsList = creditorRelationDao.getProductAssetsList(tableSpec, batchId);
        if (productAssetsList == null || productAssetsList.isEmpty()) {
            logger.info("productAssetsList isEmpty!");
            return null;
        }

        AssetsStatEntity assetsStatEntity = buildAssets(accountDate.toDate(), productAssetsList);

        return JsonUtils.toJson(assetsStatEntity);
    }

    /**
     * 统计结果增量方式，更新到数据库
     */
    public void update2DB(String line) {
        AssetsStatEntity assetsStatEntity = JsonUtils.toBean(line, AssetsStatEntity.class);

        // 不存在则先写入
        statService.updateIncreament2DB(assetsStatEntity);
    }

    @Override
    public List<Long> getAllIdList(ExecutorContext context) {
        // Get partition info from context
        TableSpec tableSpec = context.getAttach(TableSpec.class);

        return creditorRelationDao.getAllIdList(tableSpec);
    }

    public AssetsStatEntity buildAssets(Date transDate, List<ProductAssetsEntity> productAssetsList) {

        AssetsStatEntity entity = new AssetsStatEntity(transDate, 0, 0, 0, 0, 0, 0);

        // 三种产品的资金规模
        productAssetsList.forEach(p -> {
            if (p.getProductType() == ProductType.CURRENT.getCode()) {
                entity.setCurrent(p.getAssets());
            } else if (p.getProductType() == ProductType.FIX_PLAN.getCode()) {
                entity.setFixPlan(p.getAssets());
            } else if (p.getProductType() == ProductType.FIX_PROJECT.getCode()) {
                entity.setFixProject(p.getAssets());
            } else {
                logger.warn("invalid p.getProductType():" + p.getProductType());
            }
        });

        return entity;
    }

}
