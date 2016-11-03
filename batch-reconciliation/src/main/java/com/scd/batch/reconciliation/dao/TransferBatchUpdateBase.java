package com.scd.batch.reconciliation.dao;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public abstract class TransferBatchUpdateBase {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "batchSqlSession")
    private SqlSession batchSession;

    protected SqlSession getBatchSession() {
        return batchSession;
    }

    protected <T> T getDAO(Class<T> type) {
        return batchSession.getMapper(type);
    }

    protected int[] flush2UpdateCounts0() {
        return getBatchSession().flushStatements().get(0).getUpdateCounts();
    }

    protected List<BatchResult> flushStatements() {

        return getBatchSession().flushStatements();
    }

}
