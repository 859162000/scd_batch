package com.scd.batch.api.entity;

import java.io.Serializable;

public interface ErrorResponse extends Serializable {
    /**
     * set error code
     */
    void setErrorCode(int errorCode);

    /**
     * Retrieves the error code
     */
    int getErrorCode();

    /**
     * Set message
     */
    void setMessage(String message);

    /**
     * Retrieve the bound error message
     */
    String getMessage();
}
