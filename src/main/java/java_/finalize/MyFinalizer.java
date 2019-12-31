
package java_.finalize;

/**
 * https://blog.csdn.net/aitangyong/article/details/39450341
 * 《Effective Java》中提到“使用finalizer会导致严重的性能损失。在我的机器上，创建和销毁一个简单对象的实践大约是5.6ns，
 * 增加finalizer后时间增加到2400ns。
 * 换言之，创建和销毁带有finalizer的对象会慢430倍”
 * C:\Documents and Settings\Administrator>jps
 * 4232 Jps
 * 3236 TestObjectHasFinalize
 * 5272
 * C:\Documents and Settings\Administrator>jmap -histo:live 3236
 * 1: 90214 3608560 java.lang.ref.Finalizer
 * 2: 90210 1443360 test.java.MyFinalizer
 * 3: 2289 306544 [C
 * 4: 483 55144 java.lang.Class
 * <ul>
 * <li>
 * 没有提供finalize()方法的类，占用的堆内存更少，垃圾回收速度更快，而且JVM也不会创建那么多java.lang.ref.Finalizer对象。
 * <li>
 * 为什么有这么多TestObjectHasFinalize对象呢？很简单，垃圾回收的速度变慢了，对象的销毁速度小于对象的创建速度。
 * <li>为什么有这么多的java.lang.ref.Finalizer对象对象呢？这是JVM内部的机制，用来保证finalize只被调用一次。
 * <li>JVM不确保finalize一定会被执行，而且执行finalize的时间也不确定。
 * 依靠finalizer来关闭文件就是一个严重错误，因为打开文件的描述符是一个有限资源。JVM会延迟执行finalizer，所以大量文件会被保持在打开状态，
 * 当一个程序不再能打开文件的时候，就会运行失败。
 *
 * <li>接下来介绍下JVM执行finalize方法的一些理论知识。实现了finalize()的对象，创建和回收的过程都更耗时。创建时，
 * 会新建一个额外的 Finalizer 对象指向新创建的对象。 而回收时，至少需要经过两次GC。
 * <li>Finalizer内部维护了一个unfinalized链表，每次创建的Finalizer对象都会插入到该链表中。
 * <li>如果类没有实现finalize方法，那么进行垃圾回收的时候，可以直接从堆内存中释放该对象。这是速度最快，效率最高的方式
 * <li>如果类实现了finalize方法，进行GC的时候，如果发现某个对象只被java.lang.ref.Finalizer对象引用，
 * 那么会将该Finalizer对象加入到Finalizer类的引用队列
 * （F-Queue）中，并从unfinalized链表中删除该结点。这个过程是JVM在GC的时候自动完成的。
 * <li>含有finalize()的对象从内存中释放，至少需要两次GC。 第一次GC, 检测到对象只有被Finalizer引用，将这个对象放入
 * java.lang.ref.Finalizer.ReferenceQueue
 * 此时，因为Finalizer的引用，对象还无法被GC。
 * java.lang.ref.Finalizer$FinalizerThread 会不停的清理Queue的对象，remove掉当前元素，并执行对象的finalize方法。
 * 清理后对象没有任何引用，在下一次GC被回收。
 * <li>使用finalize容易导致OOM，因为如果创建对象的速度很快，那么Finalizer线程的回收速度赶不上创建速度，就会导致内存垃圾越来越多
 * </ul>
 *
 * https://blog.csdn.net/zwjyyy1203/article/details/97397680
 * 最后总结一下finalizer的生存周期： *
 * 在创建对象时，如果对象override了finalize()方法，jvm会同时创建一个Finalizer对象
 * 所有Finalizer对象组成了一个双向链表
 * 所有Finalizer对象都有一个名为queue的成员变量，指向的都是Finalizer类的静态Queue。
 * cms gc执行到mark阶段的最后时，会把需要gc的对象加入到Reference的pending list中。
 * 有一个专门的高级别线程Reference Handler处理pending list，把pending list中的对象取出来，放到这个对象所指的Reference Queue中，对于Finalizer对象来说，这个queue指向Finalizer类的静态Queue。
 * Finalizer类有一个专门的线程负责从queue中取对象，并且执行finalizer引用的对象的finalize函数。
 * jvm的代码还是非常复杂的，感觉这次看的还是太粗略，会有不少疏漏，过段时间得抽空完整的了解一下jvm源代码。
 *
 * @author Shuaijun He
 */
public class MyFinalizer {

    public static void main(String[] args) {

    }
}
