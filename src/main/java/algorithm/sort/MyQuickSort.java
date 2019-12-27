package algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 快速排序 最坏：O(N^2) 平均：O(NlogN) 取决于枢纽元的选取（三数中值分割法）
 * https://blog.csdn.net/Holmofy/article/details/71168530
 * @author Shuaijun He
 */
public class MyQuickSort extends MySortBase {

    /**
     * 用数组实现
     */
    @Override
    protected <T extends Comparable<? super T>> void sort(T[] a) {
//        quicksort(a, 0, a.length - 1);
//        div3ScanSort(a, 0, a.length - 1);
//        div3DualScanSort(a, 0, a.length - 1);
//        dualPivotQuickSort(a, 0, a.length - 1);
        Arrays.sort(a);
    }

    private static final int CUTOFF = 10;

    private static <T extends Comparable<? super T>> void quicksort(T[] a,
            int left, int right) {
        if (left + MyQuickSort.CUTOFF <= right) {
            T pivot = MyQuickSort.median3(a, left, right);
            int i = left, j = right - 1;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    MyQuickSort.swap(a, i, j);
                } else {
                    break;
                }
            }
            MyQuickSort.swap(a, i, right - 1);
            MyQuickSort.quicksort(a, left, i - 1);
            MyQuickSort.quicksort(a, i + 1, right);
        } else {
            MyInsertionSort.insertionSort(a, left, right);
        }
    }

    /**
     * 三数中值分割法
     * 
     * @author Administrator
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static <T extends Comparable<? super T>> T median3(T[] a, int left,
            int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[right]) > 0) {
            MyQuickSort.swap(a, center, right);
        }
        if (a[left].compareTo(a[right]) > 0) {
            MyQuickSort.swap(a, left, right);
        }
        if (a[left].compareTo(a[center]) > 0) {
            MyQuickSort.swap(a, left, center);
        }
        MyQuickSort.swap(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * 数组中两个元素互换
     * 
     * @author Administrator
     * @param a
     * @param l
     * @param r
     */
    public static <T extends Comparable<? super T>> void swap(T[] a, int l,
            int r) {
        T tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }

    private static <T extends Comparable<? super T>> void div3ScanSort(T[] items,
                                                                    int start, int end) {
        if (start < end) {
            T pivot = items[start];
            int i = start, j = end, k = start + 1;
            while (k <= j) {
                if (items[k].compareTo(pivot) < 0) {
                    swap(items, i, k);
                    i++;
                    k++;
                } else if (items[k].compareTo(pivot) > 0) {
                    swap(items, j, k);
                    j--;
                } else {
                    k++;
                }
            }
            div3ScanSort(items, start, i - 1);
            div3ScanSort(items, j + 1, end);
        }

    }

    private static <T extends Comparable<? super T>> void div3DualScanSort(T[] items, int start, int end) {
        if (start < end) {
            T pivot = items[start];
            int i = start, j = end, k = start + 1;

            OUT_LOOP:
            while (k <= j) {
                if (items[k].compareTo(pivot) < 0) {
                    swap(items, i++, k++);
                } else if (items[k] == pivot) {
                    k++;
                } else {
                    // j向左扫描，直到一个不大于pivot的元素
                    while (items[j].compareTo(pivot) > 0) {
                        j--;
                        if (k > j) {
                            // 后面的待排元素全大于pivot，直接结束排序
                            break OUT_LOOP;
                        }
                    }
                    if (items[j].compareTo(pivot) < 0) {
                        swap(items, j, k);
                        swap(items, i, k);
                        i++;
                    } else {
                        swap(items, j, k);
                    }
                    k++;
                    j--;
                }
            }
            div3DualScanSort(items, start, i - 1);
            div3DualScanSort(items, j + 1, end);
        }
    }


    private static <T extends Comparable<? super T>> void dualPivotQuickSort(T[] items, int start, int end) {
        if (start < end) {
            if (items[start].compareTo(items[end]) > 0) {
                swap(items, start, end);
            }
            T pivot1 = items[start];
            T pivot2 = items[end];

            int i = start, j = end, k = start + 1;

            BREAK_LOOP:
            while (k <= j) {
                if (items[k].compareTo(pivot1) < 0) {
                    swap(items, i++, k++);
                } else if (items[k].compareTo(pivot2) <= 0) {
                    k++;
                } else {
                    while (items[j].compareTo(pivot2) > 0) {
                        j--;
                        if (j < k) {
                            // 扫描终止
                            break BREAK_LOOP;
                        }
                    }

                    if (items[j].compareTo(pivot1) < 0) {
                        swap(items, j, k);
                        swap(items, i++, k);
                    } else {
                        swap(items, j, k);
                    }
                    k++;
                    j--;
                }
            }
            if(j - i < end - start){

                dualPivotQuickSort(items, start, i - 1);
                dualPivotQuickSort(items, i, j);
                dualPivotQuickSort(items, j + 1, end);
            }
        }
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

    private static final int M = 100_0000;

    public static void main(String[] args) {
        Random r = new Random();
        Integer[] a = new Integer[MyQuickSort.M];
        for (int i = 0; i < MyQuickSort.M; i++) {
            a[i] = r.nextInt(MyQuickSort.M);
        }
        new MyQuickSort().sortWithLog(a);
//        a = new Integer[]{3, 2, 1, 2, 6, 3, 5, 2, 7, 4};
//        new MyQuickSort().sortWithLog(a);
//        System.out.println(Arrays.toString(a));
    }
}
