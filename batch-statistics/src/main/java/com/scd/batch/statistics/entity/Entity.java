package com.scd.batch.statistics.entity;

import java.util.Date;

public class Entity {
    /**
     * Entity unique id (table unique)
     */
    private long id;
    /**
     * Entity unique partition id(global unique)
     */
    private long partitionId;
    /**
     * Optimistic lock version, index from 0
     */
    private int version;
    /**
     * Created time
     */
    private Date created;
    /**
     * Last modified
     */
    private Date modified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(long partitionId) {
        this.partitionId = partitionId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
