package main.test.algorithm.sort;

import java.util.Arrays;

import main.test.algorithm.priorityqueue.MyBinaryHeap;

/**
 * 二叉堆排序 O(NlogN)
 *
 * @author Shuaijun He
 */
public class MyBinaryHeapSort extends MySortBase {
    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
        T[] tmp = Arrays.copyOf(a, a.length);
        MyBinaryHeap<T> heap = new MyBinaryHeap<>(tmp);
        for (int i = 0; i < a.length; i++) {
            a[i] = heap.deleteMin();
        }
    }
}
