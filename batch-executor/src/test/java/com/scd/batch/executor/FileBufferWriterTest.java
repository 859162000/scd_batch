package com.scd.batch.executor;

import com.scd.batch.common.job.util.FileBufferWriter;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test cases for {@link FileBufferWriter}
 */

public class FileBufferWriterTest {

    private File dir;

    @Before
    public void createDir() {
        String tmpdir = System.getProperty("java.io.tmpdir");

        this.dir = new File(tmpdir, "_" + this.getClass().getSimpleName() + "_" + System.currentTimeMillis());

        if (this.dir.exists()) {
            try {
                FileUtils.deleteDirectory(this.dir);
            } catch (IOException e) {
                // can't delete directory
                throw new RuntimeException("Can't delete the directory :" + this.dir.getAbsolutePath());
            }
        }

        this.dir.mkdir();
    }

    @Test
    public void testWriteBuffer() throws Exception {
        final String fileName = "_testWriteBuffer_";

        System.out.println("File:" + this.dir.getAbsolutePath() + "/" + fileName);

        // writer
        FileBufferWriter writer = new FileBufferWriter(dir, fileName, 1024);

        writer.writeLine(null);
        writer.writeLine("");

        for (int i = 0; i < 10000; i++) {
            String line = "This is a h2 line:" + i + "_" + Math.random();
            writer.writeLine(line);
        }

        List<String> lines = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            String line = "This is a h2 line:" + i + "_" + Math.random();
            lines.add(line);
        }

        writer.writeLines(lines);

        writer.close();
    }

    @After
    public void clearTmpFile() {
        if (this.dir != null && this.dir.exists()) {
            try {
                FileUtils.deleteDirectory(this.dir);
            } catch (IOException e) {
                // can't delete directory
                e.printStackTrace();
            }
        }
    }
}
