package com.scd.batch.common.logger;

import org.apache.commons.digester.Digester;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.HashMap;
import java.util.Map;

public class LogMessageContainer {

    /**
     * Default location
     */
    private static final String[] DEFAULT_LOCATION = {"classpath*:META-INF/log/*.log.xml",
            "classpath*:META-INF/log/*.message.xml", "classpath*:META-INF/log/*_cfg.properties"};
    /**
     * Configuration files
     */
    private final String[] configLocations;

    /**
     * Messages
     */
    private final Map<String, LogMessage> messages = new HashMap<>();

    /**
     * Constructor
     */
    public LogMessageContainer(String... configLocations) {
        this.configLocations = configLocations;
    }

    /**
     * Initialize
     */
    public void init() throws Exception {

        // sprint path resolver
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        for (String location : configLocations) {

            // loadDate resources by location
            Resource[] resources = resolver.getResources(location);

            // parse all resources
            for (Resource resource : resources) {
                parseResource(resource);
            }
        }
    }

    /**
     * Load the {@link Resource} as the log message
     */
    private void parseResource(Resource resource) throws Exception {

        // create digester
        Digester digester = createDigester();

        // push this to stackø
        digester.push(this);

        // parse the resource
        digester.parse(resource.getInputStream());
    }

    private Digester createDigester() {
        Digester digester = new Digester();

        digester.addObjectCreate("messages/message", LogMessage.class);
        digester.addSetProperties("messages/message", "id", "id");
        digester.addSetProperties("messages/message", "level", "level");
        digester.addSetProperties("messages/message", "text", "text");

        digester.addSetNext("messages/message", "addLogMessage");
        return digester;
    }

    /**
     * The method only be invoked by digester
     */
    public void addLogMessage(LogMessage message) {
        if (messages.containsKey(message.getId())) {
            throw new IllegalArgumentException("The message is duplicated.[" + message.getId() + ","
                    + message.getLevel() + "," + message.getText() + "]");
        }

        messages.put(message.getId(), message);
    }

    public LogMessage getLogMessage(String id) {
        return messages.get(id);
    }

}
