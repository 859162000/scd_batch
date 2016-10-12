package com.scd.batch.statistics.service;

import com.scd.batch.common.entity.FundStatEntity;
import com.scd.batch.statistics.dao.FundStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundStatService {

    @Autowired
    private FundStatDao fundStatDao;

    public List<FundStatEntity> getStatList(long start, long num) {
        return fundStatDao.getList(start, num);
    }


}