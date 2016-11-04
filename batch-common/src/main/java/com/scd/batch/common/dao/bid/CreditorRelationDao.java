package com.scd.batch.common.dao.bid;

import com.scd.batch.common.entity.bid.UserCreditroRelationEntity;
import com.scd.batch.common.entity.statistics.bid.FixPlanDuePlanEntity;
import com.scd.batch.common.entity.statistics.bid.FixProductDuePlanEntity;
import com.scd.batch.common.entity.statistics.bid.ProductAssetsEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import com.scd.batch.common.utils.TableSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 债权关系
 */
@Repository
public interface CreditorRelationDao {

    @MultiDB(ds = DataSourceType.BID)
    List<Long> getAllIdList(@Param("ts") TableSpec ts);

    // 获取资产规模列表
    @MultiDB(ds = DataSourceType.BID)
    List<ProductAssetsEntity> getProductAssetsList(@Param("ts") TableSpec ts,
                                                   @Param("batchId") List<Long> batchId);

    // 获取债权关系列表
    @MultiDB(ds = DataSourceType.BID)
    List<UserCreditroRelationEntity> getUserCreditorRelationList(@Param("ts") TableSpec ts,
                                                                 @Param("batchId") List<Long> batchId);


    // 产品到期计划
    @MultiDB(ds = DataSourceType.BID)
    List<FixPlanDuePlanEntity> selectFixPlanDueStatList(@Param("ts") TableSpec ts);

    // 定期产品到期计划
    @MultiDB(ds = DataSourceType.BID)
    List<FixProductDuePlanEntity> selectFixProjectDueStatList(@Param("ts") TableSpec ts);

}