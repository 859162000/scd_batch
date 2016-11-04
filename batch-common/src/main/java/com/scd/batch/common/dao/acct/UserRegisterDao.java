package com.scd.batch.common.dao.acct;

import com.scd.batch.common.mybatis.multidb.DataSourceType;
import com.scd.batch.common.mybatis.multidb.MultiDB;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterDao {
    @MultiDB(ds = DataSourceType.USER)
    long selectCount();
}