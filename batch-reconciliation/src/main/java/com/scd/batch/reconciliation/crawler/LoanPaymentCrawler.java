package com.scd.batch.reconciliation.crawler;

import com.alibaba.dubbo.rpc.RpcException;
import com.miaoqian.api.dto.ReconciliationDto;
import com.miaoqian.api.dto.ReconciliationReqDto;
import com.miaoqian.api.dto.ReconciliationResDto;
import com.miaoqian.api.facade.AccountFacade;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import com.scd.batch.common.exception.ErrorCodeException;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.reconciliation.util.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 流水对账服务实现
 */
@Component
public class LoanPaymentCrawler implements Crawler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DayCutService dayCutService;

    @Resource
    private AccountFacade accountFacade;

    /**
     * 放还款对账
     */
    @Override
    public List<LoanPaymentTransferEntity> crawler(ShortDate transDate,
                                                   TransferType transferType,
                                                   Pagination pagination) throws
            ErrorCodeException {


        ReconciliationReqDto reqDto = new ReconciliationReqDto();

        reqDto.setChannelCode("001");

        // 调用方编号,由网关系统分配：用户服务:001;标的服务:002；交易服务003；批跑服务004
        reqDto.setBizId("004");

        // YYYYMMDD，只取账务日期当天的对账数据
        reqDto.setBeginDate(DateUtil.DateToString(transDate.addDays(-1).toDate(), DateStyle.YYYYMMDD));
        reqDto.setEndDate(DateUtil.DateToString(transDate.toDate(), DateStyle.YYYYMMDD));

        // 分页数据
        reqDto.setPageSize(String.valueOf(pagination.getPageSize()));
        reqDto.setPageNum(String.valueOf(pagination.getCurPage()));

        // 流水类型，放款或者还款
        reqDto.setQueryTransType(transferType.name);

        int crawl_Times = 3;
        long retry_interval = 1000 * 60 * 1;
        Result<ReconciliationResDto> result = null;
        do {
            crawl_Times--;
            try {
                result = accountFacade.reconciliation(reqDto);
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
            throw new ErrorCodeException(CommonErrorCode.REC_CRAWLER_FAIL, "retryTime:" + crawl_Times);
        }

        ReconciliationResDto resDto = result.getData();

        // TODO int是否足够
        int totalItem = Integer.valueOf(resDto.getTotalItems());
        pagination.setCount(totalItem);

        List<ReconciliationDto> reconciliationDtoList = result.getData().getReconciliationDtoList();

        logger.info("size:" + reconciliationDtoList.size());

        List<LoanPaymentTransferEntity> transferEntityList = TransferUtil.buildLoanRepaymentTransfer
                (reconciliationDtoList);

        // 设置业务日期
        transferEntityList.forEach(p -> p.setTransDate(transDate.toDate()));

        return transferEntityList;
    }
}
