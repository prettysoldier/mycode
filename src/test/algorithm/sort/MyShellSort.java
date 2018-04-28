/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.List;

/**
 * 希尔排序
 *
 * @author Shuaijun He
 */
public class MyShellSort extends MySort {

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

    /*
     * (non-Javadoc)
     * @see test.algorithm.sort.MySort#sort(java.util.List)
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(List<T> a) {
        // TODO Auto-generated method stub

    }
}
