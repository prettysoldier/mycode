package algorithm.priorityqueue;

import java.util.PriorityQueue;

/**
 * @author hsj
 * @create 2019/12/16
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {

        System.out.println(kthLargestElement(3, new int[]{9,3,2,4,8}));
    }

    public static int kthLargestElement(int n, int[] nums) {

        PriorityQueue<Integer> heap2 = new PriorityQueue<>((e1, e2)->{return e1 > e2 ? -1 : (e1.intValue() == e2 ? 0: 1);});
        for(int i : nums){
            heap2.add(i);
        }
        int ret = 0;
        while(n-- > 0){
            ret = heap2.poll();
        }
        return ret;
    }
}
