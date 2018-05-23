package test.algorithm.sort;

import java.util.List;

/**
 * @author Administrator
 */
public class MySortBase extends MySort {

    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
    }

    @Override
    protected <T extends Comparable<? super T>> void sort(List<T> a) {
    }

    @Override
    protected void sort4Integer(Integer[] a) {
    }
}
