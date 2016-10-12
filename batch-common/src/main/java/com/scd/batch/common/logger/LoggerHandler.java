package com.scd.batch.common.logger;

import com.scd.batch.common.exception.ApplicationException;
import com.scd.batch.common.exception.SystemException;
import org.slf4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggerHandler implements InvocationHandler {
    /**
     * Target logger
     */
    private final Logger target;
    /**
     * Proxy logger
     */
    private final FLogger proxy;
    /**
     * throws the message missing error?
     */
    private final boolean throwMessageMissingError;

    public LoggerHandler(Logger target) {
        this.target = target;

        Class<?>[] targetInterfaces = target.getClass().getInterfaces();
        Class<?>[] proxyInterfaces = new Class<?>[target.getClass().getInterfaces().length + 1];

        System.arraycopy(targetInterfaces, 0, proxyInterfaces, 0, targetInterfaces.length);
        proxyInterfaces[proxyInterfaces.length - 1] = FLogger.class;

        this.proxy = (FLogger) Proxy.newProxyInstance(target.getClass().getClassLoader(), proxyInterfaces, this);

        // check if throws exception when message is missing
        this.throwMessageMissingError = Boolean.valueOf(System.getProperty("throwMessageMissingError", "false"));
    }

    public FLogger getFLogger() {
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String mName = method.getName();

        // ignore all non-log methods
        if (!mName.equals("log")) {
            return method.invoke(target, args);
        }

        // all parameter types
        Class<?>[] paramTypes = method.getParameterTypes();

        // main parameter type
        Class<?> firstParamType = paramTypes[0];

        String messageId = null;
        Object[] logArgs = null;

        if (firstParamType == String.class) {
            messageId = (String) args[0];
            if (args.length > 1) {
                logArgs = (Object[]) args[1];
            }
        } else if (firstParamType == ApplicationException.class) {
            ApplicationException e = (ApplicationException) args[0];
            if (e != null) {
                messageId = e.messageId;
                logArgs = e.args;
            }
        } else if (firstParamType == SystemException.class) {
            SystemException e = (SystemException) args[0];
            if (e != null) {
                messageId = e.messageId;
                logArgs = e.args;
            }
        }

        // retrieved the log message
        LogMessage logMessage = LogMessageContainers.getLogMessage(LogMessageContainers.MESSAGE_KEY_LOG, messageId);

        if (logMessage == null) {

            String errMsg = "Missing the message id:" + messageId;

            if (throwMessageMissingError) {
                throw new IllegalStateException(errMsg);
            }

            // log the error message to standard error
            System.err.println(errMsg);

        } else {
            log(logMessage, logArgs);
        }

        return null;
    }

    private void log(LogMessage logMessage, Object[] args) {

        FormatedLogMessage fMessage = new FormatedLogMessage(logMessage.getText(), args);

        String level = logMessage.getLevel();
        if (level == null) {
            level = "DEBUG";
        }

        String logMsg = logMessage.getId() + " " + fMessage.formatedMsg;

        if (level.equals("TRACE")) {
            target.trace(logMsg, fMessage.t);
        } else if (level.equals("DEBUG")) {
            target.debug(logMsg, fMessage.t);
        } else if (level.equals("WARN")) {
            target.warn(logMsg, fMessage.t);
        } else if (level.equals("INFO")) {
            target.info(logMsg, fMessage.t);
        } else if (level.equals("ERROR")) {
            target.error(logMsg, fMessage.t);
        } else if (level.equals("ALARM")) {

        }
    }
}
