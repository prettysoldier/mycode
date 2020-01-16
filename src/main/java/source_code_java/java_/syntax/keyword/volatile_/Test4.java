package source_code_java.java_.syntax.keyword.volatile_;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        final Test4 test = new Test4();
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