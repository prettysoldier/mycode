
package java_.lang.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
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
        System.out.println("SoftReference:" + refQueue.poll());// null

        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println("SoftReference:" + softRef.get());

        Thread.sleep(200);
        System.out.println("SoftReference:" + refQueue.poll());
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
        System.out.println("WeakReference:" + refQueue.poll());// null
        System.out.println("WeakReference:" + weakRef);
        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println("WeakReference:" + weakRef.get());

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
