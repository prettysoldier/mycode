package source_code_java.java_.syntax.keyword.volatile_;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        final Test3 test = new Test3();
        int nums = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(nums);
        CountDownLatch latch = new CountDownLatch(nums);
        for (int i = 0; i < nums; i++) {
            executorService.submit(()->{
                for (int j = 0; j < 1000; j++) {
                    test.increase();
                }
                latch.countDown();
            });
        }
        latch.await();

        System.out.println(test.inc);
        System.out.println(System.currentTimeMillis() - start);
    }
}