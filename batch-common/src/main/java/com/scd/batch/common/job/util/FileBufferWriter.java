package com.scd.batch.common.job.util;

import org.apache.commons.io.FileUtils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 使用FileChannel
 * <p>
 * The helper class for batch file writer
 */
public class FileBufferWriter implements Closeable {
    /**
     * Default max flush buffer size
     */
    private static final int DEFAULT_MAX_BUFFER_SIZE = 128 * 1024 * 1024;

    /**
     * File object
     */
    private File file;

    /**
     * Max flush buffer size
     */
    private int maxBufferSize;
    /**
     * Buffered size
     */
    private int bufferSize;
    /**
     * Buffered writer
     */
    private final List<String> buffer = new ArrayList<>();

    /**
     * Constructor with default buffer size
     */
    public FileBufferWriter(File dir, String fileName) {
        this(dir, fileName, DEFAULT_MAX_BUFFER_SIZE);
    }

    /**
     * Constructor with buffer size
     */
    public FileBufferWriter(File dir, String fileName, int maxBufferSize) {
        this(new File(dir, fileName), maxBufferSize);
    }

    /**
     * Constructor with buffer size
     */
    public FileBufferWriter(File file, int maxBufferSize) {
        this.file = file;
        this.maxBufferSize = maxBufferSize;
    }

    public void writeLines(List<String> lines) throws Exception {
        if (lines != null) {
            for (String line : lines) {
                writeLine(line);
            }
        }
    }

    /**
     * Write line with buffer
     */
    public void writeLine(String line) throws Exception {

        if (line == null || line.isEmpty()) {
            return;
        }

        // calculate the lines size
        bufferSize += line.length();

        if (bufferSize >= maxBufferSize) {

            // write lines to file
            FileUtils.writeLines(file, "UTF-8", buffer, true);

            // clear the buffer size
            bufferSize = 0;

            // clear buffer
            buffer.clear();

        } else {
            // buffer all lines
            buffer.add(line);
        }
    }

    @Override
    public void close() throws IOException {
        if (!buffer.isEmpty()) {

            // write lines to file
            FileUtils.writeLines(file, "UTF-8", buffer, true);

            buffer.clear();

            bufferSize = 0;
        }
    }
}
