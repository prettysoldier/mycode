package spin_lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * MCS 来自于其发明人名字的首字母： John Mellor-Crummey和Michael Scott。是一种基于链表的可扩展、高性能、公平的自旋锁，
 * 申请线程只在本地变量上自旋，直接前驱负责通知其结束自旋，从而极大地减少了不必要的处理器缓存同步的次数，降低了总线和内存的开销。
 *
 * 差异：
 *
 * 从代码实现来看，CLH比MCS要简单得多。
 * 从自旋的条件来看，CLH是在前驱节点的属性上自旋，而MCS是在本地属性变量上自旋。
 * 从链表队列来看，CLHNode不直接持有前驱节点，CLH锁释放时只需要改变自己的属性；MCSNode直接持有后继节点，MCS锁释放需要改变后继节点的属性。
 * CLH锁释放时只需要改变自己的属性，MCS锁释放则需要改变后继节点的属性。
 *
 * @author hsj
 * @create 2020/1/16
 */
public class MCSLock {

    private AtomicReference<QNode> tail = new AtomicReference<>(null);
    private ThreadLocal<QNode> myNode = ThreadLocal.withInitial(QNode::new);

    public void lock() {
        QNode qnode = myNode.get();
        QNode preNode = tail.getAndSet(qnode);
        if (preNode != null) {
            qnode.locked = false;
            preNode.next = qnode;
            //wait until predecessor gives up the lock
            while (!qnode.locked) {
            }
        }
        qnode.locked = true;
    }

    public void unlock() {
        QNode qnode = myNode.get();
        if (qnode.next == null) {
            //后面没有等待线程的情况
            if (tail.compareAndSet(qnode, null)) {
                //真的没有等待线程，则直接返回，不需要通知
                return;
            }
            //wait until predecessor fills in its next field
            // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
            while (qnode.next == null) {
            }
        }
        //后面有等待线程，则通知后面的线程
        qnode.next.locked = true;
        qnode.next = null;
    }

    private class QNode {
        /**
         * 是否被qNode所属线程锁定
         */
        private volatile boolean locked = false;
        /**
         * 与CLHLock相比，多了这个真正的next
         */
        private volatile QNode next = null;
    }
}
