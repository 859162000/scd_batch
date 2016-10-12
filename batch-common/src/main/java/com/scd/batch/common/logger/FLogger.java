package com.scd.batch.common.logger;

import com.scd.batch.common.exception.ApplicationException;
import com.scd.batch.common.exception.SystemException;
import org.slf4j.Logger;

/**
 * FCoreLogger extends Slf4j {@link Logger} and adds log method for message id
 * 
 * @author chenguoqing
 */
public interface FLogger extends Logger {
    /**
     * Output the log message represents by <code>messageId</code>
     * 
     * @param messageId message id
     * @param args message arguments
     */
    void log(String messageId, Object... args);

    void log(ApplicationException e);

    void log(SystemException e);
}
