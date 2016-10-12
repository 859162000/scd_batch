package com.scd.batch.common.logger;

import org.apache.log4j.Level;

/**
 * The structure for log message
 * 
 * @author chenguoqing
 */
public class LogMessage {
    /**
     * Message id
     */
    private String id;
    /**
     * Message level
     */
    private String level;
    /**
     * Message content
     */
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public Level getLog4jLevel() {
        return Level.toLevel(level);
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
