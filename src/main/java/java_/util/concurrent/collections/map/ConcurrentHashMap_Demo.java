package java_.util.concurrent.collections.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap jdk1.5 线程安全。各个版本的ConcurrentHashMap，内部实现机制千差万别，本节所有的讨论基于JDK1.8
 *
 * 一）tableSizeFor：
 *      对n右移1位：001xx...xxx，再位或：011xx...xxx
 *      对n右移2为：00011...xxx，再位或：01111...xxx
 *      此时前面已经有四个1了，再右移4位且位或可得8个1
 *      同理，有8个1，右移8位肯定会让后八位也为1。
 *      综上可得，该算法让最高位的1后面的位全变为1。
 *      最后再让结果n+1，即得到了2的整数次幂的值了。
 *      让cap-1再赋值给n的目的是另找到的目标值大于或等于原值。
 *
 * 二）concurrencyLevel只是为了兼容JDK1.8以前的版本，并不是实际的并发级别，loadFactor也不是实际的负载因子
 *      这两个都失去了原有的意义，仅仅对初始容量有一定的控制作用
 * 三）链表转树的阈值：8    MIN_TREEIFY_CAPACITY  键值对总数不小于64
 *      树转链表的阈值：6   MIN_TRANSFER_STRIDE 键值对总数小于16
 *      上面指的是单个槽。另外链表转树还有一个条件：键值对总数不少于64
 * 四）put(k, v) 键和值都不能为null
 * 五）当正在写锁被占用时，如果此时需要查找，用链表形势进行查找（TreeBin、TreeNode 是 Node 的子类）
 *
 * 六）扩容：JDK1.8中，ConcurrentHashMap最复杂的部分就是扩容/数据迁移，涉及多线程的合作和rehash。
 *  1）当table数组的大小为2的幂次时，通过key.hash & table.length-1这种方式计算出的索引i，当table扩容后（2倍），
 *      新的索引要么在原来的位置i，要么是i+n。（n是原来的数组大小）
 *      这种处理方式非常利于扩容时多个线程同时进行的数据迁移操作，因为旧table的各个桶中的结点迁移不会互相影响！！
 *  2）扩容时机：
 *      当某槽位的链表数>=8，且总数小于64时，先扩容。tryPresize.
*   3) 桶table[i]为空:
 *      当旧table的桶table[i] == null，说明原来这个桶就没有数据，那就直接尝试放置一个ForwardingNode，表示这个桶已经处理完成。
 *      ForwardingNode我们在上一篇提到过，主要做占用位，多线程进行数据迁移时，其它线程看到这个桶中是ForwardingNode结点，就知道有线程已经在数据迁移了。
 *      另外，当最后一个线程完成迁移任务后，会遍历所有桶，看看是否都是ForwardingNode，如果是，那么说明整个扩容/数据迁移的过程就完成了。
 *  4) 桶table[i]已迁移完成
 *  5) 桶table[i]未迁移完成:
 *      把节点分成两类，一类的索引i不变，另一类的索引为i+n
 *
 *
 * @author shuaijunhe
 * @create 2019/10/21
 * @description
 */
public class ConcurrentHashMap_Demo {


    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(9, 0.75f);

    }
}
