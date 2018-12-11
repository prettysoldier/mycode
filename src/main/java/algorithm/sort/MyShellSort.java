package main.test.algorithm.sort;

/**
 * 希尔排序Shellsort（缩减增量排序diminishing increment sort）
 * TODO 使用Hibbard增量的希尔排序
 *
 * @author Shuaijun He
 */
public class MyShellSort extends MySortBase {

    /**
     * O(n^2)
     */
    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }
}
