package com.scd.batch.common.uid.buffer;

@FunctionalInterface
public interface RejectedPutBufferHandler {

    /**
     * Reject put buffer request
     * 
     * @param ringBuffer
     * @param uid
     */
    void rejectPutBuffer(RingBuffer ringBuffer, long uid);
}
