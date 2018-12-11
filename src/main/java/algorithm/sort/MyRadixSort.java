package main.test.algorithm.sort;

import java.util.ArrayList;

/**
 * 0:48
 * a:97
 * A:65
 * TODO java 字符串排序和比较的实现
 * TODO 计数基数排序counting radix sort
 * 
 * @author Administrator
 */
public class MyRadixSort extends MySortBase {

    private static <T> void radixSortA(T[] arr) {
        MyRadixSort.radixSortA(arr, arr[0].toString().length());
    }

    private static <T> void radixSortA(T[] arr, int num) {
        final int bucketCount = 256;
        ArrayList<T>[] buckets = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int pos = num - 1; pos >= 0; pos--) {
            for (T s : arr) {
                buckets[s.toString().charAt(pos)].add(s);
            }

            int idx = 0;
            for (ArrayList<T> thisBucket : buckets) {
                for (T s : thisBucket) {
                    arr[idx++] = s;
                }
                thisBucket.clear();
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = { "wsf", "gyh", "dfr", "azz", "wsf" };
        MyRadixSort.radixSortA(arr, 3);
        for (String s : arr) {
            System.out.print(s + ",");
        }
        System.out.println();

        Integer[] arr1 = { 345, 122, 424, 122, 1222 };
        MyRadixSort.radixSortA(arr1);
        for (Integer s : arr1) {
            System.out.print(s + ",");
        }
        System.out.println();
    }
}
