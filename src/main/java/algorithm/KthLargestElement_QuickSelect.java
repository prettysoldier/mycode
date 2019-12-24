package algorithm;

/**
 * 第K大的数
 * 时间复杂度O(n) 额外空间O(1)
 * @author hsj
 * @create 2019/12/16
 */
public class KthLargestElement_QuickSelect {

    public static void main(String[] args) {

        System.out.println(kthLargestElement(3, new int[]{9,3,2,4,8}));
    }

    public static int kthLargestElement(int k, int[] nums) {

        // write your code here
        int low = 0, high = nums.length -1;
        while(low <= high){
            int index = low-1;
            for(int i = low; i < high; i++){
                if(nums[i] > nums[high]){
                    swap(nums, i, ++index);
                }
            }
            swap(nums, ++index, high);
            if(index == k - 1){
                return nums[index];
            }
            if(index < k -1){
                low = index + 1;
            }else{
                high = index - 1;
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


}
