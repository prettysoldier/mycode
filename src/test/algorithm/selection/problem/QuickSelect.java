/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.selection.problem;

import test.algorithm.sort.MyInsertionSort;
import test.algorithm.sort.MyQuickSort;

/**
 * @author Administrator
 *
 */
public class QuickSelect extends MySelect{
    private static final int CUTOFF = 10;
    @Override
    protected <T extends Comparable<? super T>> T select(T[] a, int k) {
        quickSelect(a, 0, a.length-1, k);
        return a[k-1];
    }
    
    private  <T extends Comparable<? super T>> void quickSelect(T[] a, int left, int right,int k) {
        if(left + CUTOFF <= right) {
            T pivot = MyQuickSort.median3(a, left, right);
            int i = left, j = right-1;
            for(;;) {
                while(a[++i].compareTo(pivot)<0) {};
                while(a[--j].compareTo(pivot)>0) {};
                if(i<j) {
                    MyQuickSort.swap(a, i, j);
                }else {
                    break;
                }
                MyQuickSort.swap(a, j, right-1);
                if(k<=i) {
                    quickSelect(a, left, i-1, k);
                }else if(k>i){
                    quickSelect(a, i+1, right, k);
                }
            }
        }else {
            MyInsertionSort.insertionSort(a, left, right);
        }
    }
    
    public static void main(String[] args) {
        Integer[] a = {0,9,7,8,5,4,8,2,1,6,11,12,30};
        System.out.println(new QuickSelect().select(a, 4));
        for (int i : a) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

}
