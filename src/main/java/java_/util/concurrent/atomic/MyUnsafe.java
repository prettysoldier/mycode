package java_.util.concurrent.atomic;

import java_.lang.instrument.PreMainTraceAgent;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Unsafe的强大功能：内存管理、操纵对象、阻塞/唤醒线程
 * @author shuaijunhe
 * @create 2019/10/16
 * @description
 */
public class MyUnsafe {
    /**
     * Unsafe是一个final类，不能被继承，也没有公共的构造器，只能通过工厂方法getUnsafe获得Unsafe的单例。
     *
     * Unsafe unsafe = Unsafe.getUnsafe(); 通过这样的方式获得Unsafe的实力会抛出异常信息，因为在unsafe的源码中会有对安全性的检查
     */
    private static final Unsafe unsafe;
    public static Unsafe getUnsafeInstance() throws Exception{
        /**
         * 缓存Field、Method可以加快速度，报错后重新获取
         */
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        // f.get(null) 和 f.get(Unsafe.class) 效果是一样的
        return (Unsafe)f.get(null);
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
        System.out.println("通过unsafe原子地修改属性");
        MyUnsafe myUnsafe = new MyUnsafe();
        System.out.println(myUnsafe.compareAndSetX(0, 1));
        System.out.println(myUnsafe.x);

        System.out.println("----------------");
        /**
         * arrayBaseOffset：返回指定类型数组的第一个元素地址相对于数组起始地址的偏移值。
         * 一般是16，如果关闭指针压缩的话是22。UseCompressedOops
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
        System.out.println("对象A的内存占用：" + countMemoryByteSize(A.class.getName()));
        System.out.println("----------------");
        System.out.println("数组A[1]的内存占用：" + countArrayMemoryByteSize(new A[1]));
        System.out.println("----------------");
        /**
         * 添加vm参数： -javaagent:E:\data\idea_workspace\mycode\out\artifacts\PreMainTraceAgent\PreMainTraceAgent.jar=asdf
         */
        System.out.println("通过Instrumentation获得对象A的内存：" + PreMainTraceAgent.sizeOf(new A()));

    }

    /**
     * 计算一个数组对象的内存占用
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
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
            // 过滤静态域
            int modifiers = f.getModifiers();
            if(Modifier.isStatic(modifiers)){
                continue;
            }
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



    public static class A {
        char c;
        boolean d;
        short e;
        float f;
        double g;
        int a;
        byte b;
        String str;
        // 不占用内存
        static String str1;
        Object o;
        Integer h = null;
        MyUnsafe u = null;
        MyAtomicInteger u1 = null;
        /**
         * 两种计算方式不一致的原因是？
         * 当一个对象含有自己（A i = new A()）时，会发生StackOverflowError，当这个值为null时
         */
//         A i = null;
    }
}
