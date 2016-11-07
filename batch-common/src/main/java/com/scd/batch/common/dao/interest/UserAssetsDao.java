package com.scd.batch.common.dao.interest;

import com.scd.batch.common.entity.interest.UserAssetsEntity;
import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserAssetsDao {

    @MultiDB(ds = DataSourceType.BATCH)
    UserAssetsEntity selectAssets(@Param("uid") String uid,
                                  @Param("transDate") Date transDate);

    @MultiDB(ds = DataSourceType.BATCH)
    int insert(@Param("entity") UserAssetsEntity entity);

    @MultiDB(ds = DataSourceType.BATCH)
    int update(@Param("entity") UserAssetsEntity entity);

}
