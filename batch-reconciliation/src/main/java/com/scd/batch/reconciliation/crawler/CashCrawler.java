package com.scd.batch.reconciliation.crawler;

import com.miaoqian.api.dto.CashReconciliationDto;
import com.miaoqian.api.dto.CashReconciliationReqDto;
import com.miaoqian.api.dto.CashReconciliationResDto;
import com.miaoqian.api.facade.AccountFacade;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
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
 * 取现对账
 */
@Component
public class CashCrawler implements Crawler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DayCutService dayCutService;

    @Resource
    private AccountFacade accountFacade;

    @Resource
    private TransferBatchUpdateService batchUpdate;

    /**
     * 取现对账
     */
    @Override
    public List<CashTransferEntity> crawler(ShortDate transDate, TransferType transferType, Pagination pagination) {

        CashReconciliationReqDto reqDto = new CashReconciliationReqDto();

        reqDto.setChannelCode("001");

        reqDto.setBizId("004");

        Date lastDay = transDate.addDays(-1).toDate();
        // YYYYMMDD，只取账务日期当天的对账数据
        reqDto.setBeginDate(DateUtil.DateToString(lastDay, DateStyle.YYYYMMDD));
        reqDto.setEndDate(DateUtil.DateToString(lastDay, DateStyle.YYYYMMDD));

        // 分页数据
        reqDto.setPageSize(String.valueOf(pagination.getPageSize()));
        reqDto.setPageNum(String.valueOf(pagination.getCurPage()));

        logger.info("pageSize:" + reqDto.getPageSize() + ", pageNum:" + reqDto.getPageNum());

        Result<CashReconciliationResDto> result = accountFacade.queryCashReconciliation(reqDto);
        if (!result.isSuccess()) {
            logger.info(result.getMessage());
            throw new ErrorCodeException(CommonErrorCode.REC_CRAWLER_FAIL,
                    result.getCode() + "," + result.getMessage());
        }

        logger.info("result:" + result);

        List<CashReconciliationDto> cashReconciliationDtoList = result.getData().getCashReconciliationDtoList();

        String feeObj = result.getData().getFeeObj();
        // 构建实体
        List<CashTransferEntity> transferEntityList = TransferUtil.buildCashTransfer(transDate.toDate(),
                cashReconciliationDtoList,
                feeObj);

        // 设置业务日期
        transferEntityList.forEach(p -> p.setTransDate(transDate.toDate()));

        return transferEntityList;

    }
}
