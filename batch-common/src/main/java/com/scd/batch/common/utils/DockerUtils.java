package com.scd.batch.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DockerUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DockerUtils.class);

    /** Environment param keys */
    private static final String ENV_KEY_HOST = "JPAAS_HOST";
    private static final String ENV_KEY_PORT = "JPAAS_HTTP_PORT";
    private static final String ENV_KEY_PORT_ORIGINAL = "JPAAS_HOST_PORT_8080";

    /** Docker host & port */
    private static String DOKER_HOST = "";
    private static String DOKER_PORT = "";

    /** Whether is docker */
    private static boolean IS_DOCKER;

    static {
        retrieveFromEnv();
    }

    /**
     * Retrieve docker host
     * 
     * @return empty string if not a docker
     */
    public static String getDockerHost() {
        return DOKER_HOST;
    }

    /**
     * Retrieve docker port
     * 
     * @return empty string if not a docker
     */
    public static String getDockerPort() {
        return DOKER_PORT;
    }

    /**
     * Whether a docker
     * 
     * @return
     */
    public static boolean isDocker() {
        return IS_DOCKER;
    }

    /**
     * Retrieve host & port from environment
     */
    private static void retrieveFromEnv() {
        // retrieve host & port from environment
        DOKER_HOST = System.getenv(ENV_KEY_HOST);
        DOKER_PORT = System.getenv(ENV_KEY_PORT);

        // not found from 'JPAAS_HTTP_PORT', then try to find from 'JPAAS_HOST_PORT_8080'
        if (StringUtils.isBlank(DOKER_PORT)) {
            DOKER_PORT = System.getenv(ENV_KEY_PORT_ORIGINAL);
        }

        boolean hasEnvHost = StringUtils.isNotBlank(DOKER_HOST);
        boolean hasEnvPort = StringUtils.isNotBlank(DOKER_PORT);

        // docker can find both host & port from environment
        if (hasEnvHost && hasEnvPort) {
            IS_DOCKER = true;

            // found nothing means not a docker, maybe an actual machine
        } else if (!hasEnvHost && !hasEnvPort) {
            IS_DOCKER = false;

        } else {
            LOGGER.error("Missing host or port from env for Docker. host:{}, port:{}", DOKER_HOST, DOKER_PORT);
            throw new RuntimeException(
                    "Missing host or port from env for Docker. host:" + DOKER_HOST + ", port:" + DOKER_PORT);
        }
    }

}
