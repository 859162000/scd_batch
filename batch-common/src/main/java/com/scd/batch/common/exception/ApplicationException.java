package com.scd.batch.common.exception;


import com.scd.batch.common.logger.LogMessageContainers;

/**
 * Application exception that encapsulates the logic error message.
 *
 * @author chenguoqing
 *
 */
public class ApplicationException extends Exception {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1878149770329891540L;
    /**
     * Message Id
     */
    public final String messageId;
    /**
     * Message arguments
     */
    public final Object[] args;

    /**
     * Constructor
     *
     * @param messageId message id
     * @param args message arguments, if the last element is {@link Throwable} instance, it will be removed from
     *            argument list.
     */
    public ApplicationException(String messageId, Object...args) {
        this.messageId = messageId;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return LogMessageContainers.getFormatedMessage(LogMessageContainers.MESSAGE_KEY_LOG, messageId, args);
    }
}
