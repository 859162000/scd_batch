package com.scd.batch.reconciliation.crawler;

import com.miaoqian.api.dto.*;
import com.miaoqian.api.facade.AccountFacade;
import com.miaoqian.api.facade.UserFacade;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.HuiFuUserBalanceEntity;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 余额抓取
 */
@Component
public class UserBalanceCrawler implements Crawler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DayCutService dayCutService;

    @Resource
    private UserFacade userFacade;

    @Resource
    private TransferBatchUpdateService batchUpdate;

    @Override
    public List<HuiFuUserBalanceEntity> crawler(ShortDate transDate, List<String> acctList) {

        List<HuiFuUserBalanceEntity> huifuBalanceList = new ArrayList<>();

        QueryBalanceBgReqDto reqDto = new QueryBalanceBgReqDto();

        reqDto.setChannelCode("001");

        reqDto.setBizId("004");
        for (String acctId : acctList) {

            reqDto.setUsrCustId(acctId);

            Result<QueryBalanceBgResDto> result = userFacade.queryBalanceBg(reqDto);
            if (!result.isSuccess()) {
                logger.info(result.getMessage());
                throw new ErrorCodeException(CommonErrorCode.REC_CRAWLER_FAIL,
                        result.getCode() + "," + result.getMessage());
            }

            logger.info("result:" + result);

            QueryBalanceBgResDto dto = result.getData();

            // 构建实体
            HuiFuUserBalanceEntity balance = TransferUtil.buildUserBalance(dto);
            huifuBalanceList.add(balance);
        }

        return huifuBalanceList;
    }

    @Override
    public <T extends TransferEntity> List<T> crawler(ShortDate transDate, TransferType transferType, Pagination
            pagination) {
        return null;
    }
}
