package java_.util.concurrent.atomic;

import sun.misc.Unsafe;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) throws Exception{
        MyUnsafe myUnsafe = new MyUnsafe();
        System.out.println(myUnsafe.compareAndSetX(0, 1));
        System.out.println(myUnsafe.x);

        /**
         * arrayBaseOffset：返回指定类型数组的第一个元素地址相对于数组起始地址的偏移值。
         *
         * 数组对象:8个字节对象头(mark) + 4/8字节对象指针 + 4字节数组长度 + 数据区 + padding内存对齐(按照8的倍数对齐)
         * 对象指针究竟是4字节还是8字节要看是否开启指针压缩。Oracle JDK从6 update 23开始在64位系统上会默认开启压缩指针
         * 所以下面的值都是16
         */
        int doubleBase = unsafe.arrayBaseOffset(double[].class);
        System.out.println("double[] : " + doubleBase);
        int intBase = unsafe.arrayBaseOffset(int[].class);
        System.out.println("int[] : " + intBase);

        /**
         * 计算对象占用内存大小：Instrumentation
         */
        System.out.println("----------------");
        System.out.println(countMemoryByteSize(A.class.getName()));
        System.out.println("----------------");
        System.out.println(countArrayMemoryByteSize(new A[1]));
        System.out.println("----------------");

    }

    private static <T> long countArrayMemoryByteSize(T[] obj) throws Exception{
        if(obj == null){
            return 0;
        }
        if(obj.getClass().getName().startsWith("[")){
            return 8 + 4 + 4 + obj.length * countMemoryByteSize(obj.getClass().getName().substring(2, obj.getClass().getName().length() -1));
        }
        return countMemoryByteSize(obj.getClass().getName());
    }
    /**
     * 此方法无法过滤静态field
     * @param clazz
     * @return
     */
    private static long countMemoryByteSize(String clazz) throws Exception{
        if(clazz == null){
            return 0;
        }

        Field[] fields = Class.forName(clazz).getDeclaredFields();
        String[] max = {"0", null};
        String[] min = {"100000", null};
        for(Field f : fields){
            long offset = unsafe.objectFieldOffset(f);
            System.out.println(f.getName() + " offset: " + unsafe.objectFieldOffset(f));
            if(offset > Long.valueOf(max[0])){
                max[0] = offset + "";
                max[1] = f.getType().getName();
            }
            if(offset < Long.valueOf(min[0])){
                min[0] = offset + "";
                min[1] = f.getType().getName();
            }
        }
        if(max[1] == null){
            return 12;
        }

        long data = 0;
        switch (max[1]){
            case "byte":
            case "boolean":
                data = 1;
                break;
            case "char":
            case "short":
                data = 2;
                break;
            case "int":
            case "java.lang.String":
            case "java.lang.Object":
            case "float":
                data = 4;
                break;
            case "long":
            case "double":
                data = 8;
                break;
            default:
                data = 4;
        }
        // 一般非数组对象: 8个字节对象头(mark) + 4/8字节对象指针 + 数据区 + padding内存对齐(按照8的倍数对齐)
        return 8 + 4 + Long.valueOf(max[0]) - Long.valueOf(min[0]) + data;
    }

    private static Instrumentation inst;
    public static void premain(String agentArgs, Instrumentation instP){
        inst = instP;
    }
    public static long sizeOf(Object obj){
        return inst.getObjectSize(obj);
    }

    static class A {
        char c;
        boolean d;
        short e;
        float f;
        double g;
        int a;
        byte b;
        String str;
        Object o;
        Integer h;
        A i;
    }
}
