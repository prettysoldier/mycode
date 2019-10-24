package java_.util.concurrent.collections.queue.block;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * 基于双链表的阻塞双端队列 LinkedBlockingDeque
 *
 * JDK1.5
 * 底层利用ReentrantLock实现同步
 * 容量必须大于0；不指定的话为Integer.MAX_VALUE
 * 不能含有null数据
 * 同步实现：一把锁ReentrantLock，所有对队列的修改操作都需要先获取这把全局锁，实现比较简单！
 *
 * 场景：
 *   工作窃取算法：考虑下面这样一种场景：有多个消费者，每个消费者有自己的一个消息队列，生产者不断的生产数据扔到队列中，消费者消费数据有快又慢。
 *   为了提升效率，速度快的消费者可以从其它消费者队列的队尾出队元素放到自己的消息队列中，由于是从其它队列的队尾出队，
 *   这样可以减少并发冲突（其它消费者从队首出队元素），又能提升整个系统的吞吐量。这其实是一种“工作窃取算法”的思路。
 * @author shuaijunhe
 * @create 2019/10/24
 * @description
 */
public class LinkedBlockingDeque_Demo {

    LinkedBlockingDeque lbd = new LinkedBlockingDeque();

}
