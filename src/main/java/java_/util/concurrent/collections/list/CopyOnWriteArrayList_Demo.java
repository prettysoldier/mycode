package java_.util.concurrent.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制列表 CopyOnWriteArrayList jdk1.5
 *
 * 同步实现：ReentrantLock
 *
 * 用于处理“读多写少”的并发场景
 *
 * 1.如果想在并发环境下使用列表：
 *      Vector ：synchronized方法！
 *      Collections.synchronizedList 返回一个同步代理类(内部类)：synchronized同步块！
 *      自己实现ArrayList的子类，并进行同步、加锁
 *
 * 2.CopyOnWriteArrayList 迭代时不能修改，会抛出UnsupportedOperationException异常
 *   迭代时，仅仅返回一个当前内部数组的快照，也就是说，
 *   如果此时有其它线程正在修改元素，并不会在迭代中反映出来，因为修改都是在新数组中进行的。
 *   迭代器对象也不支持修改方法，全部会抛出UnsupportedOperationException异常
 *
 * 3.数组内存占用的太大，那么可能会造成频繁GC, 不适合大数据量的场景
 *
 * 4.数据一致性：可能获取不到刚刚写入的数据
 *   CopyOnWriteArrayList只能保证数据的最终一致性，不能保证数据的实时一致性——读操作读到的数据只是一份快照。
 *   所以如果希望写入的数据可以立刻被读到，那CopyOnWriteArrayList并不适合。
 *
 * 总结：
 * 1 适合“读多写少”且 数据量不大的场景。
 * 2 线程安全
 * 3 内存的使用较多
 * 4 迭代是对快照进行的，不会抛出ConcurrentModificationException，且迭代过程中不支持修改操作。、
 * 5 每次添加都需要重新复制整个数组，性能较低。
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class CopyOnWriteArrayList_Demo {
    List list = new ArrayList();
    CopyOnWriteArrayList li = new CopyOnWriteArrayList();
}
