package java_.util.concurrent.collections.set;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 写时复制集合 CopyOnWriteArraySet
 *
 * 同步实现：ReentrantLock
 *
 * CopyOnWriteArraySet，从名字上可以看出，也是基于“写时复制”的思想。
 * 以“组合”方式，委托CopyOnWriteArrayList对象实现了所有API功能。
 *
 * 唯一的区别就是 CopyOnWriteArraySet 不允许含有重复元素，所以添加元素（add方法）时，
 * 内部调用了CopyOnWriteArrayList的 addAllAbsent 方法。
 *
 * 总结：
 * 1 适合“读多写少”且数据量不大的场景。
 * 2 线程安全
 * 3 内存的使用较多
 * 4 迭代是对快照进行的，不会抛出ConcurrentModificationException，且迭代过程中不支持修改操作。
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class CopyOnWriteArraySet_Demo {

    CopyOnWriteArraySet set = new CopyOnWriteArraySet();
}
