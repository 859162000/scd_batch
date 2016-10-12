package com.scd.batch.common.exception;

/**
 * RequestIdExistedException
 *
 * 应该当做成功的特殊异常，manager遇到该异常，则当做交易成功处理。
 *
 */
public class RequestIdExistedException extends SystemException {

    private static final long serialVersionUID = 7935531759132038079L;

    /**
     * Default constructor
     */
    public RequestIdExistedException() {
        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public RequestIdExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public RequestIdExistedException(String message) {
        super(message);
    }

    /**
     * Constructor with messageId & args
     *
     * @param messageId
     * @param args
     */
    public RequestIdExistedException(String messageId, Object...args) {
        super(messageId, args);
    }

}
