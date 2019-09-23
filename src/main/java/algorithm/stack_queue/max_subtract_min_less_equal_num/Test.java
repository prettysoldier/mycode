package algorithm.stack_queue.max_subtract_min_less_equal_num;

import java.util.LinkedList;

/**
 * @author shuaijunhe
 * @create 2019/9/23
 * @description
 */
public class Test {

    private static int getNum(int[] arr, int num){

        if(arr == null || arr.length == 0 || num < 0){
            return 0;
        }

        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int i = 0;
        int j = 0;
        int res = 0;
        while(i < arr.length){
            while(j < arr.length){

                while(!qmin.isEmpty() && arr[j] <= arr[qmin.peekLast()]){
                    qmin.pollLast();
                }
                qmin.addLast(j);

                while(!qmax.isEmpty() && arr[j] >= arr[qmax.peekLast()]){
                    qmax.pollLast();
                }
                qmax.addLast(j);

                if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num){
                    break;
                }
                j++;
            }
            res += j - i;
            if(qmin.peekFirst() == i){
                qmin.pollFirst();
            }
            if(qmax.peekFirst() == i){
                qmax.pollFirst();
            }
            i++;

        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(getNum(new int[]{3,5,4,1,2,5,3}, 2));
    }
}
