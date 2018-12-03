package test.algorithm.sort;

import java.util.Random;

/**
 * 归并排序 O(NlogN)
 * 算法与LegacyMergeSort遗产归并排序相同
 * 据说TimSort的性能更高
 *
 * @author Shuaijun He
 */
public class MyMergeSort extends MySortBase {
    public static void main(String[] args) {
        Random r = new Random();
        Integer[] arr = new Integer[Client.SIZE];
        for (int i = 0; i < Client.SIZE; i++) {
            arr[i] = r.nextInt(Client.SIZE);
        }
        new MyMergeSort().sortWithLog(arr);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] t) {
        @SuppressWarnings("unchecked")
        T[] tmpArray = (T[]) new Comparable[t.length];
        MyMergeSort.mergeSort(t, tmpArray, 0, t.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] t,
            T[] tmpArray, int left, int right) {
        if (left < right) {
            int center = left + right >>> 1;
            MyMergeSort.mergeSort(t, tmpArray, left, center);
            MyMergeSort.mergeSort(t, tmpArray, center + 1, right);
            // 把两个已排序的数组合并
            MyMergeSort.merge(t, tmpArray, left, center + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] t,
            T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int currTmp = leftPos;
        int count = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (t[leftPos].compareTo(t[rightPos]) <= 0) {
                tmpArray[currTmp++] = t[leftPos++];
            } else {
                tmpArray[currTmp++] = t[rightPos++];
            }
        }
        while (leftPos <= leftEnd) {
            tmpArray[currTmp++] = t[leftPos++];
        }
        while (rightPos <= rightEnd) {
            tmpArray[currTmp++] = t[rightPos++];
        }

        for (int i = 0; i < count; i++, rightEnd--) {
            t[rightEnd] = tmpArray[rightEnd];
        }
    }

    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {

        MyMergeSort.mergeSort(a);
//        Arrays.sort(a);
    }


}
