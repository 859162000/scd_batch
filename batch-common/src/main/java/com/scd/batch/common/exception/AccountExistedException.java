package com.scd.batch.common.exception;

/**
 * AccountExistedException
 *
 * 应该当做成功的特殊异常，manager遇到该异常，则当做交易成功处理。
 *
 */
public class AccountExistedException extends SystemException {



    /**
     * Default constructor
     */
    public AccountExistedException() {
        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public AccountExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public AccountExistedException(String message) {
        super(message);
    }

    /**
     * Constructor with messageId & args
     *
     * @param messageId
     * @param args
     */
    public AccountExistedException(String messageId, Object...args) {
        super(messageId, args);
    }

}
