package com.scd.batch.common.uid.impl;

import com.google.common.base.Preconditions;
import com.scd.batch.common.uid.buffer.BufferPaddingExecutor;
import com.scd.batch.common.uid.buffer.RejectedPutBufferHandler;
import com.scd.batch.common.uid.buffer.RejectedTakeBufferHandler;
import com.scd.batch.common.uid.buffer.RingBuffer;
import com.scd.batch.common.uid.exception.UidGenerateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.ArrayList;
import java.util.List;

public class CachedUidGenerator extends DefaultUidGenerator implements DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(CachedUidGenerator.class);
    private static final int DEFAULT_BOOST_POWER = 3;

    /** Spring properties */
    private int boostPower = DEFAULT_BOOST_POWER;
    private int paddingFactor = RingBuffer.DEFUALT_PADDING_PERCENT;
    private Long scheduleInterval;
    
    private RejectedPutBufferHandler rejectedPutBufferHandler;
    private RejectedTakeBufferHandler rejectedTakeBufferHandler;

    /** RingBuffer */
    private RingBuffer ringBuffer;
    private BufferPaddingExecutor bufferPaddingExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        // initialize workerId & bitsAllocator
        super.afterPropertiesSet();
        
        // initialize RingBuffer & RingBufferPaddingExecutor
        this.initRingBuffer();
        LOGGER.info("Initialized RingBuffer successfully.");
    }
    
    @Override
    public long getUID() {
        try {
            return ringBuffer.take();
        } catch (Exception e) {
            LOGGER.error("Generate unique id exception. ", e);
            throw new UidGenerateException(e);
        }
    }

    @Override
    public String parseUID(long uid) {
        return super.parseUID(uid);
    }
    
    @Override
    public void destroy() throws Exception {
        bufferPaddingExecutor.shutdown();
    }

    /**
     * Get the UIDs in the same specified second under the max sequence
     * 
     */
    protected List<Long> nextIdsForOneSecond(long currentSecond) {
        // Initialize result list size of (max sequence + 1)
        int listSize = (int) bitsAllocator.getMaxSequence() + 1;
        List<Long> uidList = new ArrayList<>(listSize);

        // Allocate the first sequence of the second, the others can be calculated with the offset
        long firstSeqUid = bitsAllocator.allocate(currentSecond - FCORE_EPOCH, workerId, 0L);
        for (int offset = 0; offset < listSize; offset++) {
            uidList.add(firstSeqUid + offset);
        }

        return uidList;
    }
    
    /**
     * Initialize RingBuffer & RingBufferPaddingExecutor
     */
    private void initRingBuffer() {
        // initialize RingBuffer
        int bufferSize = ((int) bitsAllocator.getMaxSequence() + 1) << boostPower;
        this.ringBuffer = new RingBuffer(bufferSize, paddingFactor);
        LOGGER.info("Initialized ring buffer size:{}, paddingFactor:{}", bufferSize, paddingFactor);

        // initialize RingBufferPaddingExecutor
        boolean usingSchedule = (scheduleInterval != null);
        this.bufferPaddingExecutor = new BufferPaddingExecutor(ringBuffer, this::nextIdsForOneSecond, usingSchedule);
        if (usingSchedule) {
            bufferPaddingExecutor.setScheduleInterval(scheduleInterval);
        }
        
        LOGGER.info("Initialized BufferPaddingExecutor. Using schdule:{}, interval:{}", usingSchedule, scheduleInterval);
        
        // set rejected put/take handle policy
        this.ringBuffer.setBufferPaddingExecutor(bufferPaddingExecutor);
        if (rejectedPutBufferHandler != null) {
            this.ringBuffer.setRejectedPutHandler(rejectedPutBufferHandler);
        }
        if (rejectedTakeBufferHandler != null) {
            this.ringBuffer.setRejectedTakeHandler(rejectedTakeBufferHandler);
        }
        
        // fill in all slots of the RingBuffer
        bufferPaddingExecutor.paddingBuffer();
        
        // start buffer padding threads
        bufferPaddingExecutor.start();
    }

    /**
     * Setters for spring property
     */
    public void setBoostPower(int boostPower) {
        Preconditions.checkArgument(boostPower > 0, "Boost power must be positive!");
        this.boostPower = boostPower;
    }
    
    public void setRejectedPutBufferHandler(RejectedPutBufferHandler rejectedPutBufferHandler) {
        Preconditions.checkArgument(rejectedPutBufferHandler != null, "RejectedPutBufferHandler can't be null!");
        this.rejectedPutBufferHandler = rejectedPutBufferHandler;
    }

    public void setRejectedTakeBufferHandler(RejectedTakeBufferHandler rejectedTakeBufferHandler) {
        Preconditions.checkArgument(rejectedTakeBufferHandler != null, "RejectedTakeBufferHandler can't be null!");
        this.rejectedTakeBufferHandler = rejectedTakeBufferHandler;
    }

    public void setScheduleInterval(long scheduleInterval) {
        Preconditions.checkArgument(scheduleInterval > 0, "Schedule interval must positive!");
        this.scheduleInterval = scheduleInterval;
    }

}
