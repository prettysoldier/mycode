package algorithm.stack_queue.visible_peek;

import java.util.Stack;

/**
 * @author shuaijunhe
 * @create 2019/9/23
 * @description
 */
public class VisiblePeek {
    /**
     * O(1)
     * 难度：1星
     * @param arr
     * @return 可见山峰的对数
     */
    private static int getVisiblePeekNoRepeat(int[] arr){

        return 2 * arr.length -3;
    }

    /**
     * O(N) 难度4星
     */
    private static int getVisiblePeekRepeat(int[] arr){

        if(arr == null || arr.length <= 1){
            return 0;
        }

        int maxIndex = 0;
        for(int i = 1; i < arr.length; i++){
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{arr[maxIndex], 1});

        int nextIndex = nextIndex(maxIndex, arr.length);
        int res = 0;

        while(nextIndex != maxIndex){

            while(arr[nextIndex] > stack.peek()[0]){
                int[] pop = stack.pop();
                res += 2 * pop[1] + getInnerNum(pop[1]);
            }
            if(arr[nextIndex] == stack.peek()[0]){
                stack.peek()[1] ++;
            }else{
                stack.push(new int[]{arr[nextIndex], 1});
            }
            nextIndex = nextIndex(nextIndex, arr.length);
        }

        while(stack.size() > 2){
            int[] pop = stack.pop();
            res += 2 * pop[1] + getInnerNum(pop[1]);
        }

        if(stack.size() == 2){
            int[] pop = stack.pop();
            res += (stack.peek()[1] == 1 ? 1 : 2) * pop[1] + getInnerNum(pop[1]);
        }

        res += getInnerNum(stack.pop()[1]);

        return res;
    }

    private static int nextIndex(int index, int size){
        return index + 1 >= size ? 0 : index + 1;
    }

    private static int getInnerNum(int k){
        return k == 1 ? 0 : (k * (k - 1) / 2);
    }

    public static void main(String[] args) {
        System.out.println(getVisiblePeekRepeat(new int[]{3,2,5,4,3,5,4,2,4,4,5}));
    }
}
