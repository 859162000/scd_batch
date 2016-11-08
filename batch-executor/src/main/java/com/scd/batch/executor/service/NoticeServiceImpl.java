package com.scd.batch.executor.service;

import com.miaoqian.basis.api.dubbo.MailAPI;
import com.miaoqian.basis.api.dubbo.SmsAPI;
import com.miaoqian.basis.api.dubbo.request.EnumContentType;
import com.miaoqian.basis.api.dubbo.request.MailInfo;
import com.miaoqian.basis.api.dubbo.request.MailSendRequest;
import com.miaoqian.basis.api.dubbo.request.SmsInfo;
import com.miaoqian.framework.domain.Request;
import com.miaoqian.framework.domain.Result;
import com.scd.batch.common.utils.DateStyle;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.executor.service.daycut.SwitchService;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Resource
    private SwitchService switchService;

    @Autowired
    private SmsAPI smsAPI;

    @Autowired
    private MailAPI mailAPI;

    @Override
    public Request<SmsInfo> buildSmsNotice(List<String> mobiles, String message) {
        ShortDate transDate = switchService.currentAccountDate();

        Request<SmsInfo> request = new Request<>();
        SmsInfo smsInfo = new SmsInfo();

        smsInfo.setMobileNumbers(mobiles.toArray(new String[]{}));
        smsInfo.setMessageContent(message);

        request.setParam(smsInfo);

        return request;
    }

    @Override
    public Request<MailSendRequest> buildEmailNotice(List<String> emails, String subject, String content) {

        Request<MailSendRequest> request = new Request<>();
        MailInfo mailInfo = new MailInfo();
        mailInfo.setContentType(EnumContentType.HTML);
        mailInfo.setToAddresses(emails);
        mailInfo.setSubject(subject);
        mailInfo.setContent(content);
        MailSendRequest mailSend = new MailSendRequest();

        mailSend.setMailInfo(mailInfo);
        request.setParam(mailSend);

        return request;
    }

    @Override
    public boolean batchSuccess() {
        ShortDate transDate = switchService.currentAccountDate();
        // SMS
        List<String> mobiles = new ArrayList<>();
        mobiles.add("15901411984");

        String msg = "批跑执行成功！time:["
                + DateUtils.formatDate(new Date(),
                DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()) + "]";

        Result result = smsAPI.sendMessage(buildSmsNotice(mobiles, msg));
        if (!result.isSuccess()) {
            LOGGER.info("发送短信失败！");
        }

        // EMAIL
        List<String> emails = new ArrayList<>();
        emails.add("liyankai@shicaidia.com");
        String subject = "批跑情况日报[" + transDate.toString() + "]";
        String content = "批跑执行成功！time:["
                + DateUtils.formatDate(new Date(),
                DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()) + "]";
        result = mailAPI.send(buildEmailNotice(emails, subject, content));

        if (!result.isSuccess()) {
            LOGGER.info("发送邮件失败！");
        }
        return true;
    }

    @Override
    public boolean batchFailed() {
        ShortDate transDate = switchService.currentAccountDate();
        // SMS
        List<String> mobiles = new ArrayList<>();
        mobiles.add("15901411984");

        String msg = "批跑执行失败！time:["
                + DateUtils.formatDate(new Date(),
                DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()) + "]";

        Result result = smsAPI.sendMessage(buildSmsNotice(mobiles, msg));
        if (!result.isSuccess()) {
            LOGGER.info("发送短信失败！");
        }

        // EMAIL
        List<String> emails = new ArrayList<>();
        emails.add("liyankai@shicaidia.com");
        String subject = "批跑情况日报[" + transDate.toString() + "]";
        String content = "批跑执行失败！time:["
                + DateUtils.formatDate(new Date(),
                DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()) + "]";
        result = mailAPI.send(buildEmailNotice(emails, subject, content));

        if (!result.isSuccess()) {
            LOGGER.info("发送邮件失败！");
        }
        return true;
    }

}
