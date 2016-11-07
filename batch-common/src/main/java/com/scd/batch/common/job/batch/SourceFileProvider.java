package com.scd.batch.common.job.batch;

import com.scd.batch.common.job.util.FileIteratorReader;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SourceFileProvider implements SourceDataProvider {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /** File reader */
    private FileIteratorReader reader;
    
    /** Source file DIR */
    private final File sourceDir;
    /** Source file desc */
    private final String sourceFileName;
    /** Read file lines */
    private final int batchSize;    
    
    /**
     * Constructor with fields
     * 
     * @param sourceDir
     * @param sourceFileName
     * @param batchSize
     */
    public SourceFileProvider(File sourceDir, String sourceFileName, int batchSize) {
        this.sourceDir = sourceDir;
        this.sourceFileName = sourceFileName;
        this.batchSize = batchSize;
    }

    @Override
    public void init() {
        if (reader != null) {
            return;
        }

        File sourceFile = new File(sourceDir, sourceFileName);
        if (!sourceFile.exists()) {
            logger.warn("file: {} does not exist", sourceFile);
            return;
        }

        try {
            reader = new FileIteratorReader(sourceDir, sourceFileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> batchRead() {
        if (reader == null) {
            return Collections.emptyList();
        }
        
        try {
            Stopwatch sw = Stopwatch.createStarted();

            List<String> result = reader.nextLines(batchSize);

            logger.debug("batchRead {}, takes: {}", batchSize, sw.stop().elapsed(TimeUnit.MILLISECONDS));

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (reader != null) {
            reader.close();
            reader = null;
        }
    }

}
