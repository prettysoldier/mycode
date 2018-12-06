package test.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Desc Semaphore 是一个票池，用来控制某个资源只允许固定的线程使用
 * 公平锁和非公平锁两种
 * @Author shuaijunhe
 * @CreateTime 2018/12/6 11:45
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5, false);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int idx = index;
            exec.execute(() -> {

                for (; ; ) {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println(Thread.currentThread().getName() + "----Accessing---" + idx);
                        Thread.sleep(2000);
                        // 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
                        semp.release();
//                        System.out.println(Thread.currentThread().getName() + "----close----" + idx);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 退出线程池
        exec.shutdown();
    }
}