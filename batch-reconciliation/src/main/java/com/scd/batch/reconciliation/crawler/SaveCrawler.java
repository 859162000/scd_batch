package com.scd.batch.reconciliation.crawler;

import com.alibaba.dubbo.rpc.RpcException;
import com.miaoqian.api.dto.*;
import com.miaoqian.api.facade.AccountFacade;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.reconciliation.SaveTransferEntity;
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
public class SaveCrawler implements Crawler {

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
    public List<SaveTransferEntity> crawler(ShortDate transDate, TransferType transferType, Pagination pagination) {

        RechargeReconciliationReqDto reqDto = new RechargeReconciliationReqDto();

        reqDto.setChannelCode("001");

        reqDto.setBizId("004");

        // YYYYMMDD，只取账务日期当天的对账数据
        reqDto.setBeginDate(DateUtil.DateToString(transDate.addDays(-1).toDate(), DateStyle.YYYYMMDD));
        reqDto.setEndDate(DateUtil.DateToString(transDate.toDate(), DateStyle.YYYYMMDD));

        // 分页数据
        reqDto.setPageSize(String.valueOf(pagination.getPageSize()));
        reqDto.setPageNum(String.valueOf(pagination.getCurPage()));

        Result<RechargeReconciliationResDto> result = null;
        int crawl_Times = 3;
        long retry_interval = 1000 * 60 * 1;
        do {
            crawl_Times--;
            try {
                result = accountFacade.queryRechargeReconciliation(reqDto);
            } catch (RpcException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(retry_interval);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        } while ((result == null || !result.isSuccess()) && crawl_Times > 0);

        if (!result.isSuccess()) {
            logger.info(result.getMessage());
            throw new ErrorCodeException(CommonErrorCode.REC_CRAWLER_FAIL,
                    result.getCode() + "," + result.getMessage());
        }
        List<SaveReconciliationDto> cashReconciliationDtoList = result.getData().getSaveReconciliationDtoList();

        List<SaveTransferEntity> transferEntityList = TransferUtil.buildSaveTransfer(cashReconciliationDtoList);

        // 设置业务日期
        transferEntityList.forEach(p -> p.setTransDate(transDate.toDate()));

        return transferEntityList;

    }

    @Override
    public List crawler(ShortDate transDate, List<String> list) {
        return null;
    }
}
