package util;

import com.miaoqian.api.dto.CashReconciliationDto;
import com.miaoqian.api.dto.ReconciliationDto;
import com.scd.batch.common.entity.reconciliation.CashTransferEntity;
import com.scd.batch.common.entity.reconciliation.LoanPaymentTransferEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwei on 2016-10-18.
 */
public class TransferUtil {

    /**
     * 用汇付放款还款流水构建对账数据
     *
     * @param reconciliationDtoList
     * @return
     */
    public static List<LoanPaymentTransferEntity> buildLoanRepaymentTransfer(List<ReconciliationDto> reconciliationDtoList) {
        List<LoanPaymentTransferEntity> transferEntityList = new ArrayList<>();

        // TODO  数据转换


        return transferEntityList;
    }


    /**
     * 用汇付取现流水构建对账数据
     *
     * @param reconciliationDtoList
     * @return
     */
    public static List<CashTransferEntity> buildCashTransfer(List<CashReconciliationDto> reconciliationDtoList) {
        List<CashTransferEntity> cashTransferEntityList = new ArrayList<>();

        // TODO  数据转换


        return cashTransferEntityList;
    }
}
