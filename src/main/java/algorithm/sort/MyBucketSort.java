package algorithm.sort;

import java.util.Random;

/**
 * 桶排序：时间复杂度O(M+N)。 N:待排序的数组大小；M:待排序数组中的最大值+1。
 * 速度达到线性时间，但是耗费内存。
 *
 * @author Administrator
 */
public class MyBucketSort extends MySortBase {

    private static final int M = Client.SIZE;

    @Override
    protected void sort4Integer(Integer[] a) {
        MyBucketSort.bucketSort(a, a.length);
    }

    public static void bucketSort(Integer[] a, int m) {
        Integer[] buckets = new Integer[m];
//        for (int i = 0; i < m; i++) {
//            buckets[i] = 0;
//        }
        for (Integer t : a) {
            buckets[t]++;
        }
        int j = 0;
        for (int i = 0; i < buckets.length; i++) {
            int count = buckets[i];
            while (count-- > 0) {
                a[j++] = i;
            }
        }
    }

    public static void main(String[] args) {
        kthSmallest(3, new int[]{3,4,1,2,5});
        Random r = new Random();
        Integer[] a = new Integer[Client.SIZE];
        for (int i = 0; i < Client.SIZE; i++) {
            a[i] = r.nextInt(MyBucketSort.M);
        }
        new MyBucketSort().sortWithLog4Integer(a);
    }


    public static int kthSmallest(int k, int[] nums) {
        // write your code here
        int[] buckets = new int[2000];
        for(int i = 0; i < nums.length; i++){
            buckets[nums[i]]++;
        }
        int j = 0;
        for(int i = 0; i < buckets.length; i++){
            j += buckets[i];
            if(j >= k){
                return i;
            }
        }
        return 0;
    }

}
