package com.createchance.volatilelearn;

/**
 * 研究观察 volatile 的读写性能
 *
 * @author createchance
 * @since 2020/5/24
 */
public class ReadWriteVolatilePref {
    private volatile int volatileValue = 0;
    private int plainValue = 0;

    public void test() {
        long start = System.nanoTime();
        for (int i = 0; i < 1000000000; i++) {
            volatileValue = i;
        }
        long end = System.nanoTime();
        System.out.println("Volatile write duration: " + (end - start) / 100_0000);

        start = System.nanoTime();
        for (int i = 0; i < 1000000000; i++) {
            int dump = volatileValue;
        }
        end = System.nanoTime();
        System.out.println("Volatile read duration: " + (end - start) / 100_0000);

        start = System.nanoTime();
        for (int i = 0; i < 1000000000; i++) {
            plainValue = i;
        }
        end = System.nanoTime();
        System.out.println("Plain write duration: " + (end - start) / 100_0000);

        start = System.nanoTime();
        for (int i = 0; i < 1000000000; i++) {
            int dump = plainValue;
        }
        end = System.nanoTime();
        System.out.println("Plain read duration: " + (end - start) / 100_0000);
    }
}
