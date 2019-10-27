package java_.util.concurrent.collections.queue.block;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于单链表的阻塞队列 LinkedBlockingQueue
 *
 * 同步实现：维护了两把锁——takeLock和putLock。
 *
 * 近似有界阻塞队列。可以指定队列的容量，也可以不指定，如果不指定，那么它的容量大小默认为Integer.MAX_VALUE。
 * 底层数据结构：单链表
 *   takeLock : 控制出队的并发
 *   putLock : 控制入队的并发
 *   也就是说，出队和入队是并发执行的
 *
 * 队列不能包含null元素
 *
 * 归纳一下，LinkedBlockingQueue 和 ArrayBlockingQueue 比较主要有以下区别：
 *   1.队列大小不同
 *     ArrayBlockingQueue初始构造时必须指定大小，
 *     而LinkedBlockingQueue构造时既可以指定大小，也可以不指定（默认为Integer.MAX_VALUE，近似于无界）；
 *   2.底层数据结构不同
 *     ArrayBlockingQueue底层采用数组作为数据存储容器，
 *     而LinkedBlockingQueue底层采用单链表作为数据存储容器；
 *   3.两者的加锁机制不同
 *     ArrayBlockingQueue使用一把全局锁，即入队和出队使用同一个ReentrantLock锁；
 *     而LinkedBlockingQueue进行了锁分离，入队使用一个ReentrantLock锁（putLock），
 *     出队使用另一个ReentrantLock锁（takeLock）；
 *   4.LinkedBlockingQueue不能指定公平/非公平策略（默认都是非公平），而ArrayBlockingQueue可以指定策略。
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class LinkedBlockingQueue_Demo {
    LinkedBlockingQueue lbq = new LinkedBlockingQueue();

}
