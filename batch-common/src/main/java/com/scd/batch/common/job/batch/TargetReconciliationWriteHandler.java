package com.scd.batch.common.job.batch;

import com.google.common.base.Stopwatch;
import com.scd.batch.common.job.util.FileBufferWriter;
import com.scd.batch.common.job.util.MD5;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TargetReconciliationWriteHandler implements TargetReconcliationHandler {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private FileBufferWriter fileWriter;

    /**
     * Source file DIR
     */
    private final File targetDir;
    /**
     * Source file name
     */
    private final String targetFileName;

    /**
     * Constructor with fields
     *
     * @param targetDir
     * @param targetFileName
     */
    public TargetReconciliationWriteHandler(File targetDir, String targetFileName) {
        this.targetDir = targetDir;
        this.targetFileName = targetFileName;
    }

    @Override
    public void init() {
        if (fileWriter != null) {
            return;
        }

        fileWriter = new FileBufferWriter(targetDir, targetFileName);
    }

    @Override
    public void close() {
        if (fileWriter == null) {
            return;
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            logger.error("fileWriter.close() error", e);
        }
    }

    @Override
    public void clear() {
        // Delete existed target file & MD5 file
        FileUtils.deleteQuietly(new File(targetDir, targetFileName));
        FileUtils.deleteQuietly(new File(targetDir, targetFileName + ".md5"));
    }

    public void handle(TransferRepo transferRepo) {
        try {
            Stopwatch sw = Stopwatch.createStarted();

            int size = transferRepo.repo.size();

            transferRepo.repo.forEach((k, v) -> {
                try {
                    fileWriter.writeLine(v.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

//            fileWriter.writeLines(resultLines);

//            logger.debug("writeLines {}, takes: {}", size, sw.stop().elapsed(TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postHandler() {
        // writer close will trigger the file flush
        close();

        try {
            generateMD5();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate the md5 for file
     */
    private void generateMD5() throws Exception {
        File targetFile = new File(targetDir, targetFileName);
        if (!targetFile.exists()) {
            logger.warn("file: {} does not exist", targetFile);
            return;
        }

        File md5File = new File(targetFile.getParent(), targetFile.getName() + ".md5");
        String md5Value = MD5.generateMD5(targetFile);

        FileUtils.write(md5File, md5Value, "UTF-8");
    }

}
