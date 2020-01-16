package source_code_java.java_.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * 其实阅读源码也可以发现，这些数组原子类与对应的普通原子类相比，只是多了通过索引找到内存中元素地址的操作而已。
 *
 * 注意：原子数组并不是说可以让线程以原子方式一次性地操作数组中所有元素的数组。
 * 而是指对于数组中的每个元素，可以以原子方式进行操作。
 *
 * 说得简单点，原子数组类型其实可以看成原子类型组成的数组。
 *
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class AtomicIntegerArrayTest {

    public static void main(String[] args) {

        AtomicIntegerArray  array = new AtomicIntegerArray(10);
        array.getAndIncrement(0);
        // 等同于
        AtomicInteger[]  array1 = new AtomicInteger[10];
        array1[0].getAndIncrement();

    }
}
