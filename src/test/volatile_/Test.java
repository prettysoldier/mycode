package test.volatile_;

public class Test {
    public volatile int inc = 0;

    public void increase() {
        this.inc++;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final Test test = new Test();
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
         * 2709567
         * 224
         */
        System.out.println(test.inc);
        System.out.println(System.currentTimeMillis() - start);
    }
}