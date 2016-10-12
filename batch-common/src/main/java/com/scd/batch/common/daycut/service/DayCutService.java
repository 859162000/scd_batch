package com.scd.batch.common.daycut.service;


import com.scd.batch.common.daycut.exception.DayCutException;

import java.util.Date;

public interface DayCutService {

    /**
     * Load current accounting date
     * 
     * @return current accounting date
     * @throws DayCutException
     */
    Date load() throws DayCutException;
}
