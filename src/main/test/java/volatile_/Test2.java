package main.test.java.volatile_;

public class Test2 {
    public int inc = 0;

    public synchronized void increase() {
        this.inc++;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Test2 test = new Test2();
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
         * 769
         */
        System.out.println(test.inc);
        System.out.println(System.currentTimeMillis() - start);
    }
}