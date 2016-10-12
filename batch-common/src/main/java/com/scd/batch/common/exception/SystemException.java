package com.scd.batch.common.exception;


import com.scd.batch.common.logger.LogMessageContainers;

/**
 * System runtime exception for logging purpose
 *
 * @author chenguoqing
 */
public class SystemException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4552533571424047815L;

    /**
     * Message Id
     */
    public final String messageId;
    /**
     * Message arguments
     */
    public final Object[] args;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public SystemException() {
        super();
        this.messageId = null;
        this.args = null;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public SystemException(String message) {
        super(message);
        this.messageId = null;
        this.args = null;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     *
     * @since 1.4
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.messageId = null;
        this.args = null;
    }

    /**
     * Constructor
     *
     * @param messageId message id
     * @param args      message arguments, if the last element is {@link Throwable} instance, it will be removed from
     *                  argument list.
     */
    public SystemException(String messageId, Object... args) {
        this.messageId = messageId;
        this.args = args;

        if (args.length > 0 && args[args.length - 1] instanceof Throwable) {
            initCause((Throwable) args[args.length - 1]);
        }
    }

    @Override
    public String getMessage() {
        if (messageId != null) {
            return LogMessageContainers.getFormatedMessage(LogMessageContainers.MESSAGE_KEY_LOG, messageId, args);
        }

        return super.getMessage();
    }
}
