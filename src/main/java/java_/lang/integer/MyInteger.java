
package java_.lang.integer;

/**
 * @author Shuaijun He
 */
public class MyInteger {

    /**
     * 1.Integer缓存范围[-128,127]
     * String integerCacheHighPropValue =
     * sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
     * 可以通过VM变量指定。但是不能超过Integer.MAX_VALUE - (-low) -1，因为缓存数组最大是Integer.MAX_VALUE
     *
     * 2. java没有任何无符号类型unsigned
     *
     * @author Shuaijun He
     * @param args
     */
    public static void main(String[] args) {
        // 报错：Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//        int[] max = new int[Integer.MAX_VALUE -1];
        // 报错：Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        Object[] o = new Object[Integer.MAX_VALUE -2];

        int a = 0b101;
        System.out.println(a);
        byte b = 0b101;
        System.out.println(b);
        byte b1 = 'a';
        System.out.println(b1);
        char c = 97;
        System.out.println(c);
        char c1 = '的';
        System.out.println(c1);

        // 下划线提高可读性，编译器会除去
        int i1 = 1_000_000;

    }
}
