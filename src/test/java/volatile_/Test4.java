package test.java.volatile_;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1000000
 * 32
 *
 * @author Shuaijun He
 */
public class Test4 {
    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        this.inc.getAndIncrement();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Test4 test = new Test4();
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        test.increase();
                    }
                };
            }.start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        /**
         * 10000000
         * 276
         */
        System.out.println(test.inc);
        System.out.println(System.currentTimeMillis() - start);
    }
}