package source_code_java.java_.util.concurrent.collections.map;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 并发跳表 ConcurrentSkipListMap
 *
 * 同步实现：自旋+CAS
 *
 * ConcurrentSkipListMap 的由来：JDK1.6时，为了对高并发环境下的有序Map提供更好的支持。相当于并发版本的TreeMap。
 *
 * 我们知道，通常意义上的链表是不能支持随机访问的（通过索引快速定位），其查找的时间复杂度是O(n)，
 * 而数组这一可支持随机访问的数据结构，虽然查找很快，但是插入/删除元素却需要移动插入点后的所有元素，时间复杂度为O(n)。
 * 为了解决这一问题，引入了树结构，树的增删改查效率比较平均，一棵平衡二叉树（AVL）的增删改查效率一般为O(logn)，
 * 比如工业上常用红黑树作为AVL的一种实现。但是，AVL的实现一般都比较复杂，插入/删除元素可能涉及对整个树结构的修改，
 * 特别是并发环境下，通常需要全局锁来保证AVL的线程安全，于是又出现了一种类似链表的数据结构——跳表。
 *
 * Skip List的基本思想了，总结起来，有以下几点：
 *   1.跳表，跳跃表：类似于链表的结构，但是其查询、插入、删除的时间复杂度都是 O(logn)
 *   2.跳表由很多层组成；
 *   3.每一层都是一个有序链表；
 *   4.对于每一层的任意结点，不仅有指向下一个结点的指针，也有指向其下一层的指针。
 *
 * 注意：
 * ConcurrentSkipListMap 在删除键值对时，不会立即执行删除，而是通过引入“标记结点”，以“懒删除”的方式进行，以提高并发效率。
 * ConcurrentSkipListMap对键值对的要求是均不能为null.
 *
 * 有什么缺点？
 *
 * @author shuaijunhe
 * @create 2019/10/22
 * @description
 */
public class ConcurrentSkipListMap_Demo {

    ConcurrentSkipListMap map = new ConcurrentSkipListMap();
}
