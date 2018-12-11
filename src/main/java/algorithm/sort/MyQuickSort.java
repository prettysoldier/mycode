package algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 快速排序 最坏：O(N^2) 平均：O(NlogN) 取决于枢纽元的选取（三数中值分割法）
 * 
 * @author Shuaijun He
 */
public class MyQuickSort extends MySortBase {

    /**
     * 用数组实现
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
        MyQuickSort.quicksort(a, 0, a.length - 1);
    }

    private static final int CUTOFF = 10;

    private static <T extends Comparable<? super T>> void quicksort(T[] a,
            int left, int right) {
        if (left + MyQuickSort.CUTOFF <= right) {
            T pivot = MyQuickSort.median3(a, left, right);
            int i = left, j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    MyQuickSort.swap(a, i, j);
                } else {
                    break;
                }
            }
            MyQuickSort.swap(a, i, right - 1);
            MyQuickSort.quicksort(a, left, i - 1);
            MyQuickSort.quicksort(a, i + 1, right);
        } else {
            MyInsertionSort.insertionSort(a, left, right);
        }
    }

    /**
     * 三数中值分割法
     * 
     * @author Administrator
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static <T extends Comparable<? super T>> T median3(T[] a, int left,
            int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[right]) > 0) {
            MyQuickSort.swap(a, center, right);
        }
        if (a[left].compareTo(a[right]) > 0) {
            MyQuickSort.swap(a, left, right);
        }
        if (a[left].compareTo(a[center]) > 0) {
            MyQuickSort.swap(a, left, center);
        }
        MyQuickSort.swap(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * 数组中两个元素互换
     * 
     * @author Administrator
     * @param a
     * @param l
     * @param r
     */
    public static <T extends Comparable<? super T>> void swap(T[] a, int l,
            int r) {
        T tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }

    /**
     * 实现1：用list实现
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(List<T> items) {
        if (items.size() > 1) {
            List<T> smaller = new ArrayList<>();
            List<T> same = new ArrayList<>();
            List<T> larger = new ArrayList<>();
            T chosen = items.get(items.size() / 2);
            for (T i : items) {
                if (i.compareTo(chosen) < 0) {
                    smaller.add(i);
                } else if (i.compareTo(chosen) > 0) {
                    larger.add(i);
                } else {
                    same.add(i);
                }
            }

            this.sort(smaller);
            this.sort(larger);

            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }
    }

    private static final int M = 1_000_0000;

    public static void main(String[] args) {
        Random r = new Random();
        Integer[] a = new Integer[MyQuickSort.M];
        for (int i = 0; i < MyQuickSort.M; i++) {
            a[i] = r.nextInt(MyQuickSort.M);
        }
        new MyQuickSort().sortWithLog(a);
    }
}
