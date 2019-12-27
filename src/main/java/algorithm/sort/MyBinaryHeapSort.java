package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

import algorithm.priorityqueue.MyBinaryHeap;

/**
 * 二叉堆排序 O(NlogN)
 *
 * @author Shuaijun He
 */
public class MyBinaryHeapSort extends MySortBase {
    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
        MyBinaryHeap<T> heap = new MyBinaryHeap<>(a, true);
        for (int i = 0; i < a.length; i++) {
            a[a.length - i - 1] = heap.deleteTop();
        }
    }

    private static final int M = 1_00;

    public static void main(String[] args) {
        Random r = new Random();
        Integer[] a = new Integer[M];
        for (int i = 0; i < M; i++) {
            a[i] = r.nextInt(M);
        }
        new MyBinaryHeapSort().sortWithLog(a);
        System.out.println(Arrays.toString(a));
    }
}
