package com.scd.batch.common.job.command;

import org.springframework.context.ApplicationContext;

public interface Command extends Runnable {
    /**
     * Set the command name
     */
    void setName(String name);

    /**
     * Retrieve the command name
     */
    String getName();

    /**
     * Set the command type
     */
    void setType(CommandType commandType);

    /**
     * Retrieve the command type
     */
    CommandType getType();

    /**
     * Set the command options
     */
    void setCommandOptions(CommandOptions commandOptions);

    /**
     * Bound the application context
     */
    void setApplicationContext(ApplicationContext context);

    /**
     * Validate options
     *
     * @throws IllegalArgumentException if options are invalidate
     */
    void validateOptions();
}
