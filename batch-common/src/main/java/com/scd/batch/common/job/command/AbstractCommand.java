package com.scd.batch.common.job.command;

import com.google.common.base.Preconditions;
import org.springframework.context.ApplicationContext;

public abstract class AbstractCommand implements Command {
    /**
     * Command name
     */
    private String name;
    /**
     * Command type
     */
    private CommandType commandType;
    /**
     * Command options
     */
    protected CommandOptions commandOptions;
    /**
     * Application context
     */
    protected ApplicationContext context;

    public AbstractCommand(CommandOptions commandOptions, ApplicationContext context) {
        Preconditions.checkState(commandOptions != null, "Missing command options!");
        this.commandOptions = commandOptions;
        this.name = commandOptions.getJobName();
        this.commandType = commandOptions.getCommandType();
        this.context = context;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setType(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public CommandType getType() {
        return commandType;
    }

    @Override
    public void setCommandOptions(CommandOptions commandOptions) {
        this.commandOptions = commandOptions;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }
}
