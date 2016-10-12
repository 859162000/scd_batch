package com.scd.batch.trade.schedule;

import com.scd.batch.common.job.BatchLauncher;
import org.junit.Test;

public class BatchLauncherTest {

    /**
     * Execption case 1: Args is null
     */
    @Test(expected = NullPointerException.class)
    public void testException4NullArgs() {
        BatchLauncher.main(null);
    }

    /**
     * Execption case 2: Invalid command option for -a, -s
     */
    @Test(expected = RuntimeException.class)
    public void testException4InvalidOption() {
        BatchLauncher.main(new String[] { "-a", "-s", "-h" });
    }

    /**
     * Execption case 3: Invalid command type
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException4MissingCommandType() {
        BatchLauncher.main(new String[] { "-n", "prepare", "-t", "fake_type" });
    }

    /**
     * Execption case 4: Invalid job name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException4InvalidName() {
        BatchLauncher.main(new String[] { "-n", "fake_name" });
    }

    /**
     * Usage print case
     */
    @Test()
    public void testUsagePrint() {
        BatchLauncher.main(new String[] { "-h" });
    }

//    /**
//     * Success case
//     */
//    @Test()
//    public void testProcessJobSuccess() {
//        BatchLauncher.main(new String[] { "-n", "prepare", "-p", "classpath:META-INF/spring/batch.xml", "-f" });
//    }

}
