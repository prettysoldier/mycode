package interview;

import sun.misc.Unsafe;

import java.io.PrintStream;
import java.lang.reflect.Field;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2019/1/8 11:28
 **/
public class IntegerDemo {
    public static void main(String[] args) throws Exception {
        int a = 10, b = 20;
        // 下面两个方法都是改变打印方式
//        method(a, b);
//        method1(a, b);
        // 按值传递，不会改变原值
//        method2(a, b);
//        method3(a, b);
        System.out.println("a=" + a + ",b=" + b);

    }

    /**
     * 流重写
     * @param a
     * @param b
     */
    private static void method(int a, int b){

        PrintStream stream = new PrintStream(System.out){
            @Override
            public void println(String x) {
                if(x.startsWith("a")){
                    super.println(x + "0");
                }
                if(x.startsWith("b")){
                    super.println("b=200");
                }
            }
        };
        System.setOut(stream);
    }

    private static void method1(int a, int b){

        System.out.println("a=" + 100);
        System.out.println("b=" + 200);
        System.exit(0);
    }

    private static void method2 (Integer a, Integer b) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        unsafe.getAndSetInt(a, unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")), 100);
        unsafe.getAndSetInt(b, unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")), 200);
    }

    private static void method3 (Integer a, Integer b) throws Exception {

        a *= 10;
        // 此时已经不再指向原对象了，会指向新的对象
        b *= 10;
    }
}
