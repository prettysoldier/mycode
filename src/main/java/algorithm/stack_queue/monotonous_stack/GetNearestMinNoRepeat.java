package algorithm.stack_queue.monotonous_stack;

import java.util.Stack;

/**
 * @author shuaijunhe
 * @create 2019/9/23
 * @description
 */
public class GetNearestMinNoRepeat {

    private static int[][] getNearestMinNoRepeat(int[] arr) {

        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()] ) {
                int pop = stack.pop();
                res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                res[pop][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            res[pop][1] = -1;
        }
        return res;
    }

    public static void main(String[] args) {

        int[][] res = getNearestMinNoRepeat(new int[]{3, 4, 1, 5, 6, 2, 7});
        for (int[] t : res) {
            System.out.println(t[0] + ", " + t[1]);
        }
    }
}
