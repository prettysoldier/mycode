package source_code_java.java_.syntax.array;

import java.util.Arrays;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 13:56
 **/
public class ArrayDemo {
    public static void main(String[] args) {
//        base();
        arrayMaxLengthTest();
//        arraysTest();
//        arrayClone();
//        arrayDeepClone();
    }

    /**
     * Arrays.BinarySearch的返回值问题
     */
    private static void returnValOfArraysBinarySearch() {
        int[] i = {1, 2, 4, 5};
        System.out.println(Arrays.binarySearch(i, 4) == 2);
        // 找不到返回r = -3。-r-1 = 2 就是新节点插入时的index。
        // 或者说，返回值是r = -(insertion point) -1
        // 由于y=-x-1是关于y=x对称的，所以上面的解释都是可以的。为什么不是-(insertion point)？因为0代表了数组的第一个元素，表示找到了元素
        // ，不能用来表示没有找到元素时，插入的index。
        System.out.println(Arrays.binarySearch(i, 3));
    }

    private static void base() {
        // 简化形式
        int[][] i = {{1}, {3, 2}};
        // 打印多维数组
        System.out.println(Arrays.deepToString(i));
        // 允许数组长度是0，是有意义的，与null不同
        int[] i2 = new int[0];
        int[] i4 = {};

        // Arrays.deepToString(Object[] a)
        // 此行编译报错，int[]等基本类型的数组 与Object[]类型不同
//        System.out.println(Arrays.deepToString(i4));
        System.out.println(Arrays.toString(i4));
        System.out.println("i4.length " + i4.length);

        int[][][] i5 = {{{}}, {}};
        System.out.println(Arrays.deepToString(i5));

//        int[] i6 = new int[];
        int[][] i6 = new int[1][];

    }

    /**
     * 测试数组初始化时的最大长度
     * 测试结果：Integer.MAX_VALUE - 2
     *
     * 虚拟机对数组大小的限制与平台有关，通常都位于10亿到21亿元素之间
     * 该错误是由JVM的本地代码抛出的. 它发生在为一个数组分配内存之前, 这时JVM会执行一个与平台有关的检查:
     * 是否待分配的数据结构在这个平台是可寻址的.      *
     * 你很少面对这个错误的原因是 Java 数组是由 int类型索引的. 在Java中最大的正整数是: 2^31 -1 = 2,147,483,647.
     * 平台相关的限制确实相当接近这个数字 – 例如在MacBook Pro, Java 1.7 Integer.MAX_VALUE-2 元素.
     * 但是这个限制有时也并不是那么高 – 在32-bit Linux, OpenJDK 6上, 你会在分配一个大约11亿元素的数组时候报错.
     * 要知道你的特定环境的限制大小, 可以运行下面这个小的测试程序.
     *
     * 不推荐的方法：sun.misc.Unsafe 类使您可以像在 C 中一样直接分配内存。
     */
    private static void arrayMaxLengthTest() {
        // -1 编译不会报错，运行时报错：java.lang.NegativeArraySizeException
//        String[] arr = new String[-1];

        // Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//        String[] arr1 = new String[Integer.MAX_VALUE];

        // Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//        String[] arr2 = new String[Integer.MAX_VALUE - 1];

        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        // 当把内存调得足够大（ -Xmx3550m -Xms3550m）时不报错，说明数组默认最大的数组长度是Integer.MAX_VALUE - 2
        byte[] arr3 = new byte[Integer.MAX_VALUE - 2];

        // length是int类型，说明不能超过int的最大值。如果数组中每个元素占用1B，最大是2G。
//        int length = new int[2].length;

//        System.out.println(length);

        System.out.println("正常");
    }

    /**
     * 结论：
     * 一维数组：深克隆；（重新分配空间，并将元素复制过去）
     * 二维数组：浅克隆。（只传递引用）
     */
    private static void arrayClone() {
        int[] a = {3, 1, 4, 2, 5};
        int[] b = a.clone();
        System.out.println(Arrays.toString(b));
        b[0] = 10;
        System.out.println(Arrays.toString(b));
        System.out.println("---------");

        int[][] a1 = {{3, 1, 4, 2, 5}, {4, 2}};
        int[][] b1 = a1.clone();
        System.out.println(Arrays.deepToString(b1));
        b1[0][0] = 10;
        System.out.println(b1[0][0] + "  " + a1[0][0]);
        System.out.println(a1[0] == b1[0]);
        System.out.println("---------");

    }

    private static void arrayDeepClone() {

        int[][] a = {{3, 1, 4, 2, 5}, {4, 2}};
        int[][] b = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i].clone();
        }
        b[0][0] = 10;
        System.out.println(b[0][0] + "  " + a[0][0]);
        System.out.println(b[0] == a[0]);
    }
}
