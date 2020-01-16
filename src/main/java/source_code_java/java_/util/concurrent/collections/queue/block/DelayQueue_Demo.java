package source_code_java.java_.util.concurrent.collections.queue.block;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 延迟阻塞队列 DelayQueue
 *
 * JDK1.5
 * 同步实现：ReentrantLock + Condition
 *
 * 内部委托给 PriorityQueue
 * 实现接口：BlockingQueue
 *
 * 数据实现 Delay 接口：
 *   long getDelay(TimeUnit unit);
 *   继承Comparable接口：public int compareTo(T o);
 *
 * DelayQueue 的特点简要概括如下：
 *   DelayQueue是无界阻塞队列；入队永远不会阻塞。
 *   队列中的元素必须实现Delayed接口，元素过期后才会从队列中取走；
 *   无法设置初始容量。remainingCapacity()永远返回Integer.MAX_VALUE
 *
 * @author shuaijunhe
 * @create 2019/10/24
 * @description
 */
public class DelayQueue_Demo {


    public static void main(String[] args) {

        DelayQueue<Data> queue = new DelayQueue<>();

        Thread c1 = new Thread(new Consumer(queue), "consumer-1");
        Thread p1 = new Thread(new Producer(queue), "producer-1");
        c1.start();
        p1.start();
    }
    /**
     * 延迟对象
     */
    static class Data implements Delayed {
        private static final AtomicLong atomic = new AtomicLong(0);
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss-n");

        // 数据的失效时间点
        private final long time;

        // 序号
        private final long seqno;

        /**
         * @param deadline 数据失效时间点
         */
        public Data(long deadline) {
            this.time = deadline;
            this.seqno = atomic.getAndIncrement();
        }

        /**
         * 返回剩余有效时间
         *
         * @param unit 时间单位
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        /**
         * 比较两个Delayed对象的大小, 比较顺序如下:
         * 1. 如果是对象本身, 返回0;
         * 2. 比较失效时间点, 先失效的返回-1,后失效的返回1;
         * 3. 比较元素序号, 序号小的返回-1, 否则返回1.
         * 4. 非Data类型元素, 比较剩余有效时间, 剩余有效时间小的返回-1,大的返回1,相同返回0
         */
        @Override
        public int compareTo(Delayed other) {
            if (other == this)  // compare zero if same object
                return 0;

            if (other instanceof Data) {
                Data x = (Data) other;

                // 优先比较失效时间
                long diff = this.time - x.time;
                if (diff < 0)
                    return -1;
                else if (diff > 0)
                    return 1;

                else if (this.seqno < x.seqno)    // 剩余时间相同则比较序号
                    return -1;
                else
                    return 1;
            }

            // 一般不会执行到此处，除非元素不是Data类型
            long diff = this.getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
            return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "time=" + time +
                    ", seqno=" + seqno +
                    "}, isValid=" + isValid();
        }

        private boolean isValid() {
            return this.getDelay(TimeUnit.NANOSECONDS) > 0;
        }
    }

    static class Producer implements Runnable {
        private final DelayQueue<Data> queue;

        public Producer(DelayQueue<Data> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {

                long validTime = ThreadLocalRandom.current().nextLong(1000000000L, 7000000000L);
                Data data = new Data(System.nanoTime() + validTime);

                queue.put(data);
                System.out.println(Thread.currentThread().getName() + ": put " + data);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private final DelayQueue<Data> queue;

        public Consumer(DelayQueue<Data> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Data data = queue.take();
                    System.out.println("---------------" + Thread.currentThread().getName() + ": take " + data);

                    Thread.yield();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
