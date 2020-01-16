package source_code_java.java_.util.concurrent.collections.set;

/**
 * ConcurrentSkipListSet：(JDK1.6)是一种为并发环境设计的有序SET工具类
 *
 * 同步实现：自旋+CAS
 *
 * ConcurrentSkipListSet的实现非常简单，其内部引用了一个ConcurrentSkipListMap对象，所有API方法均委托ConcurrentSkipListMap对象完成
 *
 * ConcurrentSkipListMap对键值对的要求是均不能为null，所以ConcurrentSkipListSet在插入元素的时候，
 * 用一个Boolean.TRUE对象（相当于一个值为true的Boolean型对象）作为value，
 *
 *
 * @author shuaijunhe
 * @create 2019/10/23
 * @description
 */
public class ConcurrentSkipListSet_Demo {
}
