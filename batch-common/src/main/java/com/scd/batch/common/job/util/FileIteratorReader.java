package com.scd.batch.common.job.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * The helper class for batch file operations
 */
public class FileIteratorReader implements Closeable {

    /**
     * Read file
     */
    private final File dir;
    /**
     * File desc
     */
    private final String fileName;
    /**
     * Reader
     */
    private BufferedReader br;

    public FileIteratorReader(File dir, String fileName) throws Exception {
        if (dir == null || fileName == null) {
            throw new IllegalArgumentException("Invalidate dir or fileName!");
        }

        if (!dir.exists()) {
            throw new IllegalArgumentException("dir " + dir + " is not exists!");
        }

        File file = new File(dir, fileName);

        if (!file.exists()) {
            throw new IllegalArgumentException("file " + file + " is not exists!");
        }

        if (!file.canRead()) {
            throw new IllegalArgumentException("file " + file + " is can't read!");
        }

        this.dir = dir;

        this.fileName = fileName;

        // check the md5
        checkMD5(file);

        Reader fileReader = new InputStreamReader(new FileInputStream(file), "UTF-8") ; 
        this.br = new BufferedReader(fileReader, 1024 * 1024);
    }

    /**
     * Check the file md5
     */
    private void checkMD5(File file) throws Exception {

        File md5File = new File(dir, fileName + ".md5");

        if (!md5File.exists()) {
            throw new IllegalArgumentException("file " + md5File + " is not exists!");
        }

        if (!md5File.canRead()) {
            throw new IllegalArgumentException("file " + md5File + " is can't read!");
        }

        // read the public md5
        String md5 = FileUtils.readFileToString(md5File, "UTF-8");

        // calculate the real md5
        String calMd5 = MD5.generateMD5(file);

        if (!md5.equals(calMd5)) {
            throw new IllegalStateException("The md5 of file " + file + " is not matched with " + md5File);
        }
    }

    public List<String> nextLines(int limit) throws IOException {
        List<String> lines = new ArrayList<>(limit);

        String line;
        int count = 0;

        while (count++ < limit && (line = br.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    @Override
    public void close() {
        IOUtils.closeQuietly(br);
    }
}
