package com.scd.batch.statistics.service;

import com.scd.batch.common.entity.statistics.BorrowerRepayPlanStatEntity;
import com.scd.batch.statistics.dao.BorrowerRepayPlanStatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerRepayPlanStatService {

    @Autowired
    private BorrowerRepayPlanStatDao borrowerRepayPlanStatDao;

    public List<BorrowerRepayPlanStatEntity> getStatList(long start, long num) {
        return borrowerRepayPlanStatDao.getList(start, num);
    }


}