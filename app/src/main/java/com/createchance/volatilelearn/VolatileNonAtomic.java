package com.createchance.volatilelearn;

/**
 * @author createchance
 * @since 2020/5/29
 */
public class VolatileNonAtomic {
    private volatile long value = 0;

    private static final long CYCLE = 1_0000_0000;

    public void test() throws InterruptedException {
        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE; i++) {
                    value++;
                }
            }
        });

        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < CYCLE; i++) {
                    value++;
                }
            }
        });

        T1.start();
        T2.start();
        T1.join();
        T2.join();
        System.out.println("Final value: " + value);
    }
}
