package com.scd.batch.common.exception;

public class MultiDBException extends SystemException {
    private static final long serialVersionUID = -8879525250880790138L;

    /**
     * Default constructor
     */
    public MultiDBException() {
        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public MultiDBException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public MultiDBException(String message) {
        super(message);
    }

    /**
     * Constructor with messageId & args
     *
     * @param messageId
     * @param args
     */
    public MultiDBException(String messageId, Object...args) {
        super(messageId, args);
    }

}
