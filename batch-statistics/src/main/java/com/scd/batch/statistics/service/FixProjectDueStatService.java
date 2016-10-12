package com.scd.batch.statistics.service;


import com.scd.batch.common.entity.FundStatEntity;
import com.scd.batch.statistics.dao.FundStatDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FixProjectDueStatService {

    @Resource
    private FundStatDao fundStatDao;

    // 按日查询客户资金变动汇总金额
    public List<FundStatEntity> getStatList(long start, long num) {
        return fundStatDao.getList(start, num);
    }

}