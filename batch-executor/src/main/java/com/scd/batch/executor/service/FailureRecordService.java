package com.scd.batch.executor.service;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.constant.executor.FailureStatus;
import com.scd.batch.common.job.batch.control.dao.FailRecordDao;
import com.scd.batch.common.entity.executor.FailRecordEntity;
import org.springframework.stereotype.Service;

@Service
public class FailureRecordService {
    @Resource
    private FailRecordDao failRecordDao;

    public List<Long> getAllFailedId(ShortDate accountDate) {
        return failRecordDao.selectAllFailedId(accountDate.toDate());
    }

    public List<FailRecordEntity> getFailRecord(List<Long> idList) {
        return isNotEmpty(idList) ? failRecordDao.selectById(idList) : new ArrayList<>();
    }

    public List<Long> getFailureIdList(ShortDate accountDate, FailureStatus status) {
        return failRecordDao.selectFailureId(accountDate.toDate(), status.type);
    }

    public int update2succ(ShortDate accountDate, FailureStatus status) {
        return failRecordDao.updateSuccessStatus(accountDate.toDate(), status.type);
    }

    public int updateFailRecord(long id, FailureStatus failureStatus) {
        return failRecordDao.updateStatusById(id, failureStatus.type);
    }

    public int addFailRecord(List<FailRecordEntity> recordList) {
        return isNotEmpty(recordList) ? failRecordDao.insert(recordList) : 0;
    }
}
