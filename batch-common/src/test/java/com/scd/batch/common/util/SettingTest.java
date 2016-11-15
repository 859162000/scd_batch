package com.scd.batch.common.util;

import com.scd.batch.common.utils.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/ApplicationContext-common.xml")
public class SettingTest extends AbstractJUnit4SpringContextTests {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testProperty() {
        logger.info("se:" + Settings.getInstance().getBidLoanName());
    }
}
