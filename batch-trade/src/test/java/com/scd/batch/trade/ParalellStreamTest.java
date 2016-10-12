package com.scd.batch.trade;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baidu on 16/5/28.
 */
public class ParalellStreamTest {

    @Test
    public void testParallelStream() {

        final List<Double> nums = new ArrayList<>(1000 - 000 - 000);
        //
        for (int i = 0; i < nums.size(); i++) {
            nums.add(Math.random());
        }

        long s = System.nanoTime();

        nums.forEach(n -> {
            //            for (int i = 0; i < 100; i++) {
            double d = (n) * (19.87);
            //            }
        });

        long t = System.nanoTime();

        nums.parallelStream().forEach(n -> {
            //            for (int i = 0; i < 100; i++) {
            double d = (n) * (19.87);
            //            }
        });

        long t2 = System.nanoTime();

        System.out.printf("Used time:%d ms,%d ms\n", (int) ((t - s) / 1e6), (int) ((t2 - t) / 1e6));
    }
}
