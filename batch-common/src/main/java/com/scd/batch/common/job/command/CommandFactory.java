package com.scd.batch.common.job.command;

import org.springframework.context.ApplicationContext;

public class CommandFactory {

    /**
     * Retrieve the command instance
     */
    public static Command newCommand(CommandOptions commandOptions, ApplicationContext context) {
        return new JobCommand(commandOptions, context);
    }
}
