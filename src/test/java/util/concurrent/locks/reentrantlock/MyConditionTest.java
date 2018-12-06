package test.java.util.concurrent.locks.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 */
public class MyConditionTest {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        ThreadA thread = new ThreadA(myService);
        thread.start();
        Thread.sleep(1000);
        myService.signal();
    }
}

class ThreadA extends Thread {
    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        this.myService.service();
    }
}

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = this.lock.newCondition();

    public void service() {
        try {
            this.lock.lock();
            System.out.println("子线程等待，释放锁 : " + System.currentTimeMillis());
            this.condition.await();
            System.out.println("子线程醒来，获取锁 : " + System.currentTimeMillis());
            this.condition.signal();
            System.out.println("唤醒主线程 : " + System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println("子线程等待: " + System.currentTimeMillis());
            this.condition.await();
            System.out.println("子线程结束: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void signal() {
        try {
            this.lock.lock();
            System.out.println("haha1 time : " + System.currentTimeMillis());
            this.condition.signal();
            System.out.println("haha time : " + System.currentTimeMillis());
            this.condition.await();
            System.out.println("主线程醒来：" + System.currentTimeMillis());
            Thread.sleep(1000);
            this.condition.signal();
            System.out.println("主线程结束：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }
}