package demo;

/**
 * @author hsj
 * @create 2020/1/6
 */
public class ArrayMaxLength {
    public static void main(String[] args) {
        arrayMaxLengthTest();
    }

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
}
