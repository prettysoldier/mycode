package java_.util.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author shuaijunhe
 * @create 2019/10/16
 * @description
 */
public class MyAtomicInteger {

    /**
     * lazySet内部调用了Unsafe类的putOrderedInt方法，通过该方法对共享变量值的改变，不一定能被其他线程立即看到。也就是说以普通变量的操作方式来写变量。
     * 为什么会有这种奇怪方法？什么情况下需要使用lazySet呢？
     * 由于锁的存在：
     * lock()方法获取锁时，和volatile变量的读操作一样，会强制使CPU缓存失效，强制从内存读取变量。
     * unlock()方法释放锁时，和volatile变量的写操作一样，会强制刷新CPU写缓冲区，把缓存数据写到主内存
     * 所以，ai.set(1)可以用ai.lazySet(1)方法替换
     * 由锁来保证共享变量的可见性，以设置普通变量的方式来修改共享变量，减少不必要的内存屏障，从而提高程序执行的效率。
     *
     */
    private void lazySet() {
        AtomicInteger ai = new AtomicInteger();
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            // ai.set(1);
            ai.lazySet(1);
        } finally {
            lock.unlock();
        }
    }

    /**
     * weakCompareAndSet = compareAndSet
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger();

        AtomicInteger ai = new AtomicInteger();

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Accumlator(ai), "thread-" + i);
            list.add(t);
            t.start();
        }

        for (Thread t : list) {
            t.join();
        }

        System.out.println(ai.get());
    }

    static class Accumlator implements Runnable {

        private AtomicInteger ai;

        Accumlator(AtomicInteger ai) {
            this.ai = ai;
        }

        @Override
        public void run() {
            for (int i = 0, len = 1000; i < len; i++) {
                ai.incrementAndGet();
            }
        }
    }
}
