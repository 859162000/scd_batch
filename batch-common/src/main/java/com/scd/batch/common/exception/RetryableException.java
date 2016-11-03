package com.scd.batch.common.exception;

/**
 * RetryableException
 * 
 */
public class RetryableException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4147467240172878091L;

    /**
     * Default constructor
     */
    public RetryableException() {
        super();
    }

    /**
     * Constructor with message & cause
     * @param message
     * @param cause
     */
    public RetryableException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     * @param message
     */
    public RetryableException(String message) {
        super(message);
    }

    /**
     * Constructor with cause
     * @param cause
     */
    public RetryableException(Throwable cause) {
        super(cause);
    }

}
