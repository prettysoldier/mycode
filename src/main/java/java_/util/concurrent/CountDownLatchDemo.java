package java_.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Desc CountDownLatch 是某个线程忍耐n次, n=0表示忍不可忍了，继续执行
 * @Author shuaijunhe
 * @CreateTime 2018/12/6 11:45
 **/
public class CountDownLatchDemo {



    public static void main(String[] args) {
        int count = 6;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService executorService =  Executors.newFixedThreadPool(count);
        for(int i = 0; i < count; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "...");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "...over");

            });
        }
        System.out.println("等待所有工作都完成...");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有工作已完成，继续...");
    }
}
