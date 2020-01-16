package spin_lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CLH队列锁的优点是空间复杂度低（如果有n个线程，L个锁，每个线程每次只获取一个锁，那么需要的存储空间是O（L+n），n个线程有n个myNode，L个锁有L个tail），
 * CLH的一种变体被应用在了JAVA并发框架中。
 *
 * 唯一的缺点是在NUMA系统结构下性能很差，在这种系统结构下，每个线程有自己的内存，如果前趋结点的内存位置比较远，自旋判断前趋结点的locked域，性能将大打折扣，
 * 但是在SMP系统结构下该法还是非常有效的。一种解决NUMA系统结构的思路是MCS队列锁。
 *
 * @author hsj
 * @create 2020/1/16
 */
public class CLHLock {

    /**
     * 锁等待队列的尾部
     */
    private AtomicReference<QNode> tail = new AtomicReference<>(null);
    /**
     * 当前节点
     */
    private ThreadLocal<QNode> myNode = ThreadLocal.withInitial(QNode::new);
    /**
     * 前置节点
     */
    private ThreadLocal<QNode> preNode = ThreadLocal.withInitial(()->null);

    public void lock() {
        QNode qnode = myNode.get();
        //设置自己的状态为locked=true表示需要获取锁
        qnode.locked = true;
        //链表的尾部设置为本线程的qNode，并将之前的尾部设置为当前线程的preNode
        QNode pre = tail.getAndSet(qnode);
        preNode.set(pre);
        if(pre != null) {
            //当前线程在前驱节点的locked字段上旋转，直到前驱节点释放锁资源
            while (pre.locked) {
            }
        }
    }

    public void unlock() {
        QNode qnode = myNode.get();
        //释放锁操作时将自己的locked设置为false，可以使得自己的后继节点可以结束自旋
        qnode.locked = false;
        //回收自己这个节点，从虚拟队列中删除
        //将当前节点引用置为自己的preNode，那么下一个节点的preNode就变为了当前节点的preNode，这样就将当前节点移出了队列
        myNode.set(preNode.get());
    }

    private class QNode {
        /**
         * true表示该线程需要获取锁，且不释放锁，为false表示线程释放了锁，且不需要锁
         */
        private volatile boolean locked = false;
    }

    public static void main(String[] args) throws Exception {
        CLHLock lock = new CLHLock();
        lock.lock();

        Thread.sleep(1000);

        new Thread(()->{lock.lock();}).start();

        Thread.sleep(1000);

        new Thread(()->{lock.lock();}).start();
    }
}
