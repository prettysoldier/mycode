package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author heshuaijun
 * @date 2019/12/14 15:43
 */
public class SearchInsert {

    public static void main (String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

    public static int searchInsert(int[] A, int target) {
        if(A == null || A.length == 0){
            return 0;
        }
        if(target <= A[0]){
            return 0;
        }
        if(target == A[A.length - 1]){
            return A.length - 1;
        }
        if(target > A[A.length - 1]){
            return A.length;
        }
        // write your code here
        return binarySearch(A, 0, A.length, target);
    }

    public static int binarySearch(int[] A, int start, int end, int target){
        if(end - start == 1){
            if(target <= A[start]){
                return start;
            }
            return start + 1;
        }
        int medium = (end-start) / 2 + start;
        if(target == A[medium]){
            return medium;
        }
        if(target < A[medium]){
            return binarySearch(A, start, medium, target);
        }
        return binarySearch(A, medium, end, target);
    }
}
