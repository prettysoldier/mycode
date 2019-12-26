package algorithm.majority_element;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.排序后，中间的元素即是
 * 2.桶排序
 * 3.抵消法：任意删去两个不同的数之后，在剩余子数组中，主元素依旧是主元素。随着遍历，最后剩下的数，一定是主元素（因为最多删除n/2 - 1次）
 * @author hsj
 * @create 2019/12/26
 */
public class MajorityElement {

    public static void main(String[] args) {
        System.out.println(majorityNumber(Arrays.asList(1, 3, 1, 2, 1, 2, 3, 1, 1)));
    }

    /**
     * 数组中有N个数，其中有一个数严格超过1/2，求这个数
     * @param nums
     * @return
     */
    public static int majorityNumber(List<Integer> nums) {

        int currentMajor = nums.get(0);
        int count = 0;

        for(Integer num : nums) {
            if(count == 0) {
                currentMajor = num;
            }

            if(num == currentMajor) {
                count++;
            } else {
                count--;
            }
        }

        return currentMajor;
    }

    /**
     * 数组中有N个数，其中有两个数严格超过1/3，求这两个数
     * 抵消法：当出现三个元素不同时，相互抵消。
     * @param nums
     * @return
     */
    public static int[] majorityNumber2(List<Integer> nums) {

        int tmp1 = nums.get(0);
        int tmp2 = nums.get(0);
        int count1 = 0;
        int count2 = 0;

        for(Integer num : nums) {

            if(num == tmp1) {
                count1++;
            } else if(num == tmp2) {
                count2++;
            } else if(count1 == 0){
                tmp1 = num;
                count1 = 1;
            } else if(count2 == 0){
                tmp2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        int[] data = new int[2];
        data[0] = tmp1;
        data[1] = tmp2;
        return data;
    }

    /**
     * 数组中有N个数，只有一个数严格超过1/3，求这个数
     * 与问题二基本相同，在得到两个数之后，我们取出现次数更多的那个数
     * @param nums
     * @return
     */
    public static int majorityNumber3(List<Integer> nums) {

        int tmp1 = nums.get(0);
        int tmp2 = nums.get(0);
        int count1 = 0;
        int count2 = 0;

        for(Integer num : nums) {

            if(num == tmp1) {
                count1++;
            } else if(num == tmp2) {
                count2++;
            } else if(count1 == 0){
                tmp1 = num;
                count1 = 1;
            } else if(count2 == 0){
                tmp2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        return count1 > count2 ? tmp1 : tmp2;
    }

    /**
     * 如果数组中存在且只存在一个出现次数严格超过1/k的数，找到这个数。要求使用O(k)的额外空间和O(n)的时间。
     * 假设K=4
     * @param nums
     * @return
     */
    public static int majorityNumber4(List<Integer> nums) {

        int tmp1 = nums.get(0);
        int tmp2 = nums.get(0);
        int tmp3 = nums.get(0);
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for(Integer num : nums) {

            if(num == tmp1) {
                count1++;
            } else if(num == tmp2) {
                count2++;
            } else if(num == tmp3) {
                count3++;
            } else if(count1 == 0){
                tmp1 = num;
                count1 = 1;
            } else if(count2 == 0){
                tmp2 = num;
                count2 = 1;
            } else if(count3 == 0){
                tmp3 = num;
                count3 = 1;
            } else {
                count1--;
                count2--;
                count3--;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(count1, tmp1);
        map.put(count2, tmp2);
        map.put(count3, tmp3);
        int max = Math.max(count1, Math.max(count2, count3));
        return map.get(max);
    }
}
