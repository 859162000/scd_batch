package com.scd.batch.common.job.executor;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ExecutorContext} represents a task execute context
 */
public class ExecutorContext {
    /**
     * Status enum
     */
    public enum Status {
        success,
        cancel,
        error,
    }

    /**
     * Execution Status
     */
    private Status status;
    /**
     * Attachments
     */
    private final Map<String, Object> attaches = new HashMap<>();

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Add attachment with name
     */
    public ExecutorContext addAttach(String name, Object attach) {
        this.attaches.put(name, attach);
        return this;
    }

    /**
     * Add attachment with name
     */
    public <T> ExecutorContext addAttach(Class<T> type, T attach) {
        return addAttach(type.getName(), attach);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttach(String name) {
        return (T) attaches.get(name);
    }

    public <T> T getAttach(Class<T> type) {
        return getAttach(type.getName());
    }
    
    /**
     * Remove attachment
     */
    public void removeAttach(Class<?> type) {
        removeAttach(type.getName());
    }
    
    public void removeAttach(String name) {
        attaches.remove(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("status", status)
                .append("attaches", attaches)
                .toString();
    }
}
