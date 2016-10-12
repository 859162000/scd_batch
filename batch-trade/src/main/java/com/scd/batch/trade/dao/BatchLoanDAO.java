package com.scd.batch.trade.dao;

import java.util.Date;
import java.util.List;

import com.scd.batch.common.mybatis.version.OptimisticLock;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.trade.model.loan.InstallmentInfo;
import com.scd.batch.trade.model.loan.LoanInfo;
import com.scd.batch.trade.model.loan.LoanInstallmentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchLoanDAO {

    List<Long> getAllOverdueLoanId(@Param("ts") TableSpec ts, @Param("accountDate") Date accountDate);

    List<LoanInstallmentInfo> selectInstallmentInfoByLoanId(@Param("ts") TableSpec ts,
                                                            @Param("loanIdList") List<Long> loanIdList,
                                                            @Param("accountDate") Date accountDate);

    List<LoanInstallmentInfo> selectLoanWithInstallment(@Param("partitionId") long partitionId,
                                                        @Param("customerId") long customerId,
                                                        @Param("accountId") long accountId,
                                                        @Param("loanId") long loanId,
                                                        @Param("accountDate") Date accountDate);

    List<Long> getAllLoanId(@Param("ts") TableSpec ts) ;

    List<InstallmentInfo> selectInstallmentByLoanIds(@Param("ts") TableSpec tableSpec,
                                                     @Param("loanIdList") List<Long> loanIdList);

    @OptimisticLock(expect = 1)
    int updateLoan(@Param("partitionId") long partitionId, @Param("loanInfo") LoanInfo loanInfo);

    @OptimisticLock(expect = 1)
    int updateInstallmentSchedule(@Param("partitionId") long partitionId,
                                  @Param("installment") InstallmentInfo installment);

    @OptimisticLock(expect = 1)
    int updateAccountStatus(@Param("partitionId") long customerId,
                            @Param("accountId") long accountId,
                            @Param("debitStatus") int debitStatus,
                            @Param("loanAge") int loanAge);

    List<Long> selectAllOverdueCustomerId(@Param("ts") TableSpec ts, @Param("accountDate") Date accountDate);

    List<InstallmentInfo> selectOverdueInstallmentByCustomerId(@Param("ts") TableSpec ts,
                                                               @Param("customerIdList") List<Long> customerIdList,
                                                               @Param("accountDate") Date accountDate);
}
