package com.createchance.volatilelearn;

/**
 * 学习 Contended 注解使用
 *
 * @author createchance
 * @since 2020/5/28
 */
public class CacheLinePadding04 {
    private final long CYCLE_TIMES = 10_0000_0000L;

    private static class Inner {
        @sun.misc.Contended
        volatile long contendedField = 0L;
    }

    private Inner[] array = new Inner[2];

    public CacheLinePadding04() {
        array[0] = new Inner();
        array[1] = new Inner();
    }

    public void test() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE_TIMES; i++) {
                    array[0].contendedField = i;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE_TIMES; i++) {
                    array[1].contendedField = i;
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

    public static void main(String[] args) throws InterruptedException {
        CacheLinePadding04 cacheLinePadding04 = new CacheLinePadding04();
        cacheLinePadding04.test();
    }
}
