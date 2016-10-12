package com.scd.batch.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public abstract class ZipUtils {
    private static final int BUFFER_SIZE = 1024;

    /**
     * Zip a list of files to the destination outputStream.
     * 
     * @param src A list of File object to be zipped
     * @param dst The destination OutputStream
     * @param prefix addition parent path for the src files. <p> 
     *        For example. src files are 1.jpg, 2.jpg; dst file is Pic.zip; prefix is "seven"; 
     *        When you unzip the generated Pic.zip, you will find all jpgs are in the parent direction named "seven"
     * @param recursive something like Linux option -r
     * @throws IOException
     */
    public static void zip(File[] src, OutputStream dst, String prefix, boolean recursive) throws IOException {
        // pre-checking
        Assert.isTrue(ArrayUtils.isNotEmpty(src), "parameter src is an empty array");
        Assert.notNull(dst, "parameter outStream is null");

        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(dst);
            String formatPrefix = formatPrefix(prefix);
            for (File eachSrcFile : src) {
                zip0(eachSrcFile, zos, formatPrefix, recursive);
            }

            zos.finish();
        } finally {
            IOUtils.closeQuietly(zos);
        }
    }
    
    /**
     * Zip to the destination outputStream, with no prefix no recursive as default
     */
    public static void zip(File[] src, OutputStream dst) throws IOException {
        zip(src, dst, null, false);
    }

    /**
     * Zip using file as the destination OutputStream
     */
    public static void zip(File[] src, File dst, String prefix, boolean recursive) throws IOException {
        zip(src, new FileOutputStream(dst), prefix, recursive);
    }
    
    /**
     * Zip with no prefix by default
     */
    public static void zip(File[] src, File dst, boolean recursive) throws IOException {
        zip(src, dst, null, recursive);
    }

    /**
     * Zip with no prefix, not recursive mode by default
     */
    public static void zip(File[] src, File dst) throws IOException {
        zip(src, dst, false);
    }

    /**
     * Unzip the src file to the destination directory.<BR>
     * The destination directory will be created if it does NOT exist
     * 
     * @param src The source file to be unziped
     * @param dstDir The destination directory
     * @throws IOException If an I/O error has occurred 
     * @return only the real files
     */
    public static List<File> unzip(File src, File dstDir) throws IOException {
        // pre-checking
        Assert.notNull(src, "parameter src can not be null");
        Assert.isTrue(src.exists(), "parameter src can not be null");
        Assert.notNull(dstDir, "parameter dstDir can not be null");

        if (dstDir.exists()) {
            Assert.isTrue(dstDir.isDirectory(), "parameter dstDir must be a existing directory");
        } else {
            Assert.isTrue(dstDir.mkdirs(), "can not make the destination dir: " + dstDir.getAbsolutePath());
        }

        List<File> resultList = new LinkedList<File>();
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream(src));

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                // Directory zipEntry: mkdir if not exists
                if (entry.isDirectory()) {
                    File dir = new File(dstDir.getAbsolutePath() + File.separator + entry.getName());
                    if (!dir.exists() && !dir.mkdirs()) {
                        throw new IOException("can not make the dir: " + dir.getAbsolutePath());
                    }

                    // Normal file zipEntry: mkdir parent directory then write the file
                } else {
                    File file = new File(dstDir.getAbsolutePath() + File.separator + entry.getName());
                    FileOutputStream output = FileUtils.openOutputStream(file);

                    try {
                        IOUtils.copy(zis, output);
                        output.close();
                    } finally {
                        IOUtils.closeQuietly(output);
                    }

                    resultList.add(file);
                }
            }

        } finally {
            IOUtils.closeQuietly(zis);
        }

        return resultList;
    }

    /**
     * Zip a file to the ZipOutputStream.
     */
    private static void zip0(File src, ZipOutputStream dst, String prefix, boolean recursive) throws IOException {
        // For the directory, we will think about the recursive mode (something like -r)
        if (src.isDirectory()) {
            if (!recursive) {
                return;
            }

            File[] files = src.listFiles();
            if (ArrayUtils.isEmpty(files)) {
                dst.putNextEntry(new ZipEntry(prefix + src.getName() + "/"));

            } else {
                prefix += src.getName() + File.separator;
                for (File childFile : files) {
                    zip0(childFile, dst, prefix, recursive);
                }
            }

            // For the actual file, we write the ZipEntry directly
        } else {
            FileInputStream fis = null;
            try {
                dst.putNextEntry(new ZipEntry(prefix + src.getName()));

                fis = new FileInputStream(src);
                byte[] buffer = new byte[BUFFER_SIZE];

                int numRead = 0;
                while ((numRead = fis.read(buffer)) != -1) {
                    dst.write(buffer, 0, numRead);
                }

                dst.flush();
            } finally {
                IOUtils.closeQuietly(fis);
            }
        }
    }

    /**
     * Format prefix. End with "/" if not empty
     */
    private static String formatPrefix(String prefix) {
        String formatRes = prefix;
        if (StringUtils.isBlank(formatRes)) {
            formatRes = "";
        } else if (!formatRes.endsWith("/")) {
            formatRes += "/";
        }

        return formatRes;
    }

}
