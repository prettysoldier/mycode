package algorithm.stack_queue.get_window_max_arr;

import java.util.LinkedList;

/**
 * @author heshuaijun
 * @date 2019/9/22 22:34
 */
public class GetWindowMax {

    private static int[] getWindowMax(int[] arr, int w){

        if( arr == null || w < 1 || arr.length < w){
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            while(!qmax.isEmpty() && arr[i] >= arr[qmax.peekLast()]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst() + w == i){
                qmax.pollFirst();
            }
            if(i >= w - 1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main (String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        for(int i : getWindowMax(arr, 3)){

            System.out.println(i);
        }
    }
}
