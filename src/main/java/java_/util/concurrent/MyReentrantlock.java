package java_.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 *
 * @author Shuaijun He
 */
public class MyReentrantlock {

    public static void main(String[] args) {

        System.out.println(null == null);

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

    private static void printABC(){

        Print print = new Print();
        new Thread(() -> {
            print.printA();
        }

        ).start();

        new Thread(() -> {
            print.printB();
        }

        ).start();

        new Thread(() -> {
            print.printC();
        }

        ).start();


    }


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


class Print {

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
                while(flag != 1) {
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
                while(flag != 2) {
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
                lock.unlock();
            }
        }
    }

    public void printC()
    {
        while (true) {
            lock.lock();
            try
            {
                while(flag != 3) {
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
                lock.unlock();
            }
        }
    }

}