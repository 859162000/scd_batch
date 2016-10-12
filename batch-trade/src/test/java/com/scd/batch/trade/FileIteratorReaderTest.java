package com.scd.batch.trade;

import com.scd.batch.common.job.util.FileIteratorReader;
import junit.framework.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by baidu on 16/4/29.
 */
public class FileIteratorReaderTest {

    /**
     *
     */
    @Test
    public void testException() {

        try {
            new FileIteratorReader(null, null);
            Assert.assertFalse(true);
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                Assert.assertFalse(true);
            }
        }

        try {
            File dir = new File("aaa");
            new FileIteratorReader(dir, "aaa");
            Assert.assertFalse(true);
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                Assert.assertFalse(true);
            }
        }

        try {
            File dir = new File("/var");
            new FileIteratorReader(dir, "aaa");
            Assert.assertFalse(true);
        } catch (Exception e) {
            if (!(e instanceof IllegalArgumentException)) {
                Assert.assertFalse(true);
            }
        }
    }
}
