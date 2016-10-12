package com.scd.batch.statistics.service;

import com.scd.batch.common.entity.AssetsStatEntity;
import com.scd.batch.common.entity.FundStatEntity;
import com.scd.batch.statistics.dao.AssetsStatDao;
import com.scd.batch.statistics.dao.FundStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsStatService {

    @Autowired
    private AssetsStatDao assetsStatDao;

    public List<AssetsStatEntity> getStatList(long start, long num) {
        return assetsStatDao.getList(start, num);
    }


}