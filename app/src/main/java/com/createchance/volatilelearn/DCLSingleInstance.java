package com.createchance.volatilelearn;

/**
 * DCL 为什么需要 volatile？
 *
 * @author createchance
 * @since 2020/5/24
 */
public class DCLSingleInstance {

    private static volatile DCLSingleInstance INSTANCE;

    private DCLSingleInstance() {
        // never new this instance.
    }

    public static DCLSingleInstance getInstance() {
        if (INSTANCE == null) {
            synchronized (DCLSingleInstance.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DCLSingleInstance();
                }
            }
        }

        return INSTANCE;
    }

    // other methods.
}
