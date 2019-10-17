package java_.util.concurrent.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author shuaijunhe
 * @create 2019/10/16
 * @description
 */
public class MyUnsafe {
    /**
     * 内存管理、操纵对象、阻塞/唤醒线程
     * Unsafe是一个final类，不能被继承，也没有公共的构造器，只能通过工厂方法getUnsafe获得Unsafe的单例。
     *
     * Unsafe unsafe = Unsafe.getUnsafe(); 通过这样的方式获得Unsafe的实力会抛出异常信息，因为在unsafe的源码中会有对安全性的检查
     */
    private static final Unsafe unsafe;
    public static Unsafe getUnsafeInstance() throws Exception{
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe)f.get(Unsafe.class);
    }


    private volatile int x = 0;
    private static final long xOffset;

    static {
        try {
            unsafe = getUnsafeInstance();
            xOffset = unsafe.objectFieldOffset(MyUnsafe.class.getDeclaredField("x"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private final boolean compareAndSetX(int expect, int update) {
        return unsafe.compareAndSwapInt(this, xOffset, expect, update);
    }

    public static void main(String[] args) {
        MyUnsafe myUnsafe = new MyUnsafe();
        System.out.println(myUnsafe.compareAndSetX(0, 1));
        System.out.println(myUnsafe.x);
    }
}
