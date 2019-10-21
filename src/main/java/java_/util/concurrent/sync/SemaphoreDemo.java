package java_.util.concurrent.sync;

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
