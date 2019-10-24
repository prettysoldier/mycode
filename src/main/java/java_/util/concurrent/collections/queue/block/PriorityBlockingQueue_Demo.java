package java_.util.concurrent.collections.queue.block;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 基于堆的优先级阻塞队列
 * PriorityBlockingQueue (jdk1.5) 无界阻塞队列
 * 特点：
 *   1.优先级队列
 *   2.由于PriorityBlockingQueue是按照元素的权重进入排序，所以队列中的元素必须是可以比较的，也就是说元素必须实现Comparable接口；
 *   3.PriorityBlockingQueue是真正的无界队列（仅受内存大小限制）
 *   4.由于PriorityBlockingQueue无界队列，所以插入元素永远不会阻塞线程；
 *   5.PriorityBlockingQueue底层是一种基于数组实现的堆结构。
 *
 * 默认初始化容量：11
 * 初始化容量必须大于0
 * PriorityBlockingQueue只有一个条件等待队列——notEmpty
 *
 *
 * 扩容
 *   1.扩容时，如果容量小于64，则扩大一倍并+2。如果容量>=64，扩大1/2倍。
 *   2.扩容最大值：MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8
 *   3.扩容只在拷贝替换数组时才加锁，提高并发。扩容和出/入队可以并发进行
 *   4.如果扩容时发生竞争，会放弃扩容！由其他线程扩容就好了。通过 allocationSpinLock 字段的CAS来实现。
 *
 * 出队
 *   每次出队的元素都是“堆顶节点”，然后将最后的节点放在堆顶，然后向下沉。
 * 入队
 *   每次入队放在数组的末尾，通过上浮来调整
 * 出队入队用ReentrantLock
 *
 *
 *
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class PriorityBlockingQueue_Demo {

    public static void main(String[] args) throws Exception {

        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>(20);
        pbq.put(9);
        pbq.put(2);
        pbq.put(93);
        pbq.put(3);
        pbq.put(25);
        pbq.put(90);
        pbq.put(100);

        pbq.take();
    }



}
