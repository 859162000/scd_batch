package com.scd.batch.common.job.command;

public enum CommandType {
    JOB;

    public static CommandType typeOf(String name) {
        try {
            return CommandType.valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }
}
