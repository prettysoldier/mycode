/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author Shuaijun He
 */
public class MyReference {

    public static void main(String[] args) {
        MyReference.softReference();
        MyReference.weakReference();
        MyReference.phantomReference();
    }

    /**
     * 内存不足时才回收。一般GC时不会回收。
     * 用于缓存
     *
     * @author Shuaijun He
     */
    private static void softReference() {
        Object obj = new Object();
        SoftReference<Object> ref = new SoftReference<>(obj);
        obj = null;
        System.gc();
        System.out.println("SoftReference:" + ref.get());
        System.out.println("SoftReference:" + ref.isEnqueued());
    }

    /**
     * 弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾，可以通过弱引用的isEnQueued方法返回对象是否被垃圾回收器标记。
     * GC时一旦发现立马删除
     *
     * @author Shuaijun He
     */
    private static void weakReference() {
        Object obj = new Object();
        WeakReference<Object> ref = new WeakReference<>(obj);
        obj = null;
        System.gc();
        System.out.println("WeakReference:" + ref.get());
        System.out.println("WeakReference:" + ref.isEnqueued());
    }

    /**
     * 通过虚引用的get方法永远获取到的数据为null
     * 虚引用主要用于检测对象是否已经从内存中删除。pf.isEnQueued()
     *
     * @author Shuaijun He
     */
    private static void phantomReference() {
        Object obj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> ref = new PhantomReference<>(obj, queue);
//        obj = null;
//        System.gc();
        System.out.println("PhantomReference:" + ref.get());
        System.out.println("PhantomReference:" + ref.isEnqueued());
    }
}
