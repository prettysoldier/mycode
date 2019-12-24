package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class BinarySearch {

    public static void main(String[] args) {

        System.out.println(new BinarySearch().binarySearch(new int[]{3,4,5,8,8,8,8,10,13,14}, 8));
    }

    public int binarySearch(int[] nums, int target) {
        // write your code here
        if(nums.length == 0){
            return -1;
        }
        int center = nums.length >> 1;
        int index;
        if(target == nums[center]){
            index = center;
        }else if(target < nums[center]){
            index = inner(nums, 0, center - 1, target);
        }else{
            index = inner(nums, center + 1, nums.length - 1, target);
        }
        while(index > 0 && nums[index] == nums[index -1]){
            index--;
        }
        return index;
    }

    private int inner(int[] nums, int start, int end, int target){
        if(end < start){
            return -1;
        }
        int center = (start + end) >> 1;
        if(target == nums[center]){
            return center;
        }
        if(target < nums[center]){
            return inner(nums, start, center - 1, target);
        }

        return inner(nums, center + 1, end, target);
    }


}
