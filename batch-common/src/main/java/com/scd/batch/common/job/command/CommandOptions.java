package com.scd.batch.common.job.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class CommandOptions implements Serializable {
    private static final long serialVersionUID = -5674475044499539614L;

    /** CommandLine */
    private CommandLine cmd;
    /** CommandLine options, used for print usage */
    private Options options;

    /** CommanType */
    private CommandType commandType;
    /** Job desc */
    private String jobName;
    /** Spring config path */
    private String springConfigPath;

    /** Whether force exit, ignore user thread is still working */
    private boolean forceExit;

    /**
     * Default constructor
     */
    public CommandOptions() {
    }

    /**
     * Print usage of {@link BatchLauncher} command options
     */
    public void printUsage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Batch launcher command options", options);
    }

    /**
     * Has an option for help (-h)
     * 
     * @return
     */
    public boolean hasHelpOption() {
        return cmd.hasOption("h");
    }

    /**
     * Getters & Setters
     */
    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public CommandLine getCmd() {
        return cmd;
    }

    public void setCmd(CommandLine cmd) {
        this.cmd = cmd;
    }

    public String getSpringConfigPath() {
        return springConfigPath;
    }

    public void setSpringConfigPath(String springConfigPath) {
        this.springConfigPath = springConfigPath;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public boolean isForceExit() {
        return forceExit;
    }

    public void setForceExit(boolean forceExit) {
        this.forceExit = forceExit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(commandType).append(jobName).append(springConfigPath).append(forceExit)
                .toString();
    }

}