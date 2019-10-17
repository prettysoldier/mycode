package java_.util.concurrent.atomic;

/**
 * 无锁算法：所谓“无锁算法”，我们在讲juc-locks锁框架系列中，已经接触过太多次了，其实底层就是通过Unsafe类实现的一种比较并交换的算法，大致的结构如下（具体入参，根据上下文有所不同）：
 * boolean compareAndSet(expectedValue, updateValue);
 * 当希望修改的值与expectedValue相同时，则尝试将值更新为updateValue，更新成功返回true，否则返回false。
 *
 *
 * @author shuaijunhe
 * @create 2019/10/16
 * @description
 */
public class NoLockAlgorithm {
}
