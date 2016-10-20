package com.scd.batch.common.job.batch;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 流水仓库
 */
public class TransferRepo {

    /**
     * key, transferID
     * Value, List<Entity>
     */
    public static ConcurrentHashMap<String, String> repo = new ConcurrentHashMap<>();
}
