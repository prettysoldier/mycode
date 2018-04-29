/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.List;

/**
 * @author Shuaijun He
 */
public class MyInsertionSort extends MySort {

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        insertionSort(a, 0, a.length-1);
    }
    
    public static <T extends Comparable<? super T>> void insertionSort(T[] a, int left, int right) {
        int j;
        for (int i = left + 1; i <= right; i++) {
            T tmp = a[i];
            for (j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
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
