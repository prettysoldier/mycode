package java_.array;

import java.util.Arrays;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 13:56
 **/
public class ArrayDemo {
    public static void main(String[] args) {
        base();
//        arrayMaxLengthTest();
//        arraysTest();

    }

    private static void arraysTest() {
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
        int[][] i = {{1},{3, 2}};
        // 打印多维数组
        System.out.println(Arrays.deepToString(i));
        // 允许数组长度是0，与null不同
        int i2[] = new int[0];

        // 这种{}，说明数组长度为0
        int[] i4 = {};
        // 此行编译报错，int[]等基本类型的数组 与Object[]类型不同
//        System.out.println(Arrays.deepToString(i4));
        System.out.println(Arrays.toString(i4));
        System.out.println("i4.length " + i4.length);

        int[][][] i5 = {{{}},{}};
        System.out.println(Arrays.deepToString(i5));


//        int[] i6 = new int[];
        int[][] i6 = new int[1][];


    }

    /**
     * 测试数组初始化时的最大长度
     * 测试结果：Integer.MAX_VALUE - 2
     */
    private static void arrayMaxLengthTest() {
        // -1 编译不会报错，运行时报错：java.lang.NegativeArraySizeException
//        String[] arr = new String[-1];

        // Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//        String[] arr1 = new String[Integer.MAX_VALUE];

        // Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//        String[] arr2 = new String[Integer.MAX_VALUE - 1];

        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        byte[] arr3 = new byte[Integer.MAX_VALUE - 2];

        // length是int类型，说明不能超过int的最大值。如果数组中每个元素占用1B，最大是2G。
//        int length = new int[2].length;
    }
}
