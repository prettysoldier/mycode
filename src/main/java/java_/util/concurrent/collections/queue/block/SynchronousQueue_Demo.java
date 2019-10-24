package java_.util.concurrent.collections.queue.block;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 特殊的同步队列 SynchronousQueue
 * JDK1.5
 * 同步实现：自旋+CAS+LockSupport
 * 实现了BlockingQueue接口，底层基于栈和队列实现：
 *
 * SynchronousQueue的底层实现包含两种数据结构——栈和队列。这是一种非常特殊的阻塞队列，它的特点简要概括如下：
 *   1.入队线程和出队线程必须一一匹配，否则任意先到达的线程会阻塞。比如ThreadA进行入队操作，在有其它线程执行出队操作之前，ThreadA会一直等待，反之亦然；
 *   2.SynchronousQueue内部不保存任何元素，也就是说它的容量为0，数据直接在配对的生产者和消费者线程之间传递，不会将数据缓冲到队列中。
 *   3.SynchronousQueue支持公平/非公平策略。
 *      非公平模式：基于内部数据结构——“栈”来实现，
 *      公平模式：基于内部数据结构——“队列”来实现；
 *   4.SynchronousQueue基于一种名为“Dual stack and Dual queue”的无锁算法实现。
 *   5.SynchronousQueue一样不支持null元素
 *   6.size()、remainingCapacity()/contains(Object o) 永远返回0
 *   7.offer() 会把数据漏掉，会丢失！返回false
 *   8.offer(E e, long timeout, TimeUnit unit) 在等待时间没被取走，数据会丢弃！
 *   9.add() 如果没有线程等待取数据，就会抛异常
 *   10.take() 会一直等待线程取走数据
 *
 *
 * 总结：
 *   TransferQueue主要用于线程之间的数据交换，由于采用无锁算法，其性能一般比单纯的其它阻塞队列要高。
 *   它的最大特点时不存储实际元素，而是在内部通过栈或队列结构保存阻塞线程。匹配后两个线程交换数据。
 * @author shuaijunhe
 * @create 2019/10/24
 * @description
 */
public class SynchronousQueue_Demo {

    public static void main(String[] args) throws Exception {

        SynchronousQueue sq = new SynchronousQueue();
        new Thread(()->{
            try {
                sq.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                sq.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                sq.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("取数据");
        new Thread(()->{
            try {
                System.out.println(sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println(sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println(sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
