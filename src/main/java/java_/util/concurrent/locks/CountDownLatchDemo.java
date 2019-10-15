package java_.util.concurrent.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AQS的共享功能，通过钩子方法tryAcquireShared暴露，与独占功能最主要的区别就是：
 *
 * 共享功能的结点，一旦被唤醒，会向队列后部传播（Propagate）状态，以实现共享结点的连续唤醒。
 * 这也是共享的含义，当锁被释放时，所有持有该锁的共享线程都会被唤醒，并从等待队列移除。
 *
 * @Author shuaijunhe
 * @CreateTime 2018/12/6 11:45
 **/
public class CountDownLatchDemo {



    public static void main(String[] args) {
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService =  Executors.newFixedThreadPool(6);

        for(int i = 0; i < count; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "等待...");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "...出发");

            });
        }
        for(int i = 0; i < count; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "...");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "...over");

            });
        }

    }
}
