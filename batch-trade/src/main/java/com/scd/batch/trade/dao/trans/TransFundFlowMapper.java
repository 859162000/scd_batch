package com.scd.batch.trade.dao.trans;

import com.scd.batch.trade.model.trans.TransFundFlow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TransFundFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TransFundFlow record);

    int insertSelective(TransFundFlow record);

    TransFundFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransFundFlow record);

    int updateByPrimaryKey(TransFundFlow record);

    //获取资金流水接口
    int getRecordTotalNum(Map condition);

    List<TransFundFlow> selectTransFundFlowByPage(Map condition);
}
