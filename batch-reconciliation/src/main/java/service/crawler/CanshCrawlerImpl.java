package service.crawler;

import com.miaoqian.api.dto.CashReconciliationDto;
import com.miaoqian.api.dto.CashReconciliationReqDto;
import com.miaoqian.api.facade.AccountFacade;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import service.TransferBatchUpdateImpl;
import util.TransferUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 流水对账服务实现
 */
public class CanshCrawlerImpl implements CrawlerIface {


    @Resource
    private DayCutService dayCutService;

    @Resource
    private AccountFacade accountFacade;

    @Resource
    private TransferBatchUpdateImpl batchUpdate;

    /**
     * 放还款对账
     */
    @Override
    public List<CashTransferEntity> crawler(TransferType transferType, Pagination pagination) {

        // 业务日期
        Date transDate = dayCutService.load();

        // TODO 调用网关服务，获取JSON
        CashReconciliationReqDto reqDto = new CashReconciliationReqDto();

        // TODO 通道编号
        reqDto.setChannelCode("");

        // 调用方编号,由网关系统分配：用户服务:001;标的服务:002；交易服务003
        reqDto.setBizId("");

        // YYYYMMDD，只取账务日期当天的对账数据
        reqDto.setBeginDate(DateUtil.DateToString(transDate, DateStyle.YYYYMMDD));
        reqDto.setEndDate(DateUtil.DateToString(transDate, DateStyle.YYYYMMDD));

        // 分页数据
        reqDto.setPageSize(String.valueOf(pagination.getPageSize()));
        reqDto.setPageNum(String.valueOf(pagination.getCurPage()));

        List<CashReconciliationDto> cashReconciliationDtoList = accountFacade.queryCashReconciliation(reqDto).getData().getCashReconciliationDtoList();

        // 清理当天的旧数据
//        batchUpdate.delete(transDate);

        // 写入数据库
        List<CashTransferEntity> transferEntityList = TransferUtil.buildCashTransfer(cashReconciliationDtoList);
//        batchUpdate.batchInsertCashTransfer(transferEntityList);

        return transferEntityList;

    }
}
