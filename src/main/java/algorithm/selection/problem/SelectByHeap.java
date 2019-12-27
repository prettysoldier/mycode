package algorithm.selection.problem;

import java.util.Random;

import algorithm.priorityqueue.MyBinaryHeap;

/**
 * 堆
 * 
 * @author Administrator
 */
public class SelectByHeap extends MySelect {

    /**
     * 找出数组中第k大的元素
     * 
     * @author Administrator
     * @param a
     * @param k
     * @return
     */
    @Override
    public <T extends Comparable<? super T>> T select(T[] a, int k) {
        if (k < 1 || k > a.length) {
            return null;
        }
        MyBinaryHeap<T> heap = new MyBinaryHeap<>(a);
        T minT = null;
        while (k-- > 0) {
            minT = heap.deleteTop();
        }
        return minT;
    }

    private static final int SIZE = 1_0000;
    private static boolean log = false;

    static {
        SelectByHeap.log = true;
    }

    public static void main(String[] args) {
        Random r = new Random();
        Integer[] a = new Integer[SelectByHeap.SIZE];
        for (int i = 0; i < SelectByHeap.SIZE; i++) {
            a[i] = r.nextInt(SelectByHeap.SIZE);
        }
        if (SelectByHeap.log) {
            for (Integer i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
        System.out.println(new SelectByHeap().select(a, a.length / 2));
    }
}
