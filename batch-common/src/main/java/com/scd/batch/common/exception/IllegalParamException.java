package com.scd.batch.common.exception;


import com.scd.batch.common.logger.LogMessageContainers;

/**
 * IllegalParamException
 */
public class IllegalParamException extends SystemException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2043946373614873514L;

    /**
     * Default constructor
     */
    public IllegalParamException() {
        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public IllegalParamException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public IllegalParamException(String message) {
        super(message);
    }

    /**
     * Constructor with messageId & args
     *
     * @param messageId
     * @param args
     */
    public IllegalParamException(String messageId, Object... args) {
        super(messageId, args);
    }

    @Override
    public String getMessage() {
        if (super.messageId != null) {
            return LogMessageContainers
                    .getFormatedMessage(LogMessageContainers.MESSAGE_KEY_ERROR, super.messageId, args);
        }

        return super.getMessage();
    }

}
