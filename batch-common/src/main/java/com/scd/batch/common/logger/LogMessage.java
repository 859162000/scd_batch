package com.scd.batch.common.logger;


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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
