package algorithm.kth_num;

/**
 * 给定一个未排序的整数数组，找到其中位数。
 *
 * 中位数是排序后数组的中间值，如果数组的个数是偶数个，则返回排序后数组的第N/2个数。
 *
 * 考点：
 *
 * 区间第k大元素的查找
 * 题解：
 * 利用快速排序的思想指导，不断递归将一个区间分为左右两个小区间，当枢轴左区间大小为所求中位数位置时，寻找到返回值。
 * 经过一次划分之后，枢轴pivot将原序列划分为两个部分：S和T [pivot包含在子序列S中]，会出现下列三种情况：
 *
 * ·子序列S中有K个数，此时pivot位置即为第K大的数，返回
 *
 * ·子序列S中的数字个数小于K，假设个数为L，则需要子序列T中继续递归划分出来前(K-L)个数
 *
 * ·子序列S中的数字个数大于K，则需要子序列S中继续递归划分出来前K个数
 *
 * @author hsj
 * @create 2019/12/27
 */
public class MedianNum {

    public static int median(int[] nums) {
        int mid = (nums.length + 1) / 2;
        return sub(nums, 0, nums.length - 1, mid);
    }

    private static int sub(int[] nums, int start, int end, int size) {

        int mid = (start + end) / 2;
        int pivot = nums[mid];
        int i = start - 1, j = end + 1;
        for (int k = start; k < j; k++) {
            if (nums[k] < pivot) {
                i++;
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;
            } else if (nums[k] > pivot) {
                j--;
                int tmp = nums[j];
                nums[j] = nums[k];
                nums[k] = tmp;
                k--;
            }
        }
        if (i - start + 1 >= size) {
            return sub(nums, start, i, size);
        } else if (j - start >= size) {
            return nums[j-1];
        } else {
            return sub(nums, j, end, size - (j - start));
        }
    }
}
