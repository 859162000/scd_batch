package com.scd.batch.executor.service;


import com.miaoqian.basis.api.dubbo.request.MailSendRequest;
import com.miaoqian.basis.api.dubbo.request.SmsInfo;
import com.miaoqian.framework.domain.Request;

import java.util.List;

public interface NoticeService {

    /**
     * 构建短信通知
     */
    Request<SmsInfo> buildSmsNotice(List<String> mobiles, String message);

    /**
     * 构建邮件通知
     */
    Request<MailSendRequest> buildEmailNotice(List<String> emails, String subject, String content);

    boolean batchSuccess();

    boolean batchFailed();

}
