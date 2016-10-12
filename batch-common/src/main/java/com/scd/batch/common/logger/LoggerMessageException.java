package com.scd.batch.common.logger;

public class LoggerMessageException extends RuntimeException {
    private static final long serialVersionUID = 173658773696463429L;

    public LoggerMessageException(String msg) {
        super(msg);
    }
}
