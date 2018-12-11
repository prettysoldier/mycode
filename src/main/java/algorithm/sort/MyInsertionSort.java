package algorithm.sort;

/**
 * 插入排序：O(n^2)
 *
 * @author Shuaijun He
 */
public class MyInsertionSort extends MySortBase {

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        MyInsertionSort.insertionSort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] a,
            int left, int right) {
        int j;
        for (int i = left + 1; i <= right; i++) {
            T tmp = a[i];
            for (j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }
}
