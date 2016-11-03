package com.scd.batch.executor.job;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.scd.batch.common.job.command.Command;
import com.scd.batch.common.job.command.CommandFactory;
import com.scd.batch.common.job.command.CommandOptions;
import com.scd.batch.common.job.command.CommandOptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class BatchLauncher {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchLauncher.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        Preconditions.checkNotNull(args, "BatchLauncher args can be null");
        LOGGER.info("BatchLauncher args: {}", Joiner.on(", ").join(args));

        // Build launcher options, if -h (help) is used, just print usage
        CommandOptions commandOptions = CommandOptionsBuilder.build(args);
        if (commandOptions.hasHelpOption()) {
            commandOptions.printUsage();
            return;
        }

        try {
            new BatchLauncher().process(commandOptions);
            LOGGER.info("BatchLauncher success for args: {}", Joiner.on(", ").join(args));

            // Force exit avoiding none-daemon thread working endless
            if (commandOptions.isForceExit()) {
                System.exit(0);
            }

        } catch (Throwable t) {
            LOGGER.error("BatchLauncher failed for args: " + Joiner.on(", ").join(args), t);

            // Exit with error
            System.exit(-1);
        }
    }

    private void process(CommandOptions commandOptions) {
        LOGGER.info("BatchLauncher command options: {}", commandOptions);

        // build command
        Command command = CommandFactory.newCommand(commandOptions, null);

        // validate options -> set application context -> run
        command.validateOptions();
        command.setApplicationContext(buildApplicationContext(commandOptions.getSpringConfigPath()));
        command.run();
    }

    /**
     * Build spring application context with a specified config path
     */
    private ApplicationContext buildApplicationContext(String springConfigPath) {
        // Load spring resource
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(springConfigPath);

        // Make sure the spring resource is existed
        if (!resource.exists()) {
            throw new IllegalArgumentException("Spring config path: " + springConfigPath + " does not exist!");
        }

        // build application context
        return new GenericXmlApplicationContext(resource);
    }

}
