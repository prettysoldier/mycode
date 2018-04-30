/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
 * 如果对象调用了notify方法就会通知某个正在等待这个对象的控制权的线程可以继续运行。
 * 如果对象调用了notifyAll方法就会通知所有等待这个对象控制权的线程继续运行。
 * 
 * @author Shuaijun He
 */
public class MyBlockQueueByCondition {

    private List<Object> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition conditon = lock.newCondition();

    public Object pop(){
        try {
            lock.lock();
            while (this.list.size() == 0) {
                conditon.await();
            }
            if (this.list.size() > 0) {
                return this.list.remove(0);
            } 
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

    public void put(Object obj) {
        try{lock.lock();
            this.list.add(obj);
            conditon.signal();
        }finally {
            lock.unlock();
        }
    }

    public int size() {
        return this.list.size();
    }

    public static void main(String[] args) {
        MyBlockQueueByCondition bq = new MyBlockQueueByCondition();
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        System.out.println("th1, bq size is " + bq.size());
                        System.out.println(bq.pop());
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        th1.start();

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    System.out.println("th2, bq size is " + bq.size());
                    bq.put(new Item(i++ + ""));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        th2.start();
    }
}

