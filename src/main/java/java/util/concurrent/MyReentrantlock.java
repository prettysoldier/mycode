package main.test.java.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 *
 * @author Shuaijun He
 */
public class MyReentrantlock {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                try {
                    // do something
                } finally {
                    lock.unlock();
                }

            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
