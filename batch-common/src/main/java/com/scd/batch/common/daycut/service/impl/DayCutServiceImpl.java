package com.scd.batch.common.daycut.service.impl;

import com.scd.batch.common.daycut.dao.DayCutDao;
import com.scd.batch.common.daycut.exception.DayCutException;
import com.scd.batch.common.daycut.service.DayCutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

public class DayCutServiceImpl implements DayCutService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DayCutServiceImpl.class);

    @Resource
    private DayCutDao dayCutDao;

    @Override
    @Transactional
    public Date load() {
        try {
            return dayCutDao.loadDate().getAccountDate();
            
        } catch (Exception e) {
            LOGGER.error("Load current accounting date exception.", e);
            throw new DayCutException("Load current accounting date exception.", e);
        }
    }

}
