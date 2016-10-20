package com.scd.batch.statistics.service;

import com.scd.batch.common.entity.statistics.ExpenditureStatEntity;
import com.scd.batch.statistics.dao.ExpenditureStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenditureStatService {

    @Autowired
    private ExpenditureStatDao expenditureStatDao;

    public List<ExpenditureStatEntity> getStatList(long start, long num) {
        return expenditureStatDao.getList(start, num);
    }


}