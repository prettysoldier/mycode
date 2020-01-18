package source_code_java.java_.util.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺利打印A B C
 *
 * @author Shuaijun He
 */
public class ReentrantlockDemo {

    public static void main(String[] args) {
        // null == null 为 true !!!
        System.out.println(null == null);


        // ReentrantLock的一般用法
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


        // 依次打印ABC
        printABC();
//        printAB();
    }

    /**
     * 依次打印ABC
     */
    private static void printABC(){

        Print print = new Print();
        new Thread(() -> { print.printA();}).start();
        new Thread(() -> { print.printB();}).start();
        new Thread(() -> { print.printC();}).start();
    }




    static class Print {

        private int flag = 1;
        private Lock lock = new ReentrantLock();
        private Condition condition_A = lock.newCondition();
        private Condition condition_B = lock.newCondition();
        private Condition condition_C = lock.newCondition();

        public void printA()
        {
            while (true) {
                lock.lock();
                try
                {
                    if(flag != 1) {
                        condition_A.await();
                    }
                    System.out.print("A");
                    Thread.sleep(1000);
                    flag = 2;
                    condition_B.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    /**
                     * 可以注掉此行的原因是 signal()执行完后，进入下个循环，遇到condition_A.await(), 会释放当前线程的锁!!
                     * 注释掉：减少一次解锁，在await时同时释放2次锁
                     * 不注释：正常的解锁和加锁。
                     */
                    lock.unlock();
                }
            }
        }

        public void printB()
        {
            while (true) {
                lock.lock();
                try
                {
                    if(flag != 2) {
                        condition_B.await();
                    }
                    System.out.print("B");
                    Thread.sleep(1000);
                    flag = 3;
                    condition_C.signal();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally
                {
//                  lock.unlock();
                }
            }
        }

        public void printC()
        {
            while (true) {
                lock.lock();
                try
                {
                    if(flag != 3) {
                        condition_C.await();
                    }
                    System.out.println("C");
                    Thread.sleep(1000);
                    flag = 1;
                    condition_A.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally
                {
//                  lock.unlock();
                }
            }
        }

    }

    /**
     * 一次打印A B
     */
    private static void printAB(){
        Object lock = new Object();
        new Thread(() -> {

            while(true) {
                try {
                    synchronized (lock){
                        System.out.println("A");
                        Thread.sleep(1000);
                        lock.notify();
                        lock.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {

            while(true) {
                try {
                    synchronized (lock){
                        System.out.println("B");
                        Thread.sleep(1000);
                        lock.notify();
                        lock.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
