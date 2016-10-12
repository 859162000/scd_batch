package com.scd.batch.common.job.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * The utils for calculate big file md5
 */
public class MD5 {

    private static final int maxBufferSize = 128 * 1024 * 1024;

    /**
     * Generate the md5 for file
     */
    public static String generateMD5(File f) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance("md5");
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(f);

            // file length
            long remainSize = f.length();
            long position = 0;
            do {

                // mapped file with max size for avoiding OOM
                MappedByteBuffer byteBuffer = fin.getChannel()
                        .map(FileChannel.MapMode.READ_ONLY,
                                position,
                                remainSize > maxBufferSize ? maxBufferSize : remainSize);

                md5.update(byteBuffer);

                remainSize -= maxBufferSize;

                position += maxBufferSize;
               
            } while (remainSize > 0);

            return Hex.encodeHexString(md5.digest());

        } finally {
            IOUtils.closeQuietly(fin);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(generateMD5(new File("/tmp/LOAN_RECONCILIATION_0_12")));
    }
}
