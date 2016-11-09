package com.scd.batch.common.dao.acct;

import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegisterDao {

    @MultiDB(ds = DataSourceType.USER)
    long selectCount();

    @MultiDB(ds = DataSourceType.USER)
    List<String> getAcctListByUIdList(@Param("uidList") List<String> uidList);
}