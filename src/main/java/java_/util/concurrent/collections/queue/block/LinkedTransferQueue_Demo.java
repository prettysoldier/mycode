package java_.util.concurrent.collections.queue.block;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 特殊的阻塞队列 LinkedTransferQueue
 *
 * jdk1.7
 * 同步实现：自旋+CAS+LockSupport(无锁算法)
 * 特点：
 *   1.transfer方法:
 *     除了具备阻塞队列的常用功能外，还有一个比较特殊的transfer方法。我们知道，在普通阻塞队列中，当队列为空时，
 *     消费者线程（调用take或poll方法的线程）一般会阻塞等待生产者线程往队列中存入元素。
 *     而LinkedTransferQueue的transfer方法则比较特殊：
 *       1)当有消费者线程阻塞等待时，调用transfer方法的生产者线程不会将元素存入队列，而是直接将元素传递给消费者；
 *       2)如果调用transfer方法的生产者线程发现没有正在等待的消费者线程，则会将元素入队，然后会阻塞等待，直到有一个消费者线程来获取该元素。
 *  2.是一种无界阻塞队列，底层基于单链表实现。最大容量：Integer.MAX_VALUE
 *  3.结点有两种类型：数据结点、请求结点
 *  4.基于无锁算法实现
 *
 * TransferQueue 接口：（jdk1.7）
 *   int getWaitingConsumerCount();
 *   boolean hasWaitingConsumer();
 *   boolean tryTransfer(E e);
 *   boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException;
 *   void transfer(E e) throws InterruptedException;
 *
 * 四种操作类型：
 * 1.NOW=0 即时操作（可能失败）
 * 2.ASYNC=1 异步操作（必然成功）：入队操作
 * 3.SYNC=2 同步操作（阻塞调用线程）：transfer、 take
 * 4.TIMED=3 限时同步：（限时阻塞调用线程）：poll(long timeout, TimeUnit unit)；tryTransfer(E e, long timeout, TimeUnit unit)
 *
 * 核心方法 xfer
 *
 * 总结：
 *   1.LinkedTransferQueue其实兼具了SynchronousQueue的特性以及无锁算法的性能，并且是一种无界队列：
 *     1)和SynchronousQueue相比，LinkedTransferQueue可以存储实际的数据；
 *     2)和其它阻塞队列相比，LinkedTransferQueue 直接用无锁算法实现，性能有所提升。
 *     3)与 ConcurrentLinkedQueue 相比，LinkedTransferQueue是阻塞队列！
 *   2.Dual Queue
 *     由于LinkedTransferQueue可以存放两种不同类型的结点，所以称之为“Dual Queue”
 *
 * 进阶：
 *   1.从awaitMatch方法其实可以看到一种经典的“锁优化”思路，就是 自旋 -> yield -> 阻塞，线程不会立即进入阻塞，
 *     因为线程上下文切换的开销往往比较大，所以会先自旋一定次数，中途可能伴随随机的yield操作，让出cpu时间片，
 *     如果自旋次数用完后，还是没有匹配线程出现，再真正阻塞线程。
 *   2.为了节省 CAS 操作的开销，LinkedTransferQueue使用了松弛（slack）操作：
 *     在结点被匹配（被删除）之后，不会立即更新队列的head、tail，而是当 head、tail结点与最近一个未匹配的结点之间的距离超过
 *     “松弛阀值”后才会更新（默认为 2）。这个“松弛阀值”一般为1到3，如果太大会增加沿链表查找未匹配结点的时间，太小会增加 CAS 的开销。
 *
 * @author shuaijunhe
 * @create 2019/10/24
 * @description
 */
public class LinkedTransferQueue_Demo {

    public static void main(String[] args) {

        LinkedTransferQueue ltq = new LinkedTransferQueue();

        ltq.put(1);
        ltq.put(2);
        ltq.put(3);

    }
}
