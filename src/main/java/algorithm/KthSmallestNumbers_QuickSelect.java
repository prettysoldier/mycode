package algorithm;

/**
 * @author hsj
 * @create 2019/12/25
 */
public class KthSmallestNumbers_QuickSelect {

    public int kthSmallest(int k, int[] nums) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    public int quickSelect(int[] A, int start, int end , int k) {
        if (start == end) {
            return A[start];
        }

        int left = start, right = end;
        int pivot = A[(start + end) / 2];

        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }
            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }

        if (right >= k && start <= right) {
            return quickSelect(A, start, right, k);
        }
        else if (left <= k && left <= end) {
            return quickSelect(A, left, end, k);
        }
        return A[k];
    }
}
