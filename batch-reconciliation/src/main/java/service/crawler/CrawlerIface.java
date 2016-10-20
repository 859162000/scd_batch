package service.crawler;

import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.TransferEntity;

import java.util.List;

/**
 * 流水爬取接口
 */
public interface CrawlerIface {

    <T extends TransferEntity> List<T> crawler(TransferType transferType, Pagination pagination);

}
