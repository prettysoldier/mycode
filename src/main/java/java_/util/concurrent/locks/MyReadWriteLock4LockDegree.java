package java_.util.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 写锁 如何降级为读锁？？
 * 锁降级 ： 是指保持住当前的写锁（已拥有），再获取读锁，随后释放写锁的过程。
 * 锁降级可以使数据修改后，所有读锁能够感知到数据变化。
 * @author shuaijunhe
 * @create 2019/10/16
 * @description
 */
public class MyReadWriteLock4LockDegree implements Runnable{
    public static int index; // 共享资源
    public volatile boolean update; // 是否更新的标志
    private ReentrantReadWriteLock reentrantReadWriteLock;

    public MyReadWriteLock4LockDegree() {
        this.update = true;
        reentrantReadWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        while (true) {
            work();
        }
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void work() {
        reentrantReadWriteLock.readLock().lock();

        if (update) {
            reentrantReadWriteLock.readLock().unlock();

            // 锁降级开始
            reentrantReadWriteLock.writeLock().lock();
            try {
                if (update) {
                    // 准备数据
                    ++index;
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    update = false;
                }
                reentrantReadWriteLock.readLock().lock();
            } finally {
                reentrantReadWriteLock.writeLock().unlock();
                // 锁降级结束,降级为读锁
            }
        }
        try {
            // 使用数据
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + index);
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        MyReadWriteLock4LockDegree myStudy1 = new MyReadWriteLock4LockDegree();
        Thread thread1 = new Thread(myStudy1);
        Thread thread2 = new Thread(myStudy1);
        Thread thread3 = new Thread(myStudy1);
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myStudy1.setUpdate(true);
        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myStudy1.setUpdate(true);
    }

}
