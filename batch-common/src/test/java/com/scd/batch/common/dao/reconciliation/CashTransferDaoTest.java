package com.scd.batch.common.dao.reconciliation;


import com.scd.batch.common.TestReconciliationUtil;
import com.scd.batch.common.utils.TableSpec;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
public class CashTransferDaoTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CashTransferDao transferDao;

    /**
     * 批量插入
     */
    @Test
    public void testBatchInsert() {

        int result = transferDao.batchInsert(TableSpec.getDefault(),
                TestReconciliationUtil.buildCashTransferList());

        Assert.assertEquals(result, 13);
    }

}
