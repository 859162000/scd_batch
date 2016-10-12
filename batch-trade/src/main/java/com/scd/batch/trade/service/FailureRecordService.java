package com.scd.batch.trade.service;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.trade.common.FailureStatus;
import com.scd.batch.trade.dao.FailRecordDAO;
import com.scd.batch.trade.model.FailRecordEntity;
import org.springframework.stereotype.Service;

@Service
public class FailureRecordService {
    @Resource
    private FailRecordDAO failRecordDAO;

    public List<Long> getAllFailedId(ShortDate accountDate) {
        return failRecordDAO.selectAllFailedId(accountDate.toDate());
    }

    public List<FailRecordEntity> getFailRecord(List<Long> idList) {
        return isNotEmpty(idList) ? failRecordDAO.selectById(idList) : new ArrayList<>();
    }

    public List<Long> getFailureIdList(ShortDate accountDate, FailureStatus status) {
        return failRecordDAO.selectFailureId(accountDate.toDate(), status.type);
    }

    public int update2succ(ShortDate accountDate, FailureStatus status) {
        return failRecordDAO.updateSuccessStatus(accountDate.toDate(), status.type);
    }

    public int updateFailRecord(long id, FailureStatus failureStatus) {
        return failRecordDAO.updateStatusById(id, failureStatus.type);
    }

    public int addFailRecord(List<FailRecordEntity> recordList) {
        return isNotEmpty(recordList) ? failRecordDAO.insert(recordList) : 0;
    }
}
