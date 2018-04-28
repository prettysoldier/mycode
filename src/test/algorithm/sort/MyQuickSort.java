/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuaijun He
 */
public class MyQuickSort extends MySort {

    /*
     * (non-Javadoc)
     * @see test.algorithm.sort.MySort#sort(java.lang.Comparable[])
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see test.algorithm.sort.MySort#sort(java.util.List)
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
}
