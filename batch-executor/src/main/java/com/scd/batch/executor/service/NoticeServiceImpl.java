package com.scd.batch.executor.service;

import com.miaoqian.basis.api.dubbo.request.SmsInfo;
import com.miaoqian.framework.domain.Request;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class NoticeServiceImpl implements NoticeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Resource
    private SwitchService switchService;

    @Override
    public boolean buildSmsNotice() {
        ShortDate transDate = switchService.currentAccountDate();

        Request<SmsInfo> request = new Request<>();
        SmsInfo smsInfo = new SmsInfo();
        request.setParam(smsInfo);

        return false;
    }

    @Override
    public boolean buildEmailNotice() {
        return false;
    }

    @Override
    public Request<SmsInfo> batchSuccess() {
        String[] mobiles = new String[]{
                "15901411984"
        };

        Request<SmsInfo> request = new Request<>();
        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setMobileNumbers(mobiles);
        smsInfo.setMessageContent("批跑执行成功！time:["
                + DateUtils.formatDate(new Date(),
                DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()) + "]");

        request.setParam(smsInfo);

        return request;
    }

    @Override
    public void batchFailed() {

    }
}
