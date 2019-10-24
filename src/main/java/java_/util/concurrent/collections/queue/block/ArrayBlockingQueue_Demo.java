package java_.util.concurrent.collections.queue.block;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue jdk1.5 有界阻塞队列
 * 通过ReentrantLock来保证并发环境下的线程安全。针对队列的修改都需要加全局锁。
 * 对于超高并发的环境，由于生产者-消息者共用一把锁，可能出现性能瓶颈。
 *
 * 特点：
 *      队列的容量一旦在构造时指定，后续不能改变；
 *      插入元素时，在队尾进行；删除元素时，在队首进行；
 *      队列满时，调用特定方法插入元素会阻塞线程；
 *      队列空时，删除元素也会阻塞线程；
 *      支持公平/非公平策略，默认为非公平策略。
 *
 * 元素不能为null！
 * 环形队列！
 *
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class ArrayBlockingQueue_Demo {

    ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(10);

}
