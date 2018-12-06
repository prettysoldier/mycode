package test.java.util.concurrent;

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

    private int capacity;
    private List<Object> list;
    private Lock lock = new ReentrantLock();
    private Condition emptyConditon = this.lock.newCondition();
    private Condition fullConditon = this.lock.newCondition();

    public MyBlockQueueByCondition(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
    }

    public Object pop() {
        try {
            this.lock.lock();
            while (this.list.size() == 0) {
                this.emptyConditon.await();
            }
            if (this.list.size() > 0) {
                Object obj = this.list.remove(0);
                this.fullConditon.signal();
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
        return null;
    }

    public void put(Object obj) {
        try {
            this.lock.lock();

            while(list.size() >= this.capacity){
                this.fullConditon.await();
            }
            this.list.add(obj);
            this.emptyConditon.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public int size() {
        return this.list.size();
    }

    public static void main(String[] args) {
        int size = 10;
        MyBlockQueueByCondition bq = new MyBlockQueueByCondition(size);
        Thread consumerThread = new Thread(() -> {
            while (true) {

                try {
                    System.out.println("consumerThread, bq size is " + bq.size());
                    System.out.println("consumerThread, 消费" + bq.pop());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        consumerThread.start();

        Thread producerThread = new Thread(() -> {
            int i = 0;
            while (true) {
                System.out.println("producerThread, bq size is " + bq.size());

                try {
                    Thread.sleep(1000);
                    bq.put(new Item(i++ + ""));
                    System.out.println("producerThread, 生产: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();
    }
}
