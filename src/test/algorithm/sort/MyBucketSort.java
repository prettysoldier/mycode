/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.sort;

import java.util.Random;


/**
 * @author Administrator
 *
 */
public class MyBucketSort extends MySortBase{

    
    private static final int M = 1_000_0000; 
    
    @Override
    protected void sort4Integer(Integer[] a) {
        bucketSort(a,Client.SIZE);
    }
    
    public static void bucketSort(Integer[] a, int m) {
        Integer[] buckets =  new Integer[m];
        for(int i = 0; i<m; i++ ) {
            buckets[i]=0;
        }
        for(Integer t : a) {
            buckets[t]++;
        }
        int j=0;
        for(int i=0;i< buckets.length;i++) {
            int count = buckets[i];
            while(count-->0) {
                a[j++]=i;
            }
        }
    }
    
    public static void main(String[] args) {
        Random r = new Random();
        Integer[] a =  new Integer[M];
        for(int i = 0; i<M; i++ ){
            a[i]=r.nextInt(M);
        }
        new MyBucketSort().sortWithLog4Integer(a);
    }


}
