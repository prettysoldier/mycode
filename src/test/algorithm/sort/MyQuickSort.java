/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Shuaijun He
 */
public class MyQuickSort extends MySortBase {

    private static final int CUTOFF = 10;
    
    /**
     * 用数组实现
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
        quicksort(a,0,a.length-1);
    }
    
    private static <T extends Comparable<? super T>> void quicksort(T[] a, int left, int right) {
        if(left+CUTOFF<=right) {
            T pivot = median3(a, left, right);
            int i = left,j=right-1;
            for(;;) {
                while(a[++i].compareTo(pivot)<0) {}
                while(a[--j].compareTo(pivot)>0) {}
                if(i<j) {
                    swap(a, i, j);
                }else {
                    break;
                }
            }
            swap(a, i, right-1);
            quicksort(a, left, i-1);
            quicksort(a, i+1, right);
        }
        else {
            MyInsertionSort.insertionSort(a, left, right);
        }
    } 
    /**
     * 三数中值分割法
     * @author Administrator
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static <T extends Comparable<? super T>> T median3(T[] a, int left, int right) {
        int center = (left + right)/2;
        if(a[center].compareTo(a[right])>0) {
            swap(a, center, right);
        }
        if(a[left].compareTo(a[right])>0) {
            swap(a, left, right);
        }
        if(a[left].compareTo(a[center])>0) {
            swap(a, left, center);
        }
        swap(a, center, right-1);
        return a[right-1];
    }
    /**
     * 数组中两个元素互换
     * @author Administrator
     * @param a
     * @param l
     * @param r
     */
    public static <T extends Comparable<? super T>> void swap(T[] a, int l, int r) {
        T tmp = a[l];
        a[l]=a[r];
        a[r]=tmp;
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
        Integer[] a =  new Integer[M];
        for(int i = 0; i<M; i++ ){
            a[i]=r.nextInt(M);
        }
        new MyQuickSort().sortWithLog(a);
    }
}
