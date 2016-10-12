package com.scd.batch.common.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link LogMessageContainers} contains all message containers
 *
 * @author chenguoqing01
 */
public class LogMessageContainers {
    /**
     * Message key: log
     */
    public static final String MESSAGE_KEY_LOG = "log";
    /**
     * Message key : error message
     */
    public static final String MESSAGE_KEY_ERROR = "error";

    /**
     * Default log message location
     */
    public static final String[] DEFAULT_LOG_LOCATION = { "classpath*:META-INF/log/*.log.xml" };
    /**
     * Default error message location
     */
    private static final String[] DEFAULT_LOG_ERROR = { "classpath*:META-INF/error/*.error.xml" };
    /**
     * All registered message containers
     */
    private static final Map<String, LogMessageContainer> containers = new ConcurrentHashMap<>();

    /**
     * Initialize the message container and bound it with the key <b>containerKey</b>
     */
    public static void initMessageContainer(String containerKey, String...locations) throws Exception {

        if (!containers.containsKey(containerKey)) {

            // construct new container instance
            LogMessageContainer container = new LogMessageContainer(locations);

            LogMessageContainer oldContainer = containers.putIfAbsent(containerKey, container);

            // initialize the container
            if (oldContainer == null) {
                container.init();
            }
        }
    }

    /**
     * Retrieve the log message
     */
    public static LogMessage getLogMessage(String containerKey, String messageId) {
        LogMessageContainer container = containers.get(containerKey);

        if (container == null) {
            throw new IllegalArgumentException("Not found the log message container: " + containerKey);
        }

        return container.getLogMessage(messageId);
    }

    public static String getFormatedMessage(String containerKey, int messageId, Object...args) {
        return getFormatedMessage(containerKey, messageId + "", args);
    }

    public static String getFormatedMessage(String containerKey, long messageId, Object...args) {
        return getFormatedMessage(containerKey, messageId + "", args);
    }

    /**
     * Retrieve the formated log message
     */
    public static String getFormatedMessage(String containerKey, String messageId, Object...args) {

        LogMessage message = getLogMessage(containerKey, messageId);
        String notFoundMsg = "NotFound the msg text(" + messageId + ")";
        if (message == null) {
            return notFoundMsg;
        }

        if (message.getText() == null) {
            return notFoundMsg;
        }

        if (args == null) {
            return message.getText();
        }
        FormatedLogMessage fMessage = new FormatedLogMessage(message.getText(), args);

        String strMsg = fMessage.formatedMsg;

        if (fMessage.t != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            fMessage.t.printStackTrace(pw);

            strMsg += "\r\n" + sw;
        }

        return strMsg;
    }
}
