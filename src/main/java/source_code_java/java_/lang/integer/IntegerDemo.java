package source_code_java.java_.lang.integer;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2019/1/8 11:28
 **/
public class IntegerDemo {
    public static void main(String[] args) throws Exception {

        int a = 10, b = 20;
        /**
         * 按值传递，不会改变原值，参数是Integer，为什么不是按引用传递？
         * 因为，会调用Integer.valueOf(int i)方法，默认属于[-128, 127]之间的数，会返回缓存值
         * 缓存里是 Integer 对象。Integer 的value 是final的，不可变的。Integer 的 加减乘除都会指向新的对象。
         *
         **/
//        method2(a, b);
        method3(a, b);
        System.out.println("a=" + a + ",b=" + b);
        /**
         * 如果参数和传参都是对象，也不会改变原来的值！
         * 因为 加减乘除等运算符 在计算结束后 会调用Integer.valueOf(int i)方法，要么新的引用。
         */
        Integer c = new Integer(11);
        Integer d = new Integer(21);
        method3(c, d);
        System.out.println("c=" + c + ",d=" + d);

        /**
         * 如果参数和传参都是对象，也不会改变原来的值！
         * 因为 加减乘除等运算符 在计算结束后 会调用Integer.valueOf(int i)方法，要么新的引用。
         */
        Integer e = new Integer(200);
        Integer f = new Integer(300);
        method3(e, f);
        System.out.println("e=" + e + ",f=" + f);


    }

    /**
     * 修改 IntegerCache
     * @param a
     * @param b
     * @throws Exception
     */
    private static void method2 (Integer a, Integer b) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        unsafe.getAndSetInt(a, unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")), 100);
        unsafe.getAndSetInt(b, unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")), 200);
        System.out.println("method2: a=" + a + ",b=" + b);
        System.out.println("method2: Integer.valueOf(10)=" + Integer.valueOf(10) + ",Integer.valueOf(20)=" + Integer.valueOf(20));
        /**
         * Integer.valueOf(10)=100 !! 已经改变了设计者的原意，所以Unsafe是很危险的操作，一定要慎用！！
         */
    }

    private static void method3 (Integer a, Integer b) throws Exception {

        a = a + 10;
        // 此时已经不再指向原对象了，会指向新的对象
        b = b + 10;
        System.out.println("method3: a=" + a + ",b=" + b);
    }
}
