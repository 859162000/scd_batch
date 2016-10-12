package com.scd.batch.common.uid;


import com.scd.batch.common.uid.exception.UidGenerateException;

public interface UidGenerator {

    long getUID() throws UidGenerateException;

    String parseUID(long uid);

}
