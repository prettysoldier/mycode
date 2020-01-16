package source_code_java.java_.util.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量：类似于“令牌”，用AQS实现
 * 用于限流，实现各种资源池，比如公共资源比较紧缺，控制同时访问的最大线程数量
 *
 * 注意：Semaphore不是锁，只能限制同时访问资源的线程数，至于对数据一致性的控制，Semaphore是不关心的。
 * 如果是只有一个许可的Semaphore，可以当作锁使用。
 *
 * @author shuaijunhe
 * @create 2019/10/18
 * @description
 */
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

    private static class Pool {
        /**
         * 可同时访问资源的最大线程数
         */
        private static final int MAX_AVAILABLE = 100;

        private final Semaphore permits = new Semaphore(MAX_AVAILABLE, true);
        /**
         * 共享资源
         */
        protected Object[] items = new Object[MAX_AVAILABLE];

        protected boolean[] used = new boolean[MAX_AVAILABLE];

        /**
         * 获取资源
         * @return
         * @throws InterruptedException
         */
        public Object getItem() throws InterruptedException {
            permits.acquire();
            return getNextAvailableItem();
        }

        /**
         * 归还资源
         * @param x
         */
        public void putItem(Object x) {
            if (markAsUnused(x)){
                permits.release();
            }
        }
        private synchronized Object getNextAvailableItem() {
            for (int i = 0; i < MAX_AVAILABLE; ++i) {
                if (!used[i]) {
                    used[i] = true;
                    return items[i];
                }
            }
            return null;
        }
        private synchronized boolean markAsUnused(Object item) {
            for (int i = 0; i < MAX_AVAILABLE; ++i) {
                if (item == items[i]) {
                    if (used[i]) {
                        used[i] = false;
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }
    }
}
