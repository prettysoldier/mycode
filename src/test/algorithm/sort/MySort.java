/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.List;

/**
 * @author Shuaijun He
 */
public abstract class MySort {

    protected abstract <T extends Comparable<? super T>> void sort(T[] a);

    protected abstract <T extends Comparable<? super T>> void sort(List<T> a);

    public <T extends Comparable<? super T>> void sortWithLog(T[] a) {

        if (Client.log) {
            for (T i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

        long start = System.currentTimeMillis();
        this.sort(a);
        long spent = System.currentTimeMillis() - start;
        System.out.println(this.getClass() + "花费：" + spent + "ms");

        if (Client.log) {
            for (T i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

    }

    public <T extends Comparable<? super T>> void sortWithLog(List<T> a) {

        if (Client.log) {
            for (T i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

        long start = System.currentTimeMillis();
        this.sort(a);
        long spent = System.currentTimeMillis() - start;
        System.out.println(this.getClass() + "花费：" + spent + "ms");

        if (Client.log) {
            for (T i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

    }
}