package com.scd.batch.common.daycut.exception;

public class DayCutException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 403285317495124802L;

    /**
     * Constructor with message & cause
     * 
     * @param message
     * @param cause
     */
    public DayCutException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message & cause
     * 
     * @param message
     */
    public DayCutException(String message) {
        super(message);
    }
}
