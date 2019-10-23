package java_.util.concurrent.collections.queue.unblock;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 无锁队列：ConcurrentLinkedQueue
 * JDK1.5时随着J.U.C一起引入的一个支持并发环境的队列。
 * 从名字就可以看出来，底层是基于链表实现的。自旋+CAS的非阻塞算法
 * 使用了自旋+CAS的非阻塞算法来保证线程并发访问时的数据一致性。
 *
 * 由于是完全基于无锁算法实现的，所以当出现多个线程同时进行修改队列的操作（比如同时入队），很可能出现CAS修改失败的情况，
 * 那么失败的线程会进入下一次自旋，再尝试入队操作，直到成功。所以，在并发量适中的情况下，一般具有较好的性能。
 *
 * 缺点：
 * ConcurrentLinkedQueue不具备实时的数据一致性，实际运用中，队列一般在生产者-消费者的场景下使用得较多，
 * 所以ConcurrentLinkedQueue的使用场景并不如阻塞队列那么多。
 *
 * 另外，关于ConcurrentLinkedQueue还有以下需要注意的几点： *
 *  ConcurrentLinkedQueue的迭代器是弱一致性的，这在并发容器中是比较普遍的现象，主要是指在一个线程在遍历队列结点
 *      而另一个线程尝试对某个队列结点进行修改的话不会抛出ConcurrentModificationException，这也就造成在遍历某个
 *      尚未被修改的结点时，在next方法返回时可以看到该结点的修改，但在遍历后再对该结点修改时就看不到这种变化。
 *  size方法需要遍历链表，所以在并发情况下，其结果不一定是准确的，只能供参考。
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class ConcurrentLinkedQueue_Demo {

    ConcurrentLinkedQueue cle = new ConcurrentLinkedQueue();
}
