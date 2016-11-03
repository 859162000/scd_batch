package com.scd.batch.reconciliation.crawler;

import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.TransferEntity;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;

import java.util.List;

/**
 * 流水爬取接口
 */
public interface Crawler {

    <T extends TransferEntity> List<T> crawler(ShortDate transDate, TransferType transferType, Pagination pagination);

}
