package java_.volatile_;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 {
    public int inc = 0;
    Lock lock = new ReentrantLock();

    public void increase() {
        this.lock.lock();
        try {
            this.inc++;
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Test3 test = new Test3();
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        test.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        /**
         * 10000000
         * 339
         */
        System.out.println(test.inc);
        System.out.println(System.currentTimeMillis() - start);
    }
}