package service.crawler;

import com.miaoqian.api.dto.ReconciliationDto;
import com.miaoqian.api.dto.ReconciliationReqDto;
import com.miaoqian.api.facade.AccountFacade;
import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.DateUtil;
import com.scd.batch.common.utils.Pagination;
import com.scd.batch.common.constant.reconciliation.TransferType;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;
import util.TransferUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 流水对账服务实现
 */
public class LoanPaymentCrawlerImpl implements CrawlerIface {


    @Resource
    private DayCutService dayCutService;

    @Resource
    private AccountFacade accountFacade;


    /**
     * 放还款对账
     */
    @Override
    public List<LoanPaymentTransferEntity> crawler(TransferType transferType, Pagination pagination) {

        // 业务日期
        Date transDate = dayCutService.load();

        // TODO 调用网关服务，获取JSON
        ReconciliationReqDto reqDto = new ReconciliationReqDto();

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

        // 流水类型，放款或者还款
        reqDto.setQueryTransType(transferType.name);

        // TODO  为空判断
        List<ReconciliationDto> reconciliationDtoList = accountFacade.reconciliation(reqDto).getData().getReconciliationDtoList();

        // 清理当天的旧数据
//        batchUpdate.delete(transDate);

        // 写入数据库
        List<LoanPaymentTransferEntity> transferEntityList = TransferUtil.buildLoanRepaymentTransfer(reconciliationDtoList);
//        batchUpdate.batchInsertLoanPaymentTransfer(transferEntityList);

        return transferEntityList;
    }
}
