package com.createchance.volatilelearn;

/**
 * 研究手动填充 cache line 对性能的影响
 *
 * @author createchance
 * @since 2020/5/24
 */
public class CacheLinePadding03 {
    private final long CYCLE_TIMES = 10_0000_0000L;

    private static class Inner {
        private long p1, p2, p3;
        private byte p4;
        volatile long value = 0L;
    }

    private Inner[] array = new Inner[2];

    public CacheLinePadding03() {
        array[0] = new Inner();
        array[1] = new Inner();
    }

    public void test() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE_TIMES; i++) {
                    array[0].value = i;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE_TIMES; i++) {
                    array[1].value = i;
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
