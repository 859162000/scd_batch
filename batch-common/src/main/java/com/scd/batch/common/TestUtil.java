package com.scd.batch.common;

import com.scd.batch.common.entity.acct.UserAccumulativeProfitEntity;
import com.scd.batch.common.entity.acct.UserDailyProfitEntity;
import com.scd.batch.common.entity.statistics.*;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.scd.batch.common.utils.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtil {

    public static TableSpec getTableSpec() {
        TableSpec tableSpec = TableSpec.getDefault();

        return tableSpec;
    }


    public static List<Long> buildProjectIdList() {
        List<Long> list = new ArrayList<>();
        list.add(1l);

        return list;
    }

    public static List<String> buildProjectCodeList() {

        List<String> list = new ArrayList<>();
        list.add("project_code");

        list.add("XM16092021000002");

        return list;
    }

    public static TableSpec buildTableSpec() {
        return TableSpec.getDefault();
    }

    public static ExecutorContext buildContext() {
        ExecutorContext context = new ExecutorContext();

        context.addAttach(TableSpec.class, buildTableSpec());

        Pagination pagination = new Pagination();
        pagination.setPageSize(10);
        pagination.setCurPage(1);
        context.addAttach(Pagination.class, pagination);

        context.addAttach(ShortDate.class, ShortDate.today());

        return context;
    }

    public static ProjectLimitEntity buildProjectLimit() {
        ProjectLimitEntity limitEntity = new ProjectLimitEntity();
        limitEntity.setId(100);
        limitEntity.setProjectCode("project_code");
        limitEntity.setProjectName("project_name100");
        limitEntity.setBid(100000);
        limitEntity.setUnbid(50000);
        limitEntity.setPaidInterest(100);
        limitEntity.setUnpaidInterest(50);
        limitEntity.setPaidPrincipal(3000);
        limitEntity.setUnpaidPrincipal(300);
        limitEntity.setTotal(999999);
        limitEntity.setWithdraw(8888);

        return limitEntity;
    }

    public static FixDueStatEntity buildFixDueStatEntity() {
        FixDueStatEntity entity = new FixDueStatEntity();
        entity.setId(1111);
        entity.setDueDate(ShortDate.today().toDate());
        entity.setFixPlanPrincipal(1000);
        entity.setFixPlanInterest(300);
        entity.setFixProjectPrincipal(33333);
        entity.setFixProjectInterest(332);
        entity.setSumAmt(3330000);

        return entity;
    }

    public static ExpenditureStatEntity buildExpenditureEntity() {
        ExpenditureStatEntity entity = new ExpenditureStatEntity();
        entity.setId(100);
        entity.setTransDate(ShortDate.today().toDate());
        entity.setAmount(3332222);
        entity.setType(1);
        return entity;
    }

    public static BorrowerRepayPlanStatEntity buildBorrowerRepayPlanEntity() {
        BorrowerRepayPlanStatEntity entity = new BorrowerRepayPlanStatEntity();
        entity.setId(100);
        entity.setDueDate(ShortDate.today().toDate());
        entity.setProjectCode("projectCode");
        entity.setBorrowerName("bid");
        entity.setBorrowerName("bname");
        entity.setDueInterest(1111);
        entity.setDueSum(33322);
        entity.setProjectName("pname");
        return entity;
    }

    public static AssetsStatEntity buildAssetsEntity() {
        AssetsStatEntity entity = new AssetsStatEntity();
        entity.setId(100);
        entity.setCurrent(111);
        entity.setFixProject(3332);
        entity.setFixPlan(333);
        entity.setFrozen(33222);
        entity.setBalance(10000);
        entity.setTransDate(ShortDate.today().toDate());

        return entity;
    }

    public static FundStatEntity buildFundStat() {
        FundStatEntity entity = new FundStatEntity();
        entity.setId(100);
        entity.setTransDate(ShortDate.today().toDate());
        entity.setRecharge(100);
        entity.setWithdraw(300);

        return entity;
    }

    public static List<ProjectLimitEntity> buildProjectLimitList() {
        List<ProjectLimitEntity> limitEntityList = new ArrayList<>();
        limitEntityList.add(buildProjectLimit());
        limitEntityList.add(buildProjectLimit());
        limitEntityList.add(buildProjectLimit());
        limitEntityList.add(buildProjectLimit());
        limitEntityList.add(buildProjectLimit());

        return limitEntityList;
    }

    public static List<FixDueStatEntity> buildFixDueStatList() {
        List<FixDueStatEntity> statEntityList = new ArrayList<>();
        statEntityList.add(buildFixDueStatEntity());
        statEntityList.add(buildFixDueStatEntity());
        statEntityList.add(buildFixDueStatEntity());
        statEntityList.add(buildFixDueStatEntity());
        statEntityList.add(buildFixDueStatEntity());

        return statEntityList;
    }

    public static List<ExpenditureStatEntity> buildExpenditureList() {
        List<ExpenditureStatEntity> entityList = new ArrayList<>();
        entityList.add(buildExpenditureEntity());
        entityList.add(buildExpenditureEntity());
        entityList.add(buildExpenditureEntity());
        entityList.add(buildExpenditureEntity());
        entityList.add(buildExpenditureEntity());
        entityList.add(buildExpenditureEntity());

        return entityList;
    }

    public static List<BorrowerRepayPlanStatEntity> buildBorrowerRepayPlanList() {
        List<BorrowerRepayPlanStatEntity> entityList = new ArrayList<>();
        entityList.add(buildBorrowerRepayPlanEntity());
        entityList.add(buildBorrowerRepayPlanEntity());
        entityList.add(buildBorrowerRepayPlanEntity());
        entityList.add(buildBorrowerRepayPlanEntity());
        entityList.add(buildBorrowerRepayPlanEntity());
        entityList.add(buildBorrowerRepayPlanEntity());
        return entityList;
    }

    public static UserDailyProfitEntity buildUserDailyEntity() {
        UserDailyProfitEntity entity = new UserDailyProfitEntity();
        entity.setId(100);
        entity.setCurrentProfit(new BigDecimal("1000"));
        entity.setProfit(new BigDecimal("1000"));
        entity.setUid("uid");
        entity.setDate(DateUtil.DateToString(ShortDate.today().toDate(), DateStyle.YYYYMMDD));

        return entity;
    }


    public static UserAccumulativeProfitEntity buildAccumulativeEntity() {
        UserAccumulativeProfitEntity entity = new UserAccumulativeProfitEntity();
        entity.setId(1000);
        entity.setUid("uid");
        entity.setCurrentInvestProfit(new BigDecimal("1000"));
        entity.setTotalProfit(new BigDecimal("10202"));

        return entity;
    }


    public static List<String> buildStatusList() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        return list;
    }

    public static Date getStartDate() {
        ShortDate date = ShortDate.today();
        date.addDays(-10);
        return date.toDate();
    }

    public static Date getEndDate() {
        return ShortDate.today().toDate();
    }

    public static List<Long> buildIds() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);

        return list;
    }

}
