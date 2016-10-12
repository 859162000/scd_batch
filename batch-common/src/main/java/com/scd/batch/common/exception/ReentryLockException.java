package com.scd.batch.common.exception;

/**
 * ReentryLockException
 *
 */
public class ReentryLockException extends SystemException {

    private static final long serialVersionUID = 7935531759132038079L;

    /**
     * Default constructor
     */
    public ReentryLockException() {
        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public ReentryLockException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public ReentryLockException(String message) {
        super(message);
    }

    /**
     * Constructor with messageId & args
     *
     * @param messageId
     * @param args
     */
    public ReentryLockException(String messageId, Object...args) {
        super(messageId, args);
    }

}
