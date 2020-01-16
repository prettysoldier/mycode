package source_code_java.java_.util.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * JDK1.8时，java.util.concurrent.atomic包中提供了一个新的原子类：LongAdder。
 * 根据Oracle官方文档的介绍，LongAdder在高并发的场景下会比它的前辈————AtomicLong 具有更好的性能，代价是消耗更多的内存空间：
 *
 * 为什么要引入LongAdder？
 * 上述方法调用了Unsafe类的getAndAddLong方法，该方法是个native方法，它的逻辑是采用自旋的方式不断更新目标值，直到更新成功。 *
 * 在并发量较低的环境下，线程冲突的概率比较小，自旋的次数不会很多。但是，高并发环境下，N个线程同时进行自旋操作，
 * 会出现大量失败并不断自旋的情况，此时AtomicLong的自旋会成为瓶颈。
 * 这就是LongAdder引入的初衷——解决高并发环境下 AtomicLong的自旋瓶颈问题 。
 *
 * LongAdder的思路
 * 我们知道，AtomicLong中有个内部变量value保存着实际的long值，所有的操作都是针对该变量进行。
 * 也就是说，高并发环境下，value变量其实是一个热点，也就是N个线程竞争一个热点。
 * LongAdder的基本思路就是分散热点，将value值分散到一个数组中，不同线程会命中到数组的不同槽中，
 * 各个线程只对自己槽中的那个值进行CAS操作，这样热点就被分散了，冲突的概率就小很多。如果要获取真正的long值，只要将各个槽中的变量值累加返回。
 * 这种做法有没有似曾相识的感觉？没错，ConcurrentHashMap中的“分段锁”其实就是类似的思路。
 *
 * 缺点：
 * 1.addAndGet、decrementAndGet除了单纯的做自增自减外，还可以立即获取增减后的值，
 * 而LongAdder则需要做同步控制才能精确获取增减后的值。如果业务需求需要精确的控制计数，做计数比较，AtomicLong也更合适。
 * 2.会多消耗一些内存
 *
 * LongAdder的原理：
 *
 * LongAdder是每个线程拥有自己的槽，各个线程一般只对自己槽中的那个值进行CAS操作。
 *
 * 注意：cells最大长度不会超过cpu核数
 *
 * @author shuaijunhe
 * @create 2019/10/17
 * @description
 */
public class LongAdderTest {

    public static void main(String[] args) throws Exception {

        LongAdder longAdder = new LongAdder();
        List<Thread> list = new ArrayList<>();
        IntStream.range(0, 10).mapToObj(i -> new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                longAdder.increment();
            }
        })).forEach(thread -> {
            list.add(thread);
            thread.start();
        });
        for (Thread t : list) {
            t.join();
        }

        System.out.println(longAdder.sum());

    }
}
