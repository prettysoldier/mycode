package algorithm;

import java.util.Arrays;

/**
 * @author heshuaijun
 * @date 2019/6/12 22:32
 */
public class RecursionDemo {

    private static int sum (int[] a){
        if(a.length == 0){
            return 0;
        }
        if(a.length == 1){
            return a[0];
        }
        int middle = a.length / 2;
        int[] left = Arrays.copyOfRange(a, 0, middle);
        int[] right = Arrays.copyOfRange(a, middle, a.length);
        return sum(left) + sum(right);
    }

    private static int sum2 (int[] a){
        if(a.length == 0){
            return 0;
        }
        if(a.length == 1){
            return a[0];
        }
        int left = a[0];
        int[] right = Arrays.copyOfRange(a, 1, a.length);
        return left + sum(right);
    }

    public static void main (String[] args) {
        int[] a = {2, 3, 6, 4, 7, 8};
        System.out.println(sum(a));
        System.out.println(sum2(a));
    }
}
