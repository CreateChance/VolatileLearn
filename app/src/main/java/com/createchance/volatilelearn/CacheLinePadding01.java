package com.createchance.volatilelearn;

/**
 * 研究 CPU 缓存行对整体性能的影响
 *
 * @author createchance
 * @since 2020/5/24
 */
public class CacheLinePadding01 {
    private final long CYCLE_TIMES = 10_0000_0000L;

    private volatile long[] array = new long[2];

    public void test() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE_TIMES; i++) {
                    array[0] = i;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE_TIMES; i++) {
                    array[1] = i;
                }
            }
        });

        long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.nanoTime();
        System.out.println("Duration: " + (end - start) / 100_0000);
    }
}
