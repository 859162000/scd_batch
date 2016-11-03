package com.scd.batch.common.exception;

public class OptimisticLockingException extends SystemException {
    private static final long serialVersionUID = -8879525250880790138L;

    /**
     * Default constructor
     */
    public OptimisticLockingException() {
        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public OptimisticLockingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public OptimisticLockingException(String message) {
        super(message);
    }

    /**
     * Constructor with messageId & args
     *
     * @param messageId
     * @param args
     */
    public OptimisticLockingException(String messageId, Object...args) {
        super(messageId, args);
    }

}
