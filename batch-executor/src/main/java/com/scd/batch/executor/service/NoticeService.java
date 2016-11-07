package com.scd.batch.executor.service;


import com.miaoqian.basis.api.dubbo.request.SmsInfo;
import com.miaoqian.framework.domain.Request;

public interface NoticeService {

    /**
     * 构建短信通知
     */
    boolean buildSmsNotice();

    /**
     * 构建邮件通知
     */
    boolean buildEmailNotice();

    Request<SmsInfo> batchSuccess();

    void batchFailed();

}
