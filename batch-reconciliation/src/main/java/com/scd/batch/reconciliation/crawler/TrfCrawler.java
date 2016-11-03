package com.scd.batch.reconciliation.crawler;

import com.miaoqian.api.dto.TrfReconciliationDto;
import com.miaoqian.api.dto.TrfReconciliationReqDto;
import com.miaoqian.api.dto.TrfReconciliationResDto;
import com.miaoqian.api.facade.AccountFacade;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.reconciliation.TransferEntity;
import com.scd.batch.common.exception.ErrorCodeException;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.reconciliation.service.TransferBatchUpdateService;
import com.scd.batch.reconciliation.util.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商户扣款对账
 */
@Component
public class TrfCrawler implements Crawler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DayCutService dayCutService;

    @Resource
    private AccountFacade accountFacade;

    @Resource
    private TransferBatchUpdateService batchUpdate;

    @Override
    public List<TransferEntity> crawler(ShortDate transDate, TransferType transferType, Pagination pagination) {

        TrfReconciliationReqDto reqDto = new TrfReconciliationReqDto();

        reqDto.setChannelCode("001");

        reqDto.setBizId("004");

        // YYYYMMDD，只取账务日期当天的对账数据
        reqDto.setBeginDate(DateUtil.DateToString(transDate.addDays(-1).toDate(), DateStyle.YYYYMMDD));
        reqDto.setEndDate(DateUtil.DateToString(transDate.toDate(), DateStyle.YYYYMMDD));

        // 分页数据
        reqDto.setPageSize(String.valueOf(pagination.getPageSize()));
        reqDto.setPageNum(String.valueOf(pagination.getCurPage()));

        Result<TrfReconciliationResDto> result = accountFacade.trfReconciliation(reqDto);
        if (!result.isSuccess()) {
            logger.info(result.getMessage());
            throw new ErrorCodeException(CommonErrorCode.REC_CRAWLER_FAIL,
                    result.getCode() + "," + result.getMessage());
        }
        List<TrfReconciliationDto> cashReconciliationDtoList = result.getData().getTrfReconciliationDtoList();

        // 写入数据库
        List<TransferEntity> transferEntityList = TransferUtil.buildTrfTransfer(cashReconciliationDtoList);

        // 设置业务日期
        transferEntityList.forEach(p -> p.setTransDate(transDate.toDate()));

        return transferEntityList;

    }
}
