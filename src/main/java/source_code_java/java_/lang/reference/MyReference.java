
package source_code_java.java_.lang.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * https://blog.csdn.net/zqz_zqz/article/details/79474314
 *
 * 理解 Reference 的关键点在于:
 * 1. pending队列
 *      成员变量 pending 和 discovered 共同构成了 pending队列。 discovered 指向下一个节点。
 *      进入这个队列的Reference对象需要满足两个条件：
 *          Reference所引用的对象已经不存在其它强引用；
 *          Reference对象在创建的时候，指定了ReferenceQueue；
 *   ReferenceQueue队列
 *      由 ReferenceQueue的head 和 Reference的next 共同实现 ReferenceQueue 队列。
 *      1）ReferenceQueue队列的作用: Reference引用的对象被回收时，Reference对象能否进入到pending队列，最终由ReferenceHander线程处理后，Reference就被放到了这个队列里面（Cleaner对象除外，后面有一篇专门讲Cleaner对象源码），然后我们就可以在这个ReferenceQueue里拿到reference,执行我们自己的操作（至于什么操作就看你想怎么用了），所以这个队列起到一个对象被回收时通知的作用；
 *      如果不带ReferenceQueue的话,要想知道Reference持有的对象是否被回收，就只有不断地轮训reference对象,通过判断里面的get是否为null(phantomReference对象不能这样做,其get始终返回null).
 *      这两种方法均有相应的使用场景,取决于实际的应用.如 WeakHashMap 中就选择去查询queue的数据,来判定是否有对象将被回收.而ThreadLocalMap,则采用判断get()是否为null来作处理;
 *
 *      2）对于带ReferenceQueue参数的构造方法，如果传入的队列为null，那么就会给成员变量queue赋值为ReferenceQueue.NULL队列,这个NULL是ReferenceQueue对象的一个继承了ReferenceQueue的内部类，它重写了入队方法enqueue，这个方法只有一个操作，直接返回 false，也就是这个对列不会存取任何数据,它起到状态标识的作用；
 *      3）ReferenceQueue队列是一个单向链表，ReferenceQueue里面只有一个header成员变量持有队列的队头，Reference对象是从队头做出队入队操作，所以它是一个后进先出的队列。
 *
 *
 * 2. Reference对象4种状态的转换；
 *      1) Active : 活动状态,对象存在强引用状态,还没有被回收;
 *      2) Pending : 垃圾回收器将没有强引用的Reference对象放入到pending队列中，等待ReferenceHander线程处理（前提是这个Reference对象创建的时候传入了ReferenceQueue，否则的话对象会直接进入Inactive状态）；
 *      3) Enqueued : ReferenceHander线程将pending队列中的对象取出来放到ReferenceQueue队列里；
 *      4) Inactive : 处于此状态的Reference对象可以被回收,并且其内部封装的对象也可以被回收掉了。
 *
 *      Reference对象的状态只需要通过成员变量next和queue来判断：
 *      1) Active:  next=null
 *      2) Pending:  next = this , queue = ReferenceQueue
 *      3) Enqueued:  queue = ReferenceQueue.ENQUEUED
 *      4) Inactive:  next = this  ,queue = ReferenceQueue.NULL; 

 * 3. ReferenceHander 线程的处理过程，它是如何将Pending与ReferenceQueue关联起来的；
 *      ReferenceHandler 线程是一个拥有最高优先级的守护线程，它是Reference类的一个内部类，在Reference类加载执行cinit的时候被初始化并启动；它的任务就是当pending队列不为空的时候，循环将pending队列里面的头部的Reference移除出来，如果这个对象是个Cleaner实例，那么就直接执行它的clean方法来执行清理工作；否则放入到它自己的ReferenceQueue里面；
 *
 * 4. Pending队列数据来源;
 *
 *
 *
 * @author Shuaijun He
 */
public class MyReference {

    public static void main(String[] args) throws InterruptedException {
//        MyReference.softReference();
        MyReference.weakReference();
//        MyReference.phantomReference();
    }

    /**
     * 内存不足时才回收。一般GC时不会回收。
     * 用于缓存
     *
     * @author Shuaijun He
     * @throws InterruptedException
     */
    private static void softReference() throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        SoftReference<Object> softRef = new SoftReference<>(obj, refQueue);
        System.out.println("SoftReference:" + softRef.get()); // java.lang.Object@f9f9d8
        System.out.println("SoftReference:" + softRef.isEnqueued());

        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println("SoftReference:" + softRef.get());

        Thread.sleep(200);
        System.out.println("SoftReference:" + softRef.isEnqueued());
    }

    /**
     * 弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾，可以通过弱引用的isEnQueued方法返回对象是否被垃圾回收器标记。
     * GC时一旦发现立马删除
     *
     * @author Shuaijun He
     * @throws InterruptedException
     */
    private static void weakReference() throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        WeakReference<Object> weakRef = new WeakReference<>(obj, refQueue);
        System.out.println("WeakReference:" + weakRef.get()); // java.lang.Object@f9f9d8
        System.out.println("WeakReference:" + weakRef.isEnqueued());
        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println("WeakReference:" + weakRef.isEnqueued());

        // 这里特别注意:poll是非阻塞的,remove是阻塞的.
        // JVM将弱引用放入引用队列需要一定的时间,所以这里先睡眠一会儿
        // System.out.println(refQueue.poll());// 这里有可能是null

        Thread.sleep(200);
        System.out.println("WeakReference:" + weakRef.isEnqueued());
        WeakReference<Object> weakRef1 = (WeakReference<Object>) refQueue
            .poll();
        System.out.println("WeakReference:" + weakRef1);
        System.out.println("WeakReference:" + weakRef1.get());

        // System.out.println(refQueue.remove());

    }

    /**
     * 通过虚引用的get方法永远获取到的数据为null
     * 虚引用主要用于检测对象是否已经从内存中删除。pf.isEnQueued()
     *
     * @author Shuaijun He
     * @throws InterruptedException
     */
    private static void phantomReference() throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<>(obj, refQueue);
        System.out.println("PhantomReference:" + phantom.get()); // 永远返回null
        System.out.println("PhantomReference:" + phantom.isEnqueued());// false 还没加到queue
        obj = null;
        System.gc();
        Thread.sleep(200);
        System.out.println("PhantomReference:" + phantom.isEnqueued());// true 加到了queue

        // 当GC发现了虚引用，GC会将phanRef插入进我们之前创建时传入的refQueue队列
        // 注意，此时phanRef所引用的obj对象，并没有被GC回收，在我们显式地调用refQueue.poll返回phanRef之后
        // 当GC第二次发现虚引用，而此时JVM将phanRef插入到refQueue会插入失败，此时GC才会对obj进行回收
        Thread.sleep(200);
        System.out.println("PhantomReference:" + phantom.isEnqueued());

        System.gc();
        Thread.sleep(200);
        System.out.println("PhantomReference:" + phantom.isEnqueued());

        while (true) {
            Thread.sleep(200);
            System.gc();
            System.out.println("PhantomReference:" + phantom.isEnqueued());
        }

//        System.out.println("PhantomReference:" + refQueue.poll().isEnqueued());
    }
}
