package java_.util.concurrent.collections.queue.unblock;

import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Queue接口：
 * 每种操作类型，都给出了两种方法，区别就是其中一种操作在队列的状态不满足某些要求时，会抛出异常；另一种，则直接返回特殊值（如null）。
 * 抛出异常：add(e) remove() element()
 * 返回特殊值null：offer(e) poll() peek()
 *
 * ConcurrentLinkedDeque是JDK1.7时，J.U.C包引入的一个集合工具类。在JDK1.7之前，除了Stack类外，并没有其它适合并发环境的“栈”数据结构。
 * ConcurrentLinkedDeque作为双端队列，可以当作“栈”来使用，并且高效地支持并发环境。
 * Stack 是用同步方法实现的线程安全的栈。
 *
 * 原理与ConcurrentLinkedQueue一样：弱一致性、不具备实时性、size方法需要遍历
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class ConcurrentLinkedDeque_Demo {

    ConcurrentLinkedDeque<String> cld = new ConcurrentLinkedDeque<>();

    Stack stack = new Stack();
}
