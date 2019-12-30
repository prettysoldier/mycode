package algorithm.sort;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author heshuaijun
 * @date 2019/12/29 13:00
 */
public class MyBitSetSort {
    /**
     *
     * @param a
     * @param max a中数字的最大值
     */
    public static int[] bitSort(int[] a, int max) {
        BitSet set = new BitSet(max);
        for (int t : a) {
            set.flip(t);
        }
        // 方法1：nextSetBit()
        int tmp = 0;
        int i = 0;
        while((tmp = set.nextSetBit(tmp + 1)) != -1){
            a[i++] = tmp;
        }
        return a;

        // 方法二：stream()
//        return set.stream().toArray();
    }
    private static final int M = 1000;
    public static void main (String[] args) {
        Random r = new Random();
        Set<Integer> set = new HashSet<>(M);
        int[] a = new int[M];
        for (int i = 0; i < M; i++) {
            int tmp = r.nextInt(M);
            if(!set.contains(tmp)){
                set.add(tmp);
                a[i] = tmp;
            }
        }
        long start = System.nanoTime();
        int[] result = bitSort(a.clone(), M);
        long spend = System.nanoTime() - start;
        System.out.println("耗时：" + spend);
        System.out.println(Arrays.toString(result));
    }
}
