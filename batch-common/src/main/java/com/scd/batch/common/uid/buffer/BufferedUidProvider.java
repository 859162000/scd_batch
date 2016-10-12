package com.scd.batch.common.uid.buffer;

import java.util.List;


@FunctionalInterface
public interface BufferedUidProvider {

    /**
     * Provides UID in the same one second
     * 
     * @param oneSecond
     * @return
     */
    List<Long> provide(long oneSecond);
}
