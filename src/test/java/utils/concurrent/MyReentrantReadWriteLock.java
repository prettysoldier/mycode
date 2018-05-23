
package test.java.utils.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Shuaijun He
 */
public class MyReentrantReadWriteLock {

    public static void main(String[] args) {
        Service service = new Service();
        Thread threadA = new Thread(() -> service.read());
        threadA.setName("A");

        Thread threadB = new Thread(() -> service.read());
        threadB.setName("B");

        threadA.start();
        threadB.start();
    }
}

class Service {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            this.lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + ","
                + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.readLock().unlock();
        }
    }
}