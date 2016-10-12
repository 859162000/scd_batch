package com.scd.batch.api.entity;


import com.scd.batch.common.constant.CommonErrorCode;
import com.scd.batch.common.exception.ErrorCodeException;

public class DefaultResponse implements ErrorResponse {

    private static final long serialVersionUID = -190397674718222460L;

    /**
     * Error code
     */
    private int errorCode = CommonErrorCode.COM_SUCCESS;

    /**
     * Bound error message
     */
    private String errorMessage = CommonErrorCode.COM_SUCCESS_DESC;

    /**
     * Constructor success response
     */
    public DefaultResponse() {
        this.errorCode = CommonErrorCode.COM_SUCCESS;
        this.errorMessage = CommonErrorCode.COM_SUCCESS_DESC;
    }

    /**
     * Constructor error response
     */
    public DefaultResponse(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public DefaultResponse(ErrorCodeException e) {
        this.errorCode = e.errorCode;
        this.errorMessage = e.getMessage();
    }

    public DefaultResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Whether a success response
     */
    public boolean isSuccess() {
        return errorCode == CommonErrorCode.COM_SUCCESS;
    }

    /**
     * Whether a error response
     */
    public boolean isNotSuccess() {
        return !isSuccess();
    }

    @Override
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public void setMessage(String message) {
        this.errorMessage = message;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DefaultResponse [errorCode=");
        builder.append(errorCode);
        builder.append(", errorMessage=");
        builder.append(errorMessage);
        builder.append("]");
        return builder.toString();
    }

}
