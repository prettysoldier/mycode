package algorithm.stack_queue.monotonous_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shuaijunhe
 * @create 2019/9/23
 * @description
 */
public class GetNearestMinRepeat {

    private static int[][] getNearestMinNoRepeat(int[] arr) {

        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)] ) {
                List<Integer> pop = stack.pop();
                for(int j = 0; j < pop.size(); j++){
                    res[pop.get(j)][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    res[pop.get(j)][1] = i;
                }
            }

            if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            for(int j = 0; j < pop.size(); j++){
                res[pop.get(j)][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                res[pop.get(j)][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[][] res = getNearestMinNoRepeat(new int[]{2, 4, 4, 4, 5, 5, 3});
        for (int[] t : res) {
            System.out.println(t[0] + ", " + t[1]);
        }
    }
}
