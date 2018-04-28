/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.Arrays;
import java.util.List;

import test.algorithm.priorityqueue.MyBinaryHeap;

/**
 * @author Shuaijun He
 */
public class MyBinaryHeapSort extends MySort {

    /*
     * (non-Javadoc)
     * @see test.algorithm.sort.MySort#sort(java.lang.Comparable[])
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
        T[] tmp = Arrays.copyOf(a, a.length);
        MyBinaryHeap<T> heap = new MyBinaryHeap<>(tmp);
        for (int i = 0; i < a.length; i++) {
            a[i] = heap.deleteMin();
        }
    }

    /*
     * (non-Javadoc)
     * @see test.algorithm.sort.MySort#sort(java.util.List)
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(List<T> a) {
    }

}
