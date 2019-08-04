//package interview;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
///**
// * @Desc
// * @Author shuaijunhe
// * @CreateTime 2019/1/8 11:28
// **/
public class IntegerDemo2 {
//    public static void main(String[] args) throws Exception {
//        Integer a = 10, b = 20;
//        method1(a, b);
//        System.out.println("a=" + a + ",b=" + b);
//
//    }
//
//
//    private static void method (Integer a, Integer b) throws Exception {
//        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//        theUnsafe.setAccessible(true);
//        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
//        unsafe.getAndSetInt(a, unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")), 100);
//        unsafe.getAndSetInt(b, unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")), 200);
//    }
//
//    private static void method1 (Integer a, Integer b) throws Exception {
//
//        a *= 10;
//        // 此时已经不再指向原对象了，会指向新的对象
//        b *= 10;
//    }
}
