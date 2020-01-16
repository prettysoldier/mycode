package java_.syntax.keyword.volatile_;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 问题不在volatile，而在于++ 是非原子的
 */
public class Test {
    public volatile int inc = 0;

    public void increase() {
        this.inc++;
    }

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        final Test test = new Test();
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