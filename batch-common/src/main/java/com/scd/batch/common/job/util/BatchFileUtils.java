package com.scd.batch.common.job.util;

import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.job.batch.control.JobControl;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.google.common.base.Joiner;

import java.io.File;

/**
 * Created on 10:18 04/01/2016.
 *
 * @author <a href="mailto:songguo@baidu.com">songguo</a>
 */
public class BatchFileUtils {

    public static String getDirPath(String root, ExecutorContext context) {
        return new File(root, context.getAttach(ShortDate.class).toString()).toString();
    }

    public static String getFileName(String prefix, JobControl control) {
        String dbId = control.getDatabaseId();
        String tableId = control.getTableId();

        return Joiner.on("_").join(prefix, dbId, tableId);
    }

}
