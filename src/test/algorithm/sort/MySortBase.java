/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class MySortBase extends MySort{

    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {}

    @Override
    protected <T extends Comparable<? super T>> void sort(List<T> a) {}

    @Override
    protected void sort4Integer(Integer[] a) {}
}
