package algorithm.kth_num;

import algorithm.priorityqueue.MyBinaryHeap;
import algorithm.priorityqueue.PriorityQueueDemo;

import java.util.PriorityQueue;

/**
 * 第K大的数
 * 时间复杂度O(n) 额外空间O(1)
 * @author hsj
 * @create 2019/12/16
 */
public class KthLargestElement {

    public static int kthLargestElement(int n, int[] nums) {

        return PriorityQueueDemo.kthLargestElement(n, nums);
    }


}
