package java_.util.concurrent.locks;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过ReentrantLock & Condition 实现阻塞队列
 *
 * 当线程在指定Condition对象上等待的时候，其实就是将线程包装成结点，加入了条件队列，然后阻塞。
 * 当线程被通知唤醒时，则是将条件队列中的结点转换成等待队列中的结点，之后的处理就和独占功能完全一样。
 * @author Shuaijun He
 */
public class BlockQueueByReentrantLockCondition {

    private int capacity;
    private List<Object> list;

    private Lock lock = new ReentrantLock();
    private Condition emptyConditon = this.lock.newCondition();
    private Condition fullConditon = this.lock.newCondition();

    public BlockQueueByReentrantLockCondition(int capacity) {
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
        BlockQueueByReentrantLockCondition bq = new BlockQueueByReentrantLockCondition(size);
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
    static class Item {
        private String name;

        /**
         * @param name
         */
        public Item(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
