package com.scd.batch.common.job.command;

import com.google.common.base.Preconditions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandOptionsBuilder {
    private static final String DEFAULT_CONFIG_PATH = "classpath:META-INF/spring/batch.xml";

    /**
     * Hide constructor
     */
    private CommandOptionsBuilder() {
    }

    /**
     * Build {@link CommandOptions} with the args from main() method
     * 
     * @param args
     * @return
     */
    public static CommandOptions build(String[] args) {
        // define command line options
        Options options = new Options();
        options.addOption("h", false, "Batch launcher help");
        options.addOption("p", true, "Path of the spring config, default as " + DEFAULT_CONFIG_PATH);
        options.addOption("t", true, "Type of command default as JOB. " + Arrays.toString(CommandType.values()));
        options.addOption("n", true, "Name of job. " + getJobNameDesc());
        options.addOption("f", false, "Force exit at the end, ignore user thread is still working. ");

        // parse command line with args
        CommandLine cmd = null;
        try {
            cmd = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // -n (Job name)
        String jobName = cmd.getOptionValue('n');
        JobCommand.Job job = JobCommand.Job.nameOf(jobName);

        // -t (Command type)
        String commandTypeStr = cmd.getOptionValue('t', CommandType.JOB.toString()).toUpperCase();
        CommandType commandType = CommandType.typeOf(commandTypeStr);

        // build CommandOptions
        CommandOptions commandOptions = new CommandOptions();
        commandOptions.setCmd(cmd);
        commandOptions.setOptions(options);
        commandOptions.setJobName(jobName);
        commandOptions.setSpringConfigPath(cmd.getOptionValue('p', DEFAULT_CONFIG_PATH));
        commandOptions.setCommandType(commandType);
        commandOptions.setForceExit(cmd.hasOption('f'));

        // if -h is not specified, check -n (Job name) -t (Command type) is valid
        if (!commandOptions.hasHelpOption()) {
            Preconditions.checkArgument(commandType != null, "Missing command type!");
            Preconditions.checkArgument(job != null, "Missing job name!");
        }

        return commandOptions;
    }

    /**
     * get job name description
     */
    private static String getJobNameDesc() {
        List<String> jobs = new ArrayList<>();
        for (JobCommand.Job job : JobCommand.Job.values()) {
            jobs.add(job.name);
        }

        return jobs.toString();
    }

}