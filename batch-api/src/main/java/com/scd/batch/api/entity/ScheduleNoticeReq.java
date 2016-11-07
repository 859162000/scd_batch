package com.scd.batch.api.entity;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Validated
public class ScheduleNoticeReq implements Serializable {

    // 是否完全执行成功
    @NotNull
    private boolean success;

    // 返回码
    @NotNull
    private String errorCode;

    @NotNull
    private String errorMsg;

    // 成功处理记录数
    private int successCnt;

    // 处理失败记录数
    private int failCnt;

    // 失败记录详细信息
    private List<String> errMsgList;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getSuccessCnt() {
        return successCnt;
    }

    public void setSuccessCnt(int successCnt) {
        this.successCnt = successCnt;
    }

    public int getFailCnt() {
        return failCnt;
    }

    public void setFailCnt(int failCnt) {
        this.failCnt = failCnt;
    }

    public List<String> getErrMsgList() {
        return errMsgList;
    }

    public void setErrMsgList(List<String> errMsgList) {
        this.errMsgList = errMsgList;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
